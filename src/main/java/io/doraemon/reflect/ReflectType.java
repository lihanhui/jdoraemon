package io.doraemon.reflect;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import io.doraemon.restful.ResultMsg;

public class ReflectType {
	public static <T> Type getType(){
		return new TypeToken<T>(){}.getType();
	}
}
