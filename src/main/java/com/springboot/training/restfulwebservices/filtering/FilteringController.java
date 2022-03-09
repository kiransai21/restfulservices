package com.springboot.training.restfulwebservices.filtering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public SomeBean retrieveSomeBean(){
		return new SomeBean("Value1", "Value2", "Value3");
	}
	
	@GetMapping("/filtering-list")
	public List<SomeBean> retrieveSomeBeans(){
		return Arrays.asList(new SomeBean("v1", "v2", "v3"), new SomeBean("v4", "v5", "v6"));
	}
	
	//==========================dynamic filtering=======================
	
	@GetMapping("/dynamic-filtering")
	public MappingJacksonValue retrieveSomeBeanDynamic(){
		SomeBean someBean = new SomeBean("Value1", "Value2", "Value3");
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mapping.setFilters(filters);
		return mapping;
	}
	
	@GetMapping("/dynamic-filtering-list")
	public MappingJacksonValue retrieveSomeBeansDynamicList(){
		 List<SomeBean> asList = Arrays.asList(new SomeBean("v1", "v2", "v3"), new SomeBean("v4", "v5", "v6"));
		 MappingJacksonValue mapping = new MappingJacksonValue(asList);
		 SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		 FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		 mapping.setFilters(filters);
		 return mapping;
	}
	
}
