package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.example.zoo.IVetClinic;
import org.example.zoo.VetClinic;
import org.example.zoo.Zoo;
@Configuration
public class AppConfig {

    @Bean
    public IVetClinic vetClinic() {
        return new VetClinic();
    }

    @Bean
    public Zoo zoo(IVetClinic vetClinic) {
        return new Zoo(vetClinic);
    }
}
