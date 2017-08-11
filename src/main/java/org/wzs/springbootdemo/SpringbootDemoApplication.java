package org.wzs.springbootdemo;

import com.didispace.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSwagger2Doc
@SpringBootApplication
public class SpringbootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemoApplication.class, args);
	}

    // @Bean
    // public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        // return args -> {
        //
        //     System.out.println("Let's inspect the beans provided by Spring Boot:");
        //
        //     String[] beanNames = ctx.getBeanDefinitionNames();
        //     Arrays.sort(beanNames);
        //     for (String beanName : beanNames) {
        //         System.out.println(beanName);
        //     }
        //
        // };
    // }
}
