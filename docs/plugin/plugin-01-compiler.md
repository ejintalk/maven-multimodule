# Compiler Plugin 
[Maven Compiler Plugin](https://maven.apache.org/plugins/maven-compiler-plugin/)은 프로젝트의 소스를 컴파일하는데 사용한다.


```xml
<plugin>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.0</version>
        <executions>
          <execution>
            <id>default-testCompile</id>
            <phase>test-compile</phase>
            <goals>
              <goal>testCompile</goal>
            </goals>
            <configuration>
              <source>1.6</source>
              <target>1.6</target>
              <encoding>utf-8</encoding>
            </configuration>
          </execution>
          <execution>
            <id>default-compile</id>
            <phase>compile</phase>
            <goals>
              <goal>compile</goal>
            </goals>
            <configuration>
              <source>1.6</source>
              <target>1.6</target>
              <encoding>utf-8</encoding>
            </configuration>
          </execution>
        </executions>
        <configuration>
          <source>1.6</source>
          <target>1.6</target>
          <encoding>utf-8</encoding>
        </configuration>
      </plugin>
```

## Compiling You Java Sources
```
mvn compile
mvn test-compile 
```

## 구성하기 

Compiler Plugin은 자동적으로 단계들에서 실행되기 때문에, executions를 다른 많은 플러그인들과는 다르게 넣을 필요는 없다. 그러나 Compiler Plugin의 버전은 명시해야 한다.

```xml
<project>
  ...
  <build>
    <pluginManagement>
      <plugins>
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.5.1</version>
          <configuration>
            <!-- put your configurations here -->
          </configuration>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
  ...
</project>
```
