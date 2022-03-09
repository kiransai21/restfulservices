package com.springboot.training.restfulwebservices.helloworld;

public class HelloWorldBean {
	
	private String messgae;
	
	public HelloWorldBean(String message){
		this.messgae = message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [messgae=" + messgae + "]";
	}

	public String getMessgae() {
		return messgae;
	}

	public void setMessgae(String messgae) {
		this.messgae = messgae;
	}

}
