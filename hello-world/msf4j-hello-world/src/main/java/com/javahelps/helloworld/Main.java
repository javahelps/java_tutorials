package com.javahelps.helloworld;

import org.wso2.msf4j.MicroservicesRunner;

public class Main {
    public static void main(String[] args) {
        new MicroservicesRunner()
                .deploy(new HelloService())
                .start();
    }
}