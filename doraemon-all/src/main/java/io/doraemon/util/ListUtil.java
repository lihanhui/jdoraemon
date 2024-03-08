package io.doraemon.util;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;

public class ListUtil {

	public static <T> List<T> fromArray(T[] array){
        List<T> list = new LinkedList<>();
        for(T t: array) {
        	list.add(t);
        }
        return list;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] toArray(List<T> list){
		T[] array = (T[])Array.newInstance(list.getClass().getComponentType(), list.size());;
        int i = 0;
        for(T t: list) {
        	array[i++] = t;
        }
        return array;
	}
}
