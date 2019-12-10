package com.example.networking;

import com.example.networking.model.ServerModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = SocketServer.class)
public class Config {
    @Bean
    public ServerModel getModel() {
        return new ServerModel();
    }
}