package com.abtesttool;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configurable
@ComponentScan(basePackages = "com.abtesttool")
@EnableAspectJAutoProxy(proxyTargetClass = false, exposeProxy = true)
public class ConfigTest {
}
