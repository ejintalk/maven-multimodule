# Plugin과 PluginManagement 

Maveve은 빌드 작업을 할 때 <plugins> 요소의 <plugin>들을 사용하여 빌드작업을 한다. 플러그인에는 소스를 컴파일하기 위한 플러그인, war 파일을 만들기 위한 플러그인 , 톰캣에 배포하기 위한 플러그인 등이 있다. Maven은 많은 플러그인들이 존재한다

```xml
<build>
    ...
    <pluginManagement>
      <plugins>
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-jar-plugin</artifactId>
          <version>2.2</version>
          <executions>
            <execution>
              <id>pre-process-classes</id>
              <phase>compile</phase>
              <goals>
                <goal>jar</goal>
              </goals>
              <configuration>
                <classifier>pre-process</classifier>
              </configuration>
            </execution>
          </executions>
        </plugin>
      </plugins>
    </pluginManagement>
    ...
  </build>
```

위의 이런 명세들을 플러그인(plugins) 요소에 추가했다면, 그것들은 단 하나의 POM에 적용될 것이다. 하지만 이것들을 pluginManagement 요소(element) 아래에 그서들을 적용한다면 이 pom과 maven-jar-plugin을 빌드에 추가한 상속받은 POM들은 마찬가지로 pre-process-classess excecution을 얻을 것이다. 따라서 모든 자식 pom.xml에서 복잡한 혼란스러운 설정이아니라 아래처럼 간단히 작성할 수 있다.


```xml
<build>
    ...
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jar-plugin</artifactId>
      </plugin>
    </plugins>
    ...
  </build>
```



## \<executions\> Tag 사용하기 

플러그인은 실행(execution)을 정의할 수 있다. 실행은 플러그인의 목적을 달성하기 위한 단계(phase)와 목표(goal)를 포함한다.


```xml
<project>
  ...
  <build>
    <plugins>
      <plugin>
        <artifactId>maven-myquery-plugin</artifactId>
        <version>1.0</version>
        <executions>
          <execution>
            <id>execution1</id>
            <phase>test</phase>
            <configuration>
              <url>http://www.foo.com/query</url>
              <timeout>10</timeout>
              <options>
                <option>one</option>
                <option>two</option>
                <option>three</option>
              </options>
            </configuration>
            <goals>
              <goal>query</goal>
            </goals>
          </execution>
          <execution>
            <id>execution2</id>
            <configuration>
              <url>http://www.bar.com/query</url>
              <timeout>15</timeout>
              <options>
                <option>four</option>
                <option>five</option>
                <option>six</option>
              </options>
            </configuration>
            <goals>
              <goal>query</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
  ...
</project>
```

ID가 "execution1"인 첫 번째 실행은 이 구성을 테스트 단계(test phase)에 바인딩한다.  두 번째 실행에는 \<phase\>태그가 없습니다. 이 실행이 어떻게 작동할까?  목표는 아래에서 더 논의되는 바와 같이 기본 단계 바인딩을 가질 수 있다. 목표에 기본 단계 바인딩이 있는 경우 해당 단계에서 실행된다. 그러나 목표가 수명 주기 단계에 바인딩되지 않은 경우 빌드 수명 주기 동안 실행되지 않는다.

실행 ID는 POM 내 단일 플러그인의 모든 실행에서 고유해야 하지만 POM의 상속 계층에서 고유할 필요는 없다. 다른 POM에서 동일한 ID의 실행이 병합된다. 프로필에 의해 정의된 실행에도 동일하게 적용된다. 








