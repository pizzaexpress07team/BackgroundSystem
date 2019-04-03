package com.project.PizzaExpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

//@ComponentScan(basePackages={"com.project.PizzaExpress"}) // 扫描该包路径下的所有spring组件
//@EnableJpaRepositories("com.project.PizzaExpress.dao") // JPA扫描该包路径下的Repositories
//@EntityScan("com.project.PizzaExpress.entity") // 扫描实体类
//@EnableAutoConfiguration
@SpringBootApplication
@EnableScheduling
public class PizzaExpressApplication extends SpringBootServletInitializer{

//	@RequestMapping("/")
//	@ResponseBody
//	String home() {
//		return "Pizza Express for PDQ!";
//	}
//
//
//	@ResponseBody
//	String test() {
//
//		TestController tc = new TestController();
//		return tc.save();
//	}

	public static final int menuStateCode = 0;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PizzaExpressApplication.class, args);
	}

	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		return builder.sources(this.getClass());
	}
}
