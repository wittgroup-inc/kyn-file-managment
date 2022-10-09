package com.wittgroup.kyn.file;

import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.config.Configuration;
import io.imagekit.sdk.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

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
