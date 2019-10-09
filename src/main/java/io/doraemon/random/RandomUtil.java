package io.doraemon.random;

import java.util.Random;

public class RandomUtil {
	public static Integer randomInt(int start, int end){//start inclusive, end inclusive
		if (start >= end) {
			throw new IllegalArgumentException("start must be greater than end");
		}
		Random r = new Random();
		return r.nextInt((end - start) + 1) + start;
	}
	public static String randomImageCode(int number){
		final String CODE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		String str = "";
		int len = CODE.length() - 1;
		double r;
		for (int i = 0; i < number; i++) {
			r = (Math.random()) * len;
			str = str + CODE.charAt((int) r);
		}
		return str;
	}
	public static String randomCode(int number, String code){
		String CODE = code;
		String str = "";
		int len = CODE.length() - 1;
		double r;
		for (int i = 0; i < number; i++) {
			r = (Math.random()) * len;
			str = str + CODE.charAt((int) r);
		}
		return str;
	}
	public static void main(String[] args){
		int a = 10, b = 11;
		randomInt(a, b);		
		System.out.println("b % a = " + (b % a) );
	}
}
