package com.springboot.blob;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@SpringBootApplication

public class SpringbootBlobRestApiApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlobRestApiApplication.class, args);
	}

	public static class Maptest {
		public static void main(String[] args) {
	//        TreeMap<String, String> map = new TreeMap<>();
			Map<String,String > map = new HashMap<>();
			map.put("Ravi", "Sourav");
			map.put("Atul", "Kumar");
			map.put("Swati", "verma");
			map.put("Swati", "vonvon");
			map.put("Amit", "Anand");
			map.put("Priya", "kumari");
			for (Map.Entry<String,String> entry : map.entrySet()){
			System.out.println("Key = " + entry.getKey() +
					", Value = " + entry.getValue());
		}
			map.forEach((k,v) -> System.out.println(k+" "+v));

			HashSet<Integer> hashSet = new HashSet<>();
			hashSet.add(2);
			HttpServlet httpServlet = new HttpServlet() {
				@Override
				protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					super.doTrace(req, resp);
				}
			};
	//        ConcurrentHashMap<Integer, Integer>
	}
	}
}
