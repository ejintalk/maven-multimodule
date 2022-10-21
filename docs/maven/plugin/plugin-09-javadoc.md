# Javadoc plugin


## Project의 일부로 javadoc 만들기

site generation의 일부로써 javadocs를 만들기 위해서, Javadoc Plugin을 <reporting> section에 추가해야 한다.
```xml
<project>
  ...
  <reporting>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-javadoc-plugin</artifactId>
        <version>2.10.4</version>
        <configuration>
          ...
        </configuration>
      </plugin>
    </plugins>
    ...
  </reporting>
  ...
</project>
```

mvn site를 실행할 때, javadocs 생성되고 생성된 사이트에 포함될 것이다. javadocs에 대한 링크는 Project Reports menu에 추가될 것이다.


## Standalone Javadocs 만들기
프로젝트에 대한 standalone javadocs를 생성하려면, Javadoc Plugin 을  <build> section에 추가해야 한다.  설정(configuration)이 없으면 , 기본값을을 사용한다. 

```xml
<project>
  ...
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-javadoc-plugin</artifactId>
        <version>2.10.4</version>
        <configuration>
          ...
        </configuration>
      </plugin>
    </plugins>
    ...
  </build>
  ...
</project>
```
그리고 다음 commands에서 하나를 실행한다. 
```shell
mvn javadoc:javadoc
mvn javadoc:jar
mvn javadoc:aggregate
mvn javadoc:aggregate-jar
mvn javadoc:test-javadoc
mvn javadoc:test-jar
mvn javadoc:test-aggregate
mvn javadoc:test-aggregate-jar
```






## Javadoc Configuration
Javadoc plugin은 많은 configuration parameters를  지원한다. 각 configuration parameter는 tag name으로 변한다.  자세한 사항은 https://maven.apache.org/plugins/maven-javadoc-plugin/javadoc-mojo.html 을 참고한다. 

\<reporting/\> or \<build/\> 요소에 Javadoc plugin을 설정하는 것은 Plugins 설정하기에 대한 가이드에서 설명한 것과 동일한 동작을 갖는다.
```xml
<project>
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-javadoc-plugin</artifactId>
        <version>2.10.4</version>
        <configuration>
          <show>private</show>
          <nohelp>true</nohelp>
        </configuration>
      </plugin>
    </plugins>
  </build>
 
  <reporting>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-javadoc-plugin</artifactId>
        <version>2.10.4</version>
        <configuration>
          <stylesheetfile>${basedir}/src/main/javadoc/stylesheet.css</stylesheetfile>
          <show>public</show>
        </configuration>
      </plugin>
    </plugins>
  </reporting>
  </build>
```

```shell
mvn site
```


\<reporting/\>에 정의된, 주어진 stylesheet를 사용하여 \<reporting/\>에 정의된 public member들을에 대한 Javadoc를 생성할 것이다. help page가 있는(기본은 없다)
```shell
mvn javadoc:javadoc
```
\<build/\>에서 정의된 help page, \<reporting/\>에서 정의된 stylesheet를 사용하여   \<build/\>에서 정의된 private members에 대한 javadoc를 생성할 것이다. 
