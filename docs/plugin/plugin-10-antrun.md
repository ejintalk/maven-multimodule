# Antrun Plugin 



http://maven.apache.org/plugins/maven-antrun-plugin/

이 플러그인은 Ant tasks를 Maven내에서 실행하는 능력을 제공한다. POM에 Ant scripts를 포함할 수 있다. 

http://maven.apache.org/plugins/maven-antrun-plugin/examples/tasksAttributes.html

## Run
mvan-antrun-plugin은 오직 하나의 goal – run 만을 가진다. 
이것은  Maven 이 Ant tasks를 실행할 수 있도록 해준다.  그렇게 하기 위해서, 존재하는 프로젝트가 있어야 하고, maven-antrun-plugin은 그것의 <target> tag를 설정해야 한다. (그것이 <target> tag 없이 여전히 실행할 수 있을 것이지만).  그것은 어떤것도 하지 않는다.   아래는 maven-antrun-plugin에 대한 template이다. 
```xml
<project>
  [...]
  <build>
    <plugins>
      <plugin>
        <artifactId>maven-antrun-plugin</artifactId>
        <version>1.8</version>
        <executions>
          <execution>
            <phase> <!-- a lifecycle phase --> </phase>
            <configuration>
              <target>

                <!--
                  Place any Ant task here. You can add anything
                  you can add between <target> and </target> in a
                  build.xml.
                -->

              </target>
            </configuration>
            <goals>
              <goal>run</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
  [...]
</project>

```
더욱이, 당신은 각 lifecycle phase에  \<execution/\> section을 중복하고 새로운 phase를  명시해서  script를 추가할 수 있다.  

궁극적으로,  당신은   \<target/\> tag안에 몇몇 Ant \<target/\> 속성을 명시할 수 있을 것이다. 

```xml
[...]
<configuration>
  <target name="The name of the target"
    if="The name of the property that must be set in order for this task"
    unless="The name of the property that must NOT be set in order for this task"
    description="A short description of this target's function">

    <!--
      Place any Ant task here. You can add anything
      you can add between <target> and </target> in a
      build.xml.
    -->

  </target>
<configuration>
[...]
```

#### Additional source directories

만일 당신의 Ant tasks가 빌드에 덧 붙여질 필요가 있는  추가적인 source code를 생산한다면,  당신은 build-helper-maven-plugin을 사용할 수 있다. 

http://mojo.codehaus.org/build-helper-maven-plugin/



## Using Maven properties
Maven에서 유용한 모든 properties는 target configuaration에서 또한 유용하다. 
그렇지만, 당시는 ant task를 사용하여 외부의  Ant build script를 호출하기를 원할수도 있다.  이름 충돌을 피하기 위해서,  properties의 subset 만이 external Ant build에 전달된다. 
이것들은  POM의 properties section에 정의된 모든 속성등를 포함한다. 일반적으로 사용되는 Maven properties의 prefixed versions의 일부분도 포함된다. 
```shell
maven.project.groupId
maven.project.artifactId
maven.project.name
etc.
```

사용하기 원하는 Maven property 가 외부 파일에서 유용하지 않게하려면, 당신은 ant을 호출하기 전에 property를 재정의해야 할 것이다. 
```xml
<property name="maven.project.url" value="${project.url}"/>
<ant antfile="build.xml"/>
```

## Ant Expressions to Maven Expressions Mapping
몇몇 Ant Expressions은 Maven에서 각자의 대응 하는 것(respective counterparts)이 있다. 


