package com.bata.billpunch;

import java.util.function.Function;

public class Test5 {
	
	public static void main(String[] args) {
		
		Function<String, Integer> f=s->s.length();
		Integer x=f.apply("janmenjaya");
		System.out.println(x);
	}

}
