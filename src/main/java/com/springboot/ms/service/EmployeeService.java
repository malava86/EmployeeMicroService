package com.springboot.ms.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springboot.ms.model.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	@Lazy
	RestTemplate restTemplate;
	
	@Bean
	RestTemplate getRestTemplate() {
		
		restTemplate = new RestTemplate();
		
	  return restTemplate;
	}
	
	public Employee getUserDetailsByFirstName(String firstName){
		
		LinkedHashMap<String,Object> usersList =  (LinkedHashMap<String,Object>) restTemplate.getForObject("https://reqres.in/api/users/",LinkedHashMap.class);
		
		Employee employee = null;
		
		for (Entry<String, Object> entry : usersList.entrySet()) {
			
			String key = entry.getKey();
			
			if(key.equalsIgnoreCase("data")) {
				ArrayList<Object> val = (ArrayList<Object>) entry.getValue();
				
				 for (Iterator iterator = val.iterator(); iterator.hasNext();) {
					Object object = (Object) iterator.next();
					LinkedHashMap<String,String> map = (LinkedHashMap<String,String>)object;
					
					employee = new Employee();
					
					for (Map.Entry<String, String> e : map.entrySet()) {
						String k = e.getKey();
						String v = String.valueOf(e.getValue());
						
						if(k.equalsIgnoreCase("id")) {
							employee.setId(Integer.parseInt(v));
						}else if(k.equalsIgnoreCase("email")) {
							employee.setEmail(v);
						}else if(k.equalsIgnoreCase("first_name")) {
							employee.setFirst_name(v);
						}else if(k.equalsIgnoreCase("last_name")) {
							employee.setLast_name(v);
						}else if(k.equalsIgnoreCase("avatar")) {
							employee.setAvatar(v);
						}
												
					}// end inner for..loop
					
					if(employee.getFirst_name().contains(firstName)) {
						return employee;
					}
										
				}
					
			}
			
			
		}
		
		return employee;
		
	}
	

}
