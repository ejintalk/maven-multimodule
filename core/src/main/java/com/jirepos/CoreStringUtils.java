package com.jirepos;


import com.naon.mvn.RespositoryString;

public class CoreStringUtils {
    // make append
    public static String append(String str, String appendStr) {
        return str + appendStr;
    }
    public static void print(){
        // 부모 pom.xml에서 의존성을 추가해서 이 모듈에서는 의존성을 추가할 필요가 없다.
        System.out.println(RespositoryString.versionString());
    }
}
