# WAR plugin 



https://maven.apache.org/plugins/maven-war-plugin/

이 플러그인은 산출물(artifacts) 의존성, 클래스들 그리고 웹 어플리케이션의 리소스들을 모으고(collecting) 그것을 web application archive로 packaging할 책임이 있다. 

```xml
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-war-plugin</artifactId>
	<version>2.4</version>
	<!-- <configuration> <archive> <manifestFile>${project.build.outputDirectory}/META-INF/MANIFEST.MF</manifestFile> 
	</archive> </configuration> -->
</plugin>
```

#### Using the package phase with the project package type as war
다음은 WAR Plugin을 사용하는 정상적인 방법이다.



```xml
<project>
  ...
  <groupId>com.example.projects</groupId>
  <artifactId>documentedproject</artifactId>
  <packaging>war</packaging>
  <version>1.0-SNAPSHOT</version>
  <name>Documented Project</name>
  <url>http://example.com</url>
  ...
</project>
```

프로젝트의 구조는 다음과 같이 보인다. 
```shell
|-- pom.xml
 `-- src
     `-- main
         |-- java
         |   `-- com
         |       `-- example
         |           `-- projects
         |               `-- SampleAction.java
         |-- resources
         |   `-- images
         |       `-- sampleimage.jpg
         `-- webapp
             |-- WEB-INF
             |   `-- web.xml
             |-- index.jsp
             `-- jsp
                 `-- websource.jsp
```
호출하기 
```shell
mvn package
```

또는 
```shell
mvn compile war:war
```

은 target/documentedproject-1.0-SNAPSHOT.war WAR 파일을 생성할 것이다. 여기에 WAR 파일의 컨텐츠가 있다. 
```shell
documentedproject-1.0-SNAPSHOT.war
  |-- META-INF
  |   |-- MANIFEST.MF
  |   `-- maven
  |       `-- com.example.projects
  |           `-- documentedproject
  |               |-- pom.properties
  |               `-- pom.xml
  |-- WEB-INF
  |   |-- classes
  |   |   |-- com
  |   |   |   `-- example
  |   |   |       `-- projects
  |   |   |           `-- SampleAction.class
  |   |   `-- images
  |   |       `-- sampleimage.jpg
  |   `-- web.xml
  |-- index.jsp
  `-- jsp
      `-- websource.jsp

```

나머지는 생략


