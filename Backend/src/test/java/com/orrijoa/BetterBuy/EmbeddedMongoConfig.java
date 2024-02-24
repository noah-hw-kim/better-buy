//package com.orrijoa.BetterBuy;
//
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//
//import java.io.IOException;
//
//
//@Configuration
//@Profile("test")
//public class EmbeddedMongoConfig {
//    @Value("${spring.data.mongodb.database}")
//    private String databaseName;
//
//    @Value("${spring.data.mongodb.host}")
//    private String mongoHost;
//
//    @Value("${spring.data.mongodb.port}")
//    private int mongoPort;
//
//    @Bean(destroyMethod = "close")
//    public MongoClient mongoClient() throws IOException {
//        return MongoClients.create(String.format("mongodb://%s:%d", mongoHost, mongoPort));
//    }
//}
