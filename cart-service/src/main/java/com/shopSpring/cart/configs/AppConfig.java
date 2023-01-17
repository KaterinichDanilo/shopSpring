package com.shopSpring.cart.configs;

import com.shopSpring.cart.properties.ProductServiceIntegrationProperties;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan("com.shopSpring.cart")
@EnableConfigurationProperties(ProductServiceIntegrationProperties.class)
@RequiredArgsConstructor
public class AppConfig {
    private final ProductServiceIntegrationProperties productServiceIntegrationProperties;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public WebClient cartServiceWebClient(){
        TcpClient tcpClient = TcpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, productServiceIntegrationProperties.getConnectTimeout())
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(productServiceIntegrationProperties.getReadTimeout(), TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new ReadTimeoutHandler(productServiceIntegrationProperties.getWriteTimeout(), TimeUnit.MILLISECONDS));
                });
        return WebClient.builder()
                .baseUrl(productServiceIntegrationProperties.getUrl())
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();
    }
}
