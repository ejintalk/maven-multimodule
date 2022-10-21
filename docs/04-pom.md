# POM 파일 
Maven은 project object model(POM)를 기반으로 한 툴로 POM 파일을 참고하여 프로젝트를 빌드한다. 따라서 POM 파일(XML)을 이해하는 것이 매우 중요하다.


| 요소 | 	설명 |
| ---- | ----|
| prerequisites |	필요한 메이븐 버전 설정 |	
| scm	 |	소스관리 시스템 정보 |	
| issueManagement	 |	이슈관리 시스템 정보 |	
| ciManagement	 |	자동 빌듣 CI tool 정보 |	
| repositories	 |	리파지터리 정보 |	
| distributionManagement |		리파지터리 배포 설정 |	
| properties |		프러퍼티 설정 |	
| dependencyManagement |		의존성 관리 |	
|  dependencies	 |	의존성들 |	
|  dependency	 |	의존성 |	
| build	빌드 설정 |	
|  extensions	 |	이 빌드에서만 사용하는 산출물의 목록. |	
|  pluginManagement	 |	상속받은 자식 POM들에서 사용가능 |	
|  plugins	 |	해당 POM에서만 사용하는 plugin 설정 |	
| reporting	 |	site 생성 phase에서 문서화 작업 설정. |	
| profiles |		특정 환경에서 빌드 설정 |	

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project>
  <modelVersion>4.0.0</modelVersion>
	<groupId>com.naonsoft.james</groupId>
	<artifactId>naonsoft-james</artifactId>
	<version>1.0-SNAPSHOT</version>
	<packaging>pom</packaging>
	<name>Naonsoft James Project</name>
  <description></description>
  <url>http://james.naonsoft.com/</url>
	<inceptionYear>2014</inceptionYear>
	<developer></developer>
	<prerequisites></prerequisites>
	<scm></scm>
	<issueManagement></issueManagement>
	<ciManagement></ciManagement>
	<repositories></repositories>
	<distributionManagement><distributionManagement>
	<properties></properties>
	<build>
	   <extensions></extensions>
	   <pluginManagement>
	   </pluginManagement>
	   <plugins>
	   </plugins>
	<reporting></reporting>
	<profiles>	</profiles>   
</project>
```