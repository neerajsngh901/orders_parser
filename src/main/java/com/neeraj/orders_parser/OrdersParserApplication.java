package com.neeraj.orders_parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@SpringBootApplication
public class OrdersParserApplication {
	public static void main(String[] args)  {

		ApplicationContext applicationContext = SpringApplication.run(OrdersParserApplication.class, args);
		OderService service = applicationContext.getBean(OderService.class);
		System.out.println(service.getOderJson());
		}


}
