package io.doraemon.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class JsonUtil {
	public static <T> T fromJson(String json, Class<T> clz){
		Gson gson = new Gson();
		return gson.fromJson(json, clz);
	}
	
	public static <T> T fromJson(String json, Type type){
		Gson gson = new Gson();
		return gson.fromJson(json, type);
	}
	
	public static <T> T fromJsonFile(String jsonFile, Class<T> clz){
		Gson gson = new Gson();
		try (FileReader r = new FileReader(jsonFile)) {
			return  gson.fromJson(r, clz);
		} catch (JsonSyntaxException | JsonIOException| IOException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	public static <T> String toJson(T obj){
		Gson gson = new Gson();
		return gson.toJson(obj);
	}
	
	public static <T> void toJson(String jsonFile, T obj){
		Gson gson = new Gson();
		try (FileWriter writer = new FileWriter(jsonFile)) {
			gson.toJson(obj, writer);
			writer.close();
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
		}
	}
}
