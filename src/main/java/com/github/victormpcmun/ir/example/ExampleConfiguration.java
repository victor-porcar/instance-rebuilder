package com.github.victormpcmun.ir.example;

import com.github.victormpcmun.ir.instancerebuilder.InstanceRebuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExampleConfiguration {

    int connectionId=0;
    @Bean
    public InstanceRebuilder<Connection> instanceRebuilderForConnection() {
        return InstanceRebuilder.build(this::buildConnection);
    }

    @Bean
    public Connection  connection() {
        InstanceRebuilder<Connection> ir = instanceRebuilderForConnection();
        return ir.getInstanceProxy();
    }

    private Connection buildConnection() {
        return new Connection(connectionId++);
    }

}
