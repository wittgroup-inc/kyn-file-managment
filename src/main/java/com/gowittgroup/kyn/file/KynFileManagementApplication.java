package com.gowittgroup.kyn.file;

import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.config.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KynFileManagementApplication {

    @Value("${imageKit.publicKey}")
    private String publicKey;

    @Value("${imageKit.privateKey}")
    private String privateKey;

    @Value("${imageKit.urlEndpoint}")
    private String urlEndPoint;

    public static void main(String[] args) {
        SpringApplication.run(KynFileManagementApplication.class, args);

    }

    @Bean
    ImageKit getImageKit() {
        ImageKit imageKit = ImageKit.getInstance();
        Configuration config = new Configuration(publicKey, privateKey, urlEndPoint);
        imageKit.setConfig(config);
        return imageKit;
    }

}
