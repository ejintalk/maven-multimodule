# Properties


Maven의 properties는 값의 placeholder이다. Ant에서 properties와 비슷하다. 이것들의 값들은 POM에서 어디서든 접근이 가능하다. ${X}와 같은 표기법을 사용한다. X는 프로퍼티이다.
다섯가지 다른 스타일이 있다.

* **env.X**: env가 앞에 붙은 변수는 shell의 실행환경의 변수를 반환한다. 예를들어, ${env.PATH}는 PATH 환경 변수 PATH를 포함한다. 주의: 윈도우에서는 대소문자에 민감하다. 모두 대문자로 사용하도록 한다.
* **projext.x**: POM에서 dot(.) 표기된 경로는 상응하는 요소의 값이다. 예를들어 , 1.0는 ${project.version}을 경유하여 접근할 수 있다.
* **settings.x**: settings.xml에서 dot(.) 표기된 경로는 상응하는 요소의 값을 포함한다. 예를들어, false는 ${settings.offline}을 경유하여 접근할 수 있다.
* **Java System Properties**: 모든 프러퍼티들은 java.lang.System.getProperties() 통해 접근할 수 있는데, POM의 프러퍼티와 마찬가지로 유용하다. ${java.home}과 같은 식으로 사용한다.
* **x**: POM의 요소 내부에 설정하는데, value는 ${somVar}로 사용될 수있다.



## Project 속성 

* ${project.build.directory} : 'target' 디렉토리. 이 속성은 ${pom.build.directory}와 같다

* ${project.build.outputDirectory} : 'target/classes' 디렉토리.

* ${project.name}, ${pom.name} : 프로젝트 이름. \<project\>/\<name\> 엘리먼트 설정 값

* ${project.version}, ​${pom.version} : 프로젝트 버전. 버전 정보는 접두사를 사용하지 않고 ​${version}으로도 접근 가능하다. \<project\>/\<version\> 엘리먼트 설정 값

* ${project.build.finalName} : package 페이즈를 실행해 압축된 최종 파일 이름. \<project\>/ \<build\>/\<finalName\>엘리먼트 설정값




## System.getProperty() 


| Java System Properties        | 설명                  |
|-------------------------------|---------------------|
| java.version                  | JRE의 버전             |
| java.vendor                   | JRE의 vendor 정보      |
| java.home                     | JDK 또는 JRE의 설치 디렉토리 |
| java.vm.specification.version | JVM SPEC 버전         |
| java.vm.specification.name    | JVM SPEC 이름         |
| os.name                       | OS 이름               |
| os.version                    | OS 버전               |
| user.home                     | 사용자 홈 디렉토리          |
| user.name                     | 사용자 이름              |
| user.dir                      | 현재 디렉토리             |


# env 
메이븐은 env 접두사를 사용하여 시스템 환경 변수 값을 참조할 수 있다.

* {env.PATH} : 시스템의 PATH 설정 값을 참조
* {env.JAVA_HOME} : 시스템의 JAVA_HOME 설정값 참조


