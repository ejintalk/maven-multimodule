# Resources Plugin



https://maven.apache.org/plugins/maven-resources-plugin/


Resources Plugin은 프로젝트 resource의 복사본을 output directory에 복사한다. 리소스에는 두가지의 다른 종류가 있는데 main resources와 test resources가 있다. main resource는 main source code와 연과된 리소스이고 반면에 test resource는 test source 코드와 연관되어 있다. 따라서 이 플러그인은 main source code와 그것의 단위테스트 코드를 위한 리소스의 분리를 허용한다. 


```xml
<plugin>
        <artifactId>maven-resources-plugin</artifactId>
        <version>2.5</version>
        <executions>
          <execution>
            <id>default-resources</id>
            <phase>process-resources</phase>
            <goals>
              <goal>resources</goal>
            </goals>
          </execution>
          <execution>
            <id>default-testResources</id>
            <phase>process-test-resources</phase>
            <goals>
              <goal>testResources</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
```


## Copy Resources for the main source code
```shell
mvn resources:resources
```

## Copy resources for the unit tests
```shell
mvn resources:testResources
```
