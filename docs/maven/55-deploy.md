# 배포 


## settings.xml 
배포하기  $MAVEN_HOME/conf/settings.xml의 \<server\>에 id를 설정하고 username, password를 설정한다. 

```xml
<?xml version="1.0" encoding="UTF-8"?>

<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <localRepository>e:/repository</localRepository>
  <pluginGroups>
  </pluginGroups>
  <proxies>
  </proxies>
  <servers>
    <!-- 배포할 서버의 아이디, 인증 정보 설정 -->
    <server>
      <id>my-nexus3-release</id>
      <username>myname</username>
      <password>mypasswd</password>
    </server>
  </servers>
  <mirrors>
  </mirrors>
  <profiles>
  </profiles>
</settings>
```


## repository 추가 
pom.xml에 배포할 repository를 추가한다. settings.xml과 동일한 아이디를 사용한다. 
```xml
    <!-- 사내 maven repository에 deploy 하기 위한 설정 -->
    <distributionManagement>
        <repository>
            <id>my-nexus3-release</id>
            <name>my nexus3 maven repo </name>
            <url>https://mvn3.myrepos.com/repository/maven-releases/</url>
        </repository>
    </distributionManagement>
```    


이제 mvn deploy를 실행하면 된다. 

## 별도의 파일 추가로 배포하기 
proguard를 사용하여 기본적으로 생성되는 jar 파일 말고 proguard라는 classifier가 붙은 jar 파일을 배포하려고 하면 두가지 방식이 있다. 

### 명령어 방식 
mvn을 사용하여 배포할 때 다음과 같은 형식으로 사용한다. 자세한 사항은 [Deploy an artifact with classifier](https://maven.apache.org/plugins/maven-deploy-plugin/examples/deploying-with-classifiers.html)을 참고한다. 

```shell
mvn org.apache.maven.plugins:maven-deploy-plugin:3.0.0:deploy-file \
    -Durl=http://localhost:8081/repomanager/ \
    -DrepositoryId=some.id \
    -Dfile=path/to/artifact-name-1.0.jar \
    -DpomFile=path-to-your-pom.xml \
    -Dfiles=path/to/artifact-name-1.0-debug.jar,path/to/site.pdf \
    -Dclassifiers=debug,site \
      -Dtypes=jar,pdf
```                                                                            
### pom.xml 
proguard를 사용하여 기본적으로 생성되는 jar 파일 말고 proguard라는 classifier가 붙은 jar 파일을 배포하려고 할 때 다음과 같이 pom.xml을 수정한다. 

```xml

    <build>
        <plugins>
            <plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-deploy-plugin</artifactId>
				<executions>
					<execution>
						<id>deploy-file</id>
						<phase>deploy</phase>
						<goals>
							<goal>deploy-file</goal>
						</goals>
						<configuration>
                            <!-- 기본으로 빌드되는 jar 파일 말고, 따로 생성되는 jar 파일을 배포할 때 jar 파일명 -->
							<file>${project.build.directory}/jirepos-service-${project.version}-proguard.jar</file>
                            <!-- $MAVEN_HOMNE/conf/settings.xml의 아이디와 동일해야 함 --> 
							<repositoryId>my-nexus3-release</repositoryId>
                            <!-- deploy할 url --> 
							<url>https://mvn3.myrepos.com/repository/maven-releases/</url>
                            <!-- 이 모듈의 groupId와 artifactId -->
							<groupId>com.jirpos</groupId>
							<artifactId>jirepos-service</artifactId>
							<version>${project.version}</version>
							<packaging>jar</packaging>
                            <!-- 별도의 파일을 배포할 때 pom 파일이 중복되지 않도록 false로 설정 -->
							<generatePom>false</generatePom>
							<classifier>proguard</classifier>
						</configuration>
					</execution>
				</executions>
			</plugin>
        </plugins>
    </build>
    ```

