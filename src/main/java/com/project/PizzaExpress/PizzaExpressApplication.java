package com.project.PizzaExpress;

import com.project.PizzaExpress.Datasource.DataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.Connection;

@Controller
@EnableAutoConfiguration
public class PizzaExpressApplication {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Pizza Express for PDQ!";
	}

	@RequestMapping("/test")
	@ResponseBody
	String test() {
		return "Test!";
	}

	public static void main(String[] args) throws Exception {
		DataSourceConfig dsc = new DataSourceConfig();
		DataSource ds = dsc.dataSource();
		//Connection c = ds.getConnection();
		//System.out.println(c);
//		System.out.println(dataSourceProperties);

		SpringApplication.run(PizzaExpressApplication.class, args);
	}

}
