//package com.ssafy.market.global.config;
//
//import org.elasticsearch.client.Client;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.TransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//
//import java.net.InetAddress;
//
//@EnableElasticsearchRepositories
//@Configuration
//@ComponentScan(basePackages = {"com.ssafy.market.domain"})
//public class ElasticConfig {
//    @Value("${elasticsearch.host}")
//    private String host;
//
//    @Value("${elasticsearch.port}")
//    private int port;
//
//    @Value("${elasticsearch.cluster_name}")
//    private String clusterName;
//
//    @Bean
//    public Client client() throws Exception {
//        Settings settings = Settings.builder().put("cluster.name", clusterName).build();
//        TransportClient client = new PreBuiltTransportClient(settings);
//        client.addTransportAddress(new TransportAddress(InetAddress.getByName(host), port));
//        return client;
//    }
//
//    @Bean
//    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
//        return new ElasticsearchTemplate(client());
//    }
//}
