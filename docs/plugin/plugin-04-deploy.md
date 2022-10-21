# Deploy plugin 

deploy plugin은  remote repository에 artifact를 추가하기 위해서  deploy phase에서 주로 쓰인다. 

대부분의 경우에, 이 mojo는 당신이 default build  lifecycle의 deploy phase를 호출할 때 적용된다. 

이 mojo가 동작하게 하려면 , 유효한 ]<distributionManagement]> secion POM을 포함해야 한다. 당신의 repository가 secured 되었다면, 당신은 또한 당신의 settings.xml 파일에  대응하는 ]<server]> server entries를 정의해야 한다. 

```xml
[...]
  <distributionManagement>
    <repository>
      <id>internal.repo</id>
      <name>MyCo Internal Repository</name>
      <url>Host to Company Repository</url>
    </repository>
  </distributionManagement>
[...]
```
