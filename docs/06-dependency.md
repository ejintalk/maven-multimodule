# 의존성 

## Dependencies
POM의 주춧돌(cornerstone)은 그것의 의존성 리스트다. 대부분의 모든 프로젝트는 빌드, 실행될 때 정확히 다른 것들에 의존한다. 

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                      http://maven.apache.org/xsd/maven-4.0.0.xsd">
  ...
  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.0</version>
      <type>jar</type>
      <scope>test</scope>
      <optional>true</optional>
    </dependency>
    ...
  </dependencies>
  ...
</project>
```

* groupid, artifactid, version
* classifier 동일한 POM에서 빌드되는 산추물을 구분하는 것을 허용
* type
  * 산출물의 packaging type
  * jar, ejb-client, test-jar 
*	scope task의 classpath에 대한 참조
  * compile :컴파일시에만
  * provided : 컴파일과 비슷하지만, JDK 또는 container가 제공
  * runtime : 실행시에만 필요
  * test : test 시에만 필요
  * system : provided와 비슷하지만 JAR를 명백히 제공해야 함
  systemPath : dependency scope가 system일 때만 사용된다 예) \${java.home}/lib


## Exclusions
포함하지 않을 프로젝트 의존성을 설명한다. 
```xml
project xmlns="http://maven.apache.org/POM/4.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                      http://maven.apache.org/xsd/maven-4.0.0.xsd">
  ...
  <dependencies>
    <dependency>
      <groupId>org.apache.maven</groupId>
      <artifactId>maven-embedder</artifactId>
      <version>2.0</version>
      <exclusions>
        <exclusion>
          <groupId>org.apache.maven</groupId>
          <artifactId>maven-core</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
    ...
  </dependencies>
  ...
</project>
```

