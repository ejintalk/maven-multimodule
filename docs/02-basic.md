# 기본 사용법 

## 프로젝트 생성 
자바프로젝트를 생성하려면 다음을 참고로 하여 타이핑 한다.
```bash
mvn archetype:generate -DarchetypeGroupId=
                     -DarchetypeArtifactId=
                     -DarchetypeVersion=1.0
                     -DgroupId=
                     -DartifactId=
```                     
예를 들어 아래와 같이 작성한다.
```bash
mvn archetype:generate  -B -DarchetypeGroupId=org.apache.maven.archetypes 
       -DarchetypeArtifactId=maven-archetype-quickstart 
       -DarchetypeVersion=1.0 
       -DgroupId=org.melody.framework  
       -DartifactId=org.melody.framework 
       -Dversion=1.0
```       

웹프로젝트는 다음을 참고한다. 
```bash
 mvn archetype:generate 
            -DgroupId=org.melody 
            -DartifactId=org.melody 
            -DarchetypeArtifactId=maven-archetype-webapp
```
```bash
mvn archetype:generate 
            -DgroupId=edu.web
            -DartifactId=edu.web
            -DarchetypeArtifactId=maven-archetype-webapp
```                        

## Eclipse 프로젝트 전환 

메이븐을 이용하여 생성한 메이븐 프로젝트를 이클립스에서 사용하기 위하여 이클립스 프로젝트로 변환하려면 다음을 입력한다.
```bash
mvn eclipse:eclipse
```


## Third party 인스톨 
메이븐의 원격레파지토리에서 제공하지 않는 라이브러리를 자신의 로컬 리파지토리에 추가할 필요가 있는 경우에 다음의 문법을 사용하여 추가한다.

```bash
mvn install:install-file
  -Dfile=<path-to-file>
  -DgroupId=<group-id>
  -DartifactId=<artifact-id>
  -Dversion=<version>
  -Dpackaging=<packaging>
  -DgeneratePom=true        
```
```bash
mvn install:install-file 
        -Dfile=tomato_jef_adapt_pojo-20090107.jar             
        -DgroupId=tomato 
        -DartifactId=tomato_jef_adapt_pojo 
        -Dversion=20090107 
        -Dpackaging=jar 
        -DgeneratePom=true
```

## 이것 저것 
```bash
mv test  # 테스트코드 컴파일 실행 
mvn test-compile   # 테스트 코드 컴파일 
mvn package # 패키지 생성 
mvn install # 로컬 리파지토리에 설치
```
Main class를 다음과 같이 실행한다. 
```bash
mvn exec:java –Dexec.mainClass=com.mycompany.com.Sample –Dexec.args=”hello”
```
Test Pacakge 제외하기 
```bash
mvn install –Dmode=T –Dmaven.test.skip=true
```


