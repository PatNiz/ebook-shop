package org.example.paymentvalidationservice;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class EbookDeliveryServiceConfig {

    @Bean
    @LoadBalanced
    public RestClient.Builder getRestClient(){
        return RestClient.builder();
    }
    @Bean
    public EbookDeliveryClient createEbookDeliveryClient(){
        RestClient restClient =    getRestClient()
                .baseUrl("http://EBOOK-DELIVERY-SERVICE")
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(EbookDeliveryClient.class);
    }
}
