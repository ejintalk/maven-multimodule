# Dependency Plugin 


http://maven.apache.org/plugins/maven-dependency-plugin/

dependency plugin은 artifacts를 다루는 능력을 제공한다. 특정 artifacts을 카피하면서,  depenbdency:copy mojo를 lifecycle에 bind할 필요가 있다. 당신이 복사(copy)을 원하는 artifact을열 명시하고 plugin을 구성해라.


```xml
<project>
  [...]
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-dependency-plugin</artifactId>
        <version>3.0.0</version>
        <executions>
          <execution>
            <id>copy</id>
            <phase>package</phase>
            <goals>
              <goal>copy</goal>
            </goals>
          </execution>
        </executions>
        <configuration>
          <artifactItems>
            <artifactItem>
              <groupId>junit</groupId>
              <artifactId>junit</artifactId>
              <version>3.8.1</version>
              <type>jar</type>
              <overWrite>false</overWrite>
              <outputDirectory>${project.build.directory}/alternateLocation</outputDirectory>
              <destFileName>optional-new-name.jar</destFileName>
            </artifactItem>
          </artifactItems>
          <outputDirectory>${project.build.directory}/wars</outputDirectory>
          <overWriteReleases>false</overWriteReleases>
          <overWriteSnapshots>true</overWriteSnapshots>
        </configuration>
      </plugin>
    </plugins>
  </build>
  [...]
</project>
```
dependency:copy goal은 또한 막 생성된 artifact를 원한다면 custom 위치에 복사할 수 있다. 그것은 package phage 뒤에 어떤 phase와 바운드되어야 한다.  아래는 어떻게 하는지 보여준다(이 경우에는 install phase와 바인드되었다.)

```xml
<project>
  [...]
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-dependency-plugin</artifactId>
        <version>3.0.0</version>
        <executions>
          <execution>
            <id>copy-installed</id>
            <phase>install</phase>
            <goals>
              <goal>copy</goal>
            </goals>
            <configuration>
              <artifactItems>
                <artifactItem>
                  <groupId>${project.groupId}</groupId>
                  <artifactId>${project.artifactId}</artifactId>
                  <version>${project.version}</version>
                  <type>${project.packaging}</type>
                </artifactItem>
              </artifactItems>
              <outputDirectory>some-other-place</outputDirectory>
            </configuration>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
  [...]
</project>
```
