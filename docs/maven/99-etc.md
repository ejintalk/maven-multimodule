# 이것저것


## Java 실행 옵션 

-classpath	-cp,  참조할 클래스 패스
-D<property name>=<property value>	시스템의 프로퍼티 값을 설정
-Djava.library.path=



## 플러그인 활용법 
### 기본 jar 생성
pom.xml에 특별한 설정 없이 maven build를 하면  target 폴더에  xxxx-0.0.1-SNAPSHOT.jar 파일이 생성됨


### test case를 모아 놓은 jar 생성

maven-jar-plugin을 사용하여  goal을 test-jar로 설정하고 maven build를 하면,  xxxx-0.0.1-SNAPSHOT-test.jar 파일이 생성된다. 

test-class들을 포함한 jar를 생성하고 싶을 때, 이러한 클래스들을 재사용하고 싶을 것이다.  두가지 방법이 있는데, 

-	현재 프로젝트로 부터 test-classes를 가진 attached jar 를 생성, 타동사의(transitive) test-scoped 의존성들을 느슨하게 한다. 
-	test-calsses를 가진 분리된 project를 생성 

```xml
<project>
...

  <build>
    <plugins>
      ...
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jar-plugin</artifactId>
        <version>3.0.2</version>
        <executions>
          <execution>
            <goals>
              <goal>test-jar</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
      ...
    </plugins>
  </build>
  ...
</project>
```


test classe와 resources 을 포함한 jar을 생산할 수 있다. 

생성된 test-jar 파일을 풀어보면 test case가 들어 있다. 



http://maven.apache.org/plugins/maven-jar-plugin/examples/create-test-jar.html



### 의존하는 패키지를 하나의  빌드하는 project의  jar에 넣기 
\<pluginManagement\>안의 \<plugins\>에  다음을 추가 
```xml
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-assembly-plugin</artifactId>
	<version>2.4</version>
	<configuration>
		<descriptorRefs>
			<descriptorRef>jar-with-dependencies</descriptorRef>
		</descriptorRefs>
	</configuration>
	<executions>
		<execution>
			<id>make-assembly</id> <!-- this is used for inheritance merges -->
			<phase>package</phase> <!-- bind to the packaging phase -->
			<goals>
				<goal>single</goal>
			</goals>
		</execution>
	</executions>
</plugin>
```


\<build\>바로 아래의 \<plugins\>에 다음을 추가한다. 
```xml
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-assembly-plugin</artifactId>
</plugin>
```


build하면 –jar-with-dependencies 가 붙은 jar 파일이 하나 더 생긴다.  jar파일을 살펴보면 \<dependencies\>에 추가한 의존 라이브러리들이 들어 있다. 


### 빌드 시에 생성된 파일들 삭제
```shell
mvn clean
```
\<pluginManagement\>에 다음을 추가한다. 

```xml
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-clean-plugin</artifactId>
                    <version>3.0.0</version>
                    <executions>
                        <execution>
                            <id>auto-clean</id>
                            <phase>initialize</phase>
                            <goals>
                                <goal>clean</goal>
                            </goals>
                        </execution>
                    </executions>

                </plugin>
```




### Maven Ant 명령어 실행 
\<pluginManagement\>에 다음을 추가한다. 
```xml
<plugin>
	<artifactId>maven-antrun-plugin</artifactId>
	<version>1.8</version>
	<executions>
		<execution>
			<phase><!-- a lifecycle phase -->compile</phase>
			<configuration>
				<target>
		<!-- Place any Ant task here. You can add anything you can add between 
					<target> and </target> in a build.xml. -->
				<echo message="Hello Echo"  />
				</target>
			</configuration>
			<goals>
				<goal>run</goal>
			</goals>
		</execution>
	</executions>
</plugin>
```


\<phase\>에 실행할 단계를 넣는다.  \<configuration\>에 task를 넣는다.  \<excuction\>에 오류가 발생하면  maven-clean-plugin 처럼 처리한다. 

\<build\> 아래의 \<plugins\>에 다음을 추가한다. 
```xml
		 <plugin>
			<artifactId>maven-antrun-plugin</artifactId>
		</plugin>
```
```shell
mvn package 
```
실행하면 그림과 같이 Hello Echo 가 출력이 된다. 

### maven compiler jave version 설정하기

\<properties\>에 java version 설정
```xml
<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<target.jdk>1.8</target.jdk>
	</properties>
```
\<pluginManagement\>에 다음을 추가한다. 
```xml
<plugin>
					<groupId>org.apache.maven.plugins</groupId>
					<artifactId>maven-compiler-plugin</artifactId>
					<version>3.1</version>
					<configuration>
						<optimize>true</optimize>
						<source>${target.jdk}</source>
						<target>${target.jdk}</target>
					</configuration>
				</plugin>
```

프러퍼티에 jdk버전을 지원하지 않는 버전인 20.0으로 입력하면 빌드 시에 오류가 난다. 


### JavaDoc 생성하기 

\<pluginManagement\>에 다음을 추가한다. 

```xml
				<plugin>
					<groupId>org.apache.maven.plugins</groupId>
					<artifactId>maven-javadoc-plugin</artifactId>
					<version>2.9.1</version>
					<configuration>
						<linksource>true</linksource>
						<maxmemory>1g</maxmemory>
						<minmemory>256m</minmemory>
						<source>${target.jdk}</source>
						<tags>
							<tag>
								<name>note</name>
								<placement>a</placement>
								<head>NOTE</head>
							</tag>
							<tag>
								<name>todo</name>
								<placement>a</placement>
								<head>TODO</head>
							</tag>
							<tag>
								<name>warning</name>
								<placement>a</placement>
								<head>WARNING</head>
							</tag>
						</tags>
						<!-- apiviz stuff, comment ATM as it doesn't works on poms packaging 
							pom -->
						<!-- <doclet>org.jboss.apiviz.APIviz</doclet> <docletArtifact> <groupId>org.jboss.apiviz</groupId> 
							<artifactId>apiviz</artifactId> <version>1.3.1.GA</version> </docletArtifact> 
							<useStandardDocletOptions>true</useStandardDocletOptions> <charset>UTF-8</charset> 
							<encoding>UTF-8</encoding> <docencoding>UTF-8</docencoding> <breakiterator>true</breakiterator> 
							<version>true</version> <author>true</author> <keywords>true</keywords> <additionalparam>-sourceclasspath 
							${project.build.outputDirectory}</additionalparam> -->
					</configuration>
				</plugin>
```
\<reporting\>\<plugins\> 에 다음을 추가한다. 


```xml		
    <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-javadoc-plugin</artifactId>
                <reportSets>
                    <reportSet>
                        <reports>
                            <report>aggregate</report>
                        </reports>
                    </reportSet>
                </reportSets>
            </plugin>
			
```

### PMD로 정적 코드 검사하기 

\<pluginManagement\>에 다음을 추가한다. 

```xml
	
				    <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-pmd-plugin</artifactId>
                    <version>3.6</version>
                    <configuration>
                        <aggregate>true</aggregate>
                        <targetJdk>${target.jdk}</targetJdk>
                        <rulesets>
                            <!-- <ruleset>/rulesets/maven.xml</ruleset> -->
                            <ruleset>rulesets/java/basic.xml</ruleset>
                        </rulesets>
                        <format>xml</format>
                        <linkXref>true</linkXref>
                        <sourceEncoding>utf-8</sourceEncoding>
                        <minimumTokens>100</minimumTokens>
                    </configuration>
                </plugin>
```


\<reporting\>에 다음을 추가한다.
```xml
 <plugin>
         <groupId>org.apache.maven.plugins</groupId>
         <artifactId>maven-pmd-plugin</artifactId>
</plugin>
```

다음을 실행하면 site에 보고서가 생성될 것이다. 

```shell
mvn site 
```

