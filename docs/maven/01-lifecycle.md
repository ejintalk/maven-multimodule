# Lifecycle 

Maven에서는 **clean, build, site의 세가지 Lifecycle을 제공**하고 있다. Maven은 모든 빌드 단위에 대한 Lifecycle이 예약되어 있어서 개발자가 임으로 변경할 수 없다.


[Introduction to the Build Lifecycle](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)에서 자세한 내용을 참고한다. 


* **clean**
* **build** : 컴파일(compile), 테스트(test), 패키지(package), 배포(deploye) 등의 과정은 빌드 Lifecycle에 속한다
* **site** 

**각 Lifecycle은 순서를 갖는 단계(Phase)로 구성**된다. 에를 들어, default lifecycle은 다음의 phase로 구성되어 있다.

* **validate** : 프로젝트가 올바른지 검증한다. 필요한 경우 모든 정보가 있는지 확인한다.
* **compile** : 프로젝트의 소스코드를 컴파일한다.
* **test** : 프로젝트의 테스트 코드를 실행한다.
* **package** : 컴파일된 코드를 배포 가능한 형태로 패키지한다.
* **integration-test** : 패키지된 코드를 통합 테스트한다.
* **verify** : 통합 테스트 결과를 검증한다.
* **install** : 패키지를 로컬 저장소에 설치한다.
* **deploy** : 패키지를 원격 저장소에 배포한다.

## Comman Line Calls
mvn 인자로 phase를 선택하여 호출한다. jar 파일을 원한다면 다음과 같이 호출한다.
```bash
mvn package
```    

각 라이프사이클은 순서를 갖는 단계(phase)로 구성된다. 또한, 각 단계별로 기본적으로 실행되는 플러그인(plugin) 골(goal)이 정의되어 있어서 각 단계마다 알맞은 작업이 실행된다. 


**라이프사이클의 특정 단계를 실행하면, 그 단계의 앞에 위치한 모든 단계가 실행된다**. 예를 들어, test 단계를 실행하면 test 단계를 실행하기에 앞서 generate-sources 단계부터 test-compile 단계까지 각 단계를 순서대로 실행한다.

**각 단계가 실행될 때에는 각 단계에 묶인 골(goal)이 실행**된다. 플러그인을 직접 실행할 수도 잇다. mvn 명령어에 단계 대신 실행할 플러그인을 지정하면 된다.

```
mvn surfire:test
```


## Phase 
Phase는 Build Lifecycle의 각각의 단계를 의미한다. Phase는 특정 순서에 따라서 goal이 실행되도록 구조를 제공한다. Phase간에는 의존 관계가 있다. 예를 들어 package page가 수행되기 위해서는 이전 phase가 순서대로 수행된 다음에 실행된다


각각의 phase는 그 안에 0개 이상의 goal을 가질 수 있다. package Phase는 jar packaging과 같은 프로젝트의 jar 파일을 생성하는 것으로 알고 있지만 maven은 생명주기의 특정 phase가 실행이 되면 그 인전의 모든 phase를 순차적으로 모두 수행한다. 즉 mvn package를 실행할 때 maven은 package 단계까지 모든 Phase를 실행하고 생명주기 Phase를 거치는 과정에서 해당 Phase에 속한 모든 goal들을 실행한다





## Goal 
GOAL은 표준 GOAL이나 다른 GOAL과 함께 실행되며 빌드의 일부분을 차지하는 구성요소로써 메이븐에 있어서 **작업의 단위**이다. 소스코드를 컴파일하는 Compiler plugin의 compile goal 또는 단위테스트를 실행할 수 있는 Surefire Plugin의 test goal을 들 수 있다.


Maven으로 의미있는 작업을 하기 위해서는 Plugin을 다운 받아야 한다. 명령을 실행하면 자동으로 다운를 받는다. Maven의 대부분의 책임을 maven 생명주기에 영향을 주고 goal에 대한 접근을 제공하는 Plugin에 위힘하도록 되어 있다. Plugin은 여러개의 Goal을 가지고 있고 Goal은 Plugin에 포함되어 있는 명령이다. 즉 Plugin은 하나 이상의 Goal의 집합체이다.


## Phase와 Goal의 관계 

메이븐에서 제공하는 보든 기능은 플러그인 기반으로 동작한다. 메이븐에서 기본으로 제공하는 Phase를 실행하면 해당 Phase와 연결된 플러그인의 Goal이 실행된다. 각 phase는 0개이상의 goal과 바인드 되어 있으며, 대부분 0개나 1개의 Goal이 바인드 되어 있다.


**플러그인 실행방법**    
Maven에서 플러그인을 실행할 때 ‘플러그인이름:플러그인지원골’의 형식으로 실행할 기능을 선택할 수 있다. 예를들어 mvn compiler:compile은 ‘compiler’플러그인에서 ‘compile’ 기능(goal)을 실행한다는 것을 뜻한다.

```bash
mvn compiler:compile
```


