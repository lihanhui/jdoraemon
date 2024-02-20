package io.doraemon.yaml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import io.doraemon.bean.ExampleBean;
import io.doraemon.exception.DoraemonRuntimeException;

public class YamlUtil {

	public static <T> T fromYaml(String yamlStr, Class<T> clz){
		InputStream inputStream = new ByteArrayInputStream(yamlStr.getBytes());
		Yaml yaml = new Yaml(new Constructor(clz, new LoaderOptions()));
		return yaml.load(inputStream);
	}
	
	public static <T> T fromYamlFile(String yamlFile, Class<T> clz){
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(new File(yamlFile));
			Yaml yaml = new Yaml(new Constructor(clz, new LoaderOptions()));
			return yaml.load(inputStream);
		} catch (FileNotFoundException e) {
			throw new DoraemonRuntimeException(e);
		}
	}
	public static <T> String toYaml(T obj){
		StringWriter writer = new StringWriter();
		Yaml yaml = new Yaml();
		yaml.dump(obj, writer);
		return writer.getBuffer().toString();
	}
	
	public static <T> void toYaml(String yamlFile, T obj){
		PrintWriter writer;
		try {
			File file = new File(yamlFile);
			file.createNewFile();
			writer = new PrintWriter(file);
			Yaml yaml = new Yaml();
			yaml.dump(obj, writer);
		} catch (IOException e) {
			throw new DoraemonRuntimeException(e);
		}
	}
	public static void main(String[] args) {
		String bean = "\r\n{a: bbbb}";
		ExampleBean b = YamlUtil.fromYaml(bean, ExampleBean.class);
		
		String c = YamlUtil.toYaml(b);
		
		b = YamlUtil.fromYaml(c, ExampleBean.class);
		System.out.println(b);
		
		YamlUtil.toYaml("/Users/lihanhui/demo.yaml", b);
		b = YamlUtil.fromYamlFile("/Users/lihanhui/demo.yaml", ExampleBean.class);
		System.out.println(b);
	}
}
