package com.project.PizzaExpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

//@ComponentScan(basePackages={"me.shijunjie"}) // 扫描该包路径下的所有spring组件
/*@EnableJpaRepositories("me.shijunjie.dao") // JPA扫描该包路径下的Repositorie
 *//*@EntityScan("me.shijunjie.entity") // 扫描实体类
 */@SpringBootApplication
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

	public static void main(String[] args) throws Exception {

		SpringApplication.run(PizzaExpressApplication.class, args);
	}

}
