# Exec Maven Plugin


http://www.mojohaus.org/exec-maven-plugin/

이 플러그인은 system과 java programs들을 실행하는 것을 돕기 위해 2개의 goal을  제공한다. 


##  Command line
system properties를 사용하여 당신은 다음의 예제에서와 같이 그것을 실행할 수 있을 것이다. 
```shell
mvn exec:exec -Dexec.executable="maven" 
[-Dexec.workingdir="/tmp"] -Dexec.args="-X myproject:dist"
```

## POM Configuration
```xml
<project>
  ...
  <build>
    <plugins>
      <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>exec-maven-plugin</artifactId>
        <version>1.5.0</version>
        <executions>
          <execution>
            ...
            <goals>
              <goal>exec</goal>
            </goals>
          </execution>
        </executions>
        <configuration>
          <executable>maven</executable>
          <!-- optional -->
          <workingDirectory>/tmp</workingDirectory>
          <arguments>
            <argument>-X</argument>
            <argument>myproject:dist</argument>
            ...
          </arguments>
        </configuration>
      </plugin>
    </plugins>
  </build>
   ...
</project>
```


## Java goal
이 goal은 당신이 동일한 VM 내에서 Java program을 실행하도록 돕는다. 

커맨드 라인에서 실행은 다음과 같이 한다. 
```shell
mvn exec:java -Dexec.mainClass="com.example.Main" [-Dexec.args="argument1"] ...
```
POM에 plugin을 구성할 수 있다. 
```xml
<project>
  ...
  <build>
    <plugins>
      <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>exec-maven-plugin</artifactId>
        <version>1.5.0</version>
        <executions>
          <execution>
            ...
            <goals>
              <goal>java</goal>
            </goals>
          </execution>
        </executions>
        <configuration>
          <mainClass>com.example.Main</mainClass>
          <arguments>
            <argument>argument1</argument>
            ...
          </arguments>
          <systemProperties>
            <systemProperty>
              <key>myproperty</key>
              <value>myvalue</value>
            </systemProperty>
            ...
          </systemProperties>
        </configuration>
      </plugin>
    </plugins>
  </build>
   ...
</project>
```

이 java goal은 새로운 프로세스를 생성(spawn) 하지 않는다. 

