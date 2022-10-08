package com.wittgroup.kyn.file;

import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.config.Configuration;
import io.imagekit.sdk.utils.Utils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class KynFileManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(KynFileManagementApplication.class, args);

	}

	@Bean
	ImageKit getImageKit(){
		ImageKit imageKit = ImageKit.getInstance();
		try {
			Configuration config = Utils.getSystemConfig(KynFileManagementApplication.class);
			imageKit.setConfig(config);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return imageKit;
	}

}
