# Assemblyu plugin 


Maven Shade plugin

패키징 시에 dependency 옵션 중 scope의 값에 따라 해당 dependency를 패키징에 포함할지 안할지를 결정할 수 있어 가벼운 jar 파일을 생성할 수 있다. 


패키지에 포함할 dependency
```xml
<dependency>
   <groupId>com.xxxx.xxxxxxxx</groupId>
   <artifactId>xxxx</artifactId>
   <version>x.x.x</version>
   <classifier>jar-with-dependencies</classifier>

   <scope>compile</scope>
</dependency>
```


패키지에 포함하지 않을 dependency 
```xml
<dependency>
   <groupId>com.xxxx.xxxxxxxx</groupId>
   <artifactId>xxxx</artifactId>
   <version>x.x.x</version>
   <classifier>jar-with-dependencies</classifier>

   <scope>provided</scope>
</dependency>
```

maven 명령시 goal을 shade:shade로 지정하여 실행할 수 있지만, <execution> 설정을 통해 package에 shade를 바인딩하는 설정으로 mvn package를 구동할 수 있다.

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <version>2.4.3</version>
            <configuration>
                <!-- put your configurations here -->                         
            </configuration>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>shade</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

Resource Transformer

shade 플러그인을 사용할 때 Resource Transformer 설정을 하면 
서로다른 artifacts들로 부터 uber-jar를 생성할 때 classes 및 resources 파일들을 중복없이 패키징 할 수 있게 해준다. 


각 Resources Transformer 설저의 종류 및 특징. 


ManifestResources Transformer
자바 MANIFEST 파일의 entries를 세팅해 준다.  실행가능한 jar 파일을 생성할 시에 자바 어프리케이션을 구동할 MainClass를 지정해야 하는데, 이것은 ‘MANIFEST’파일의 entry중 하나이다. 


\<configuration\> 설정을 추가한다. 
```xml
<configuration>
    <transformers>
        <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
            <mainClass>com.asuraiv.project.aaa.MainClass</mainClass>
        </transformer>
    </transformers>
</configuration>
```

실행 가능한 jar 파일을 생성할 시에 자바 어플리케이션을 구동할 MainClass를 지정해야 하는데, 이것은 MANIFEST 파일의 entry 중 하나이다.  위 예제ㅔ처럼 <mainClass> 설정으로 해당 어플리케이션의 메인클래스를 입력한다. 

AppendingTransformer
maven-assembly-plugin을 사용하면, 파일내에 명시된 클래스의 namespace가 중복되는 경우, 파일들이 동일한 이름의 존재하는 경우에 마지막 것만 남는다. 하지만 maven-shade-plugin의 AppendingTransformer를 설정해 주면, 파일의 중복문제, namespace의 중복문제를 해결해 준다. 
(하나의 파일로 합쳐짐) 
```xml
<configuration>
    <transformers>
        <transformer
            implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
            <mainClass>org.springframework.batch.core.launch.support.CommandLineJobRunner</mainClass>                                   
        </transformer>
        <transformer
            implementation="org.apache.maven.plugins.shade.resource.AppendingTransformer">
            <resource>META-INF/spring.handlers</resource>
        </transformer>
        <transformer
            implementation="org.apache.maven.plugins.shade.resource.AppendingTransformer">
            <resource>META-INF/spring.schemas</resource>
        </transformer>
</configuration
```



> MANIFEST.MF이란?
> Manifest 파일은 해당 자바 어플리케이션의 정보 즉, 일종의 메타정보(패키지 관련 정보 및 파일 확장 관련 정보)를 담고 있는 파일이다.
> JAR 파일을 생성할 때 자동적으로 기본 manifest 파일이 생성된다.
> 하나의 아카이브 안에는 오직 하나의 manifest 파일이 존재하고 META-INF 디텍토리에 있어야 한다.



http://maven.apache.org/plugins/maven-assembly-plugin/usage.html


프로젝트의 따라오는 그것의 dependencies, modules, site documentation, 그리고 다른 파일들을 하나의 배포물(distributable archive)로 만들도록 해준다. 

당신의 프로젝트는 배포 “assemblies”를 쉽게 빌드할 수 있다.  현재,  다음의 포맷에서 배포물을 생성할 수 있다. 
```shell
zip
tar
tar.gz (or tgz)
tar.bz2 (or tbz2)
jar
dir
war
and any other format that the ArchiveManager has been configured for
```
	“assembly”는 files, directories 그리고 dependencies의 그룹이다.


#### Configuration
우리의 프로젝트는 하나의 JAR 파일을 생산한다고 가정하라. 우리가 우리의 project의 의존들을 포함한 assembly binary을 생성하고 싶다면,  다음과 같이 설정할 수 있다. 
```xml
<project>
  [...]
  <build>
    [...]
    <plugins>
      <plugin>
        <!-- NOTE: We don't need a groupId specification because the group is
             org.apache.maven.plugins ...which is assumed by default.
         -->
        <artifactId>maven-assembly-plugin</artifactId>
        <version>2.6</version>
        <configuration>
          <descriptorRefs>
            <descriptorRef>jar-with-dependencies</descriptorRef>
          </descriptorRefs>
        </configuration>
        [...]
</project>
```

Assembly Plugin은 당신이 다중의 descriptorRef를  한번에  명시하도록 허용한다. 

대안적으로, 우리는 src.xml이라고 부르는 – src/assembly 디렉토리에 있는- custom assembly descriptor를 생성했다. 우리는 Assembly Plugin 에게 대신에 그것을 사용하라고 말할 수 있다. 
```xml
<project>
  [...]
  <build>
    [...]
    <plugins>
      <plugin>
        <artifactId>maven-assembly-plugin</artifactId>
        <version>2.6</version>
        <configuration>
          <descriptors>
            <descriptor>src/assembly/src.xml</descriptor>
          </descriptors>
        </configuration>
        [...]
</project>
```

다시, 우리는 다중 custom assembly descriptiors을 여기에 명시할 수 있을 것이다.  게다가,  그것은같은 configuration내에 desciptors와 descriptorRefs를 섞어서 명시하는게 가능하다.


#### Execution : Building an Assembly
대부분의 경우에 당신은 당신의 assemblies가 당신의 정상적인 build process의 일부분으로써 생성된 것을 확인하고 싶을 것이다. 
이것은 assembly archives가 설치와 배포시에 유용하도록 만들어진것을 보장한다.  

이것은 assembly:single goal에 의해서 처리된다. 

single goal을 project의 build lifecycle과 bind하기 위해서, 당신은 이 configuration을 추가할 수 있다. (조립식의(prefabricated) descriptor jar-with-dependencies를 사용하고 있다고 가정하고)
```xml
<project>
  [...]
  <build>
    [...]
    <plugins>
      <plugin>
        <artifactId>maven-assembly-plugin</artifactId>
        <version>2.6</version>
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
      [...]
</project>
```
그런다음, 프로젝트 assembly를 생성하기 위해서, default lifecycle로부터 normal package phase를 실행한다. 

```shell
mvn package
```

이 빌드가 완료된면, 당신은 target directory에 하나의 파일을 볼수 있다. 다음과 비슷한 이름을 가졌다. 


target/sample-1.0-SNAPSHOT_jar-withh-dependencies.jar


#### 정리

pom 파일에 json-lib 의존을 설정했다. 
```xml
<dependency>
	<groupId>net.sf.json-lib</groupId>
	<artifactId>json-lib</artifactId>
	<version>2.3</version>
	<classifier>jdk15</classifier>
</dependency>
```


mvn package 를 실행했다. 
두개의 Jar 파일이 생기는데 


```shell
📂 maven-status
📂 site
    📄test-maven-0.0.1-SNAPSHOT-jar-with-dependencies.jar
    📄test-maven-0.0.1-SNAPSHOT.jar 
```


XX-with-dependencies.jar 파일을 열어보면  org.net.sf.json이 포함되어 있고,

![](.gitbook/assets/maven/2021-12-17-11-26-18.png)

test-maven-0.0.1-SNAPSHOT.jar를 열어보면 의존하는 jar가 포함되지 않았다.


![](.gitbook/assets/maven/2021-12-17-11-26-44.png)


####  custom assembly descriptiors

http://maven.apache.org/plugins/maven-assembly-plugin/assembly.html