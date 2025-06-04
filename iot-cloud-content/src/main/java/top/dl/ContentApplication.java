package top.dl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: minder
 * @createTime: 2025/04/25 9:22
 * @description:
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"top.dl.feign"})
public class ContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContentApplication.class, args);
    }
}
