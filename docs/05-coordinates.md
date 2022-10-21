# Maven Coordinates 

### 	modelVersion
4.0.0으로 설정. pom의 버전

### grouId
프로젝트를 생성하는 조직의 고유 아이디를 결정한다. 일반적으로 도메인 이름을 사용한다.  com.naon 도메인 이름을 사용하면 com/naon 구조로 폴더(디렉토리)가 만들어 진다.
### artifactId
프로젝트를 식별하는 유일한 아이디를 의미한다.  프로젝트를 빌드하면 결과물-산출물이라고도 함-이 생기는데 war로 묶인 파일일 수도 있고 jar 묶인 파일일 수도 있다.  이 산출물의 이름과 버전명을  붙여서 산출물명이 결정이 된다. artifactId가 test이고 version이 0.01-SNAPSHOT이면 산출물의 이름은 test-0.01-SNAPSHOT.war가 된다. 확장자는 packaging에 설정된 값이 붙여진다. grouId가 com.naon이면 com/naon 디렉토리 아래에 test라는 폴더가 생성되고 그아래의 0.01-SNAPSHOT 디렉토리가 만들어 진다.  그 아래에 test-01.01-SNAPSHOT.war 파일이 놓이게 된다.  

### version
지금 배포되고 있는 산출물의 버전을 명시한다.

### packaging
해당 프로젝트를 어떤 형태로 packaging 할 것인지를 결정한다.  jar, war, ear 등이 해당된다.

* jar – jar 파일로 패키징
* war – war 파일로 패키징
* ear – ear 파일로 패키징
* pom – 상속(inheritance)과 집합(aggregation)-multi module-프로젝트들은 pom으로 설정.



### name
프로젝트의 이름을 기입한다.
### description
프로젝트의 설명을 기입한다.
### url
프로젝트의 URL을 기입한다.
### inceptionYear
프로젝트의 시작년도를 기입한다.
### developers
개발자 정보를 기입한다.

```xml
<developers>
		<developer>
			<id>jhonson</id>
			<name>Jhonson Choi</name>
			<email>jhonsonchoi@gmail.com</email>
			<timezone>9</timezone>
			<roles>
				<role>Developer</role>
				<role>PMC Member</role>
			</roles>
		</developer>
</developers>
```


### prerequisites
Maven 버전을 기입한다.
```xml
<prerequisites>
		<maven>3.0.2</maven>
</prerequisites>
```