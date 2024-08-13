package com.example.sampleapp;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages = "com.example.sampleapp")
public class Configuration {

}
