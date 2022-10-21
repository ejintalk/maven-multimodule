# JAR plugin

## How to Build a JAR file

Maven을 가지고 jar 파일을 만들려면, 먼저 pom.xml에 최소한 다음과 같이 pom.xml 파일을 생성해야 한다.
```xml
<project>
  <modelVersion>4.0.0</modelVersion>
  
  <groupId>com.mycompany.project</groupId>
  <artifactId>core</artifactId>
  <version>1.0-SNAPSHOT</version>
  <!-- <packaging>jar</packaging>  -->
</project>
```


이 경우에 기본 패키징 타입이므로 그것을 설정할 필요는 없다.   jar 파일를 command로 만들려면 아래와 같이 한다. 

```shell
mvn package 
```

## How to include/exclude content from jar artifact

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
        <configuration>
          <includes>
            <include>**/service/*</include>
          </includes>
        </configuration>
      </plugin>
      ...
    </plugins>
  </build>
  ...
</project>
```


## How to create an additional attached jar artifact from the project
jar-plugin은 new execution을 정의해야 한다. 그렇지 않으면 그것은 두번째 artifact를 추가하는 대신에 jar-plugin의 기본 사용을 대체할 것이다. 
하나 이상의 artifact를 생성하려면 classifiedr 또한 필요하다.
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
            <phase>package</phase>
            <goals>
              <goal>jar</goal>
            </goals>
            <configuration>
              <classifier>client</classifier>
              <includes>
                <include>**/service/*</include>
              </includes>
            </configuration>
          </execution>
        </executions>
      </plugin>
      ...
    </plugins>
  </build>
  ...
</project>
```




## Use Your Own Manifest File
https://maven.apache.org/shared/maven-archiver/examples/manifestFile.html

기본적으로, Maven Archiver는 당신을 위해 manifest 파일을 생성한다. 당신 자신의 손으로 가공한 manifest 파일을 사용하는 것은  쓸모있다.   src/main/resources/META-INF/MENIFEST.MF manifest 파일을 사용하고 싶다고 말하자. 이것은 <manifetFile> configuration element를 가지고 이루어진다. 당신의 팡리의 위치에 대한 값을 설정함으로써 할 수 있다. 

당신 자신의 manifest file의 content는 Maven Archiver에 의해 생성된 etries와 함께 머지될 것이다. 


당신 자신의 manifiest file에 entry를 명시한다면, 그것은 Maven Archiver에 의해 생성된 값을 override할 것이다. 
```xml
<project>
  ...
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jar-plugin</artifactId>
        ...
        <configuration>
          <archive>
            <manifestFile>src/main/resources/META-INF/MANIFEST.MF</manifestFile>
          </archive>
        </configuration>
        ...
      </plugin>
    </plugins>
  </build>
  ...
</project>
```

다른 예) 
```xml
<plugin>
					<groupId>org.apache.maven.plugins</groupId>
					<artifactId>maven-jar-plugin</artifactId>
					<version>2.4</version>
					<configuration>
						<archive>
							<manifestFile>${project.build.outputDirectory}/META-INF/MANIFEST.MF</manifestFile>
						</archive>
					</configuration>
					<executions>
						<execution>
							<id>test-jar</id>
							<phase>package</phase>
							<goals>
								<goal>test-jar</goal>
							</goals>
							<configuration>
								<archive>
									<manifest>
										<addDefaultSpecificationEntries>true</addDefaultSpecificationEntries>
										<addDefaultImplementationEntries>true</addDefaultImplementationEntries>
									</manifest>
								</archive>
							</configuration>
						</execution>
					</executions>
				</plugin>
```

## Manifest

https://maven.apache.org/shared/maven-archiver/examples/manifest.html


**Default Manifest**
```shell
Manifest-Version: 1.0
Archiver-Version: Plexus Archiver
Created-By: Apache Maven ${maven.version}
Built-By: ${user.name}
Build-Jdk: ${java.version}
```

**Adding Implementation And Specification Details**

디폴트르 Maven은 Implementation과 Specification details을 더이상 생성하지 않는다. 
당시의 manifest 파일에 그것을 두고 싶으면 configuration에 명확히 그렇게 말해야 한다.
```xml
<project>
  ...
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jar-plugin</artifactId>
        <version>2.1</version>
        ...
        <configuration>
          <archive>
            <manifest>
              <addDefaultImplementationEntries>true</addDefaultImplementationEntries>
              <addDefaultSpecificationEntries>true</addDefaultSpecificationEntries>
            </manifest>
          </archive>
        </configuration>
        ...
      </plugin>
    </plugins>
  </build>
  ...
</project>
```


결과의 manifest는 이러한 조각들의 정보를 포함할 것이다.
```shell
Manifest-Version: 1.0
Archiver-Version: Plexus Archiver
Created-By: Apache Maven ${maven.version}
Built-By: ${user.name}
Build-Jdk: ${java.version}
Specification-Title: ${project.name}
Specification-Version: ${project.artifact.selectedVersion.majorVersion}.${project.artifact.selectedVersion.minorVersion}
Specification-Vendor: ${project.organization.name}
Implementation-Title: ${project.name}
Implementation-Version: ${project.version}
Implementation-Vendor-Id: ${project.groupId}
Implementation-Vendor: ${project.organization.name}
Implementation-URL: ${project.url}
```
