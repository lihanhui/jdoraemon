package io.doraemon.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import io.doraemon.annotation.Extensible;
import io.doraemon.logging.Logger;
import io.doraemon.logging.LoggerFactory;

public class ExtensionLoader {
	private static Logger logger = LoggerFactory.getLogger(ExtensionLoader.class);
	private static <T extends Annotation> T annotation(Class<?> clazz, Class<T> annotation) {
		T anno = clazz.getAnnotation(annotation);
		return anno != null ? anno: null;
	}
	public static List<Object> load(String jarFile, Class<Extensible> annotation) {
		List<Object> extensions = new ArrayList<>();
		URLClassLoader loader = (URLClassLoader)ExtensionLoader.class.getClassLoader();
		File jar = new File(jarFile);
		try {
			String realPath = jar.getAbsolutePath();
			URL url = new URL("file:"+realPath);
			Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			method.setAccessible(true);
			method.invoke(loader, url);
			
			List<String> classNames = new ArrayList<>();
			ZipInputStream zip = new ZipInputStream(new FileInputStream(realPath));
			for(ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
				if(!entry.isDirectory() && entry.getName().endsWith(".class")) {
					String className = entry.getName().replace('/', '.');
					className = className.substring(0, className.length() - ".class".length());
					classNames.add(className);
				}
			}
			zip.close();
			
			for(String className: classNames) {
				Class<?> clazz = loader.loadClass(className);
				if(annotation(clazz, annotation) != null) {
					extensions.add(clazz.newInstance());
				}
			}
		} catch (  NoSuchMethodException 
				| SecurityException | IllegalAccessException 
				| IllegalArgumentException | InvocationTargetException 
			    | IOException | ClassNotFoundException | InstantiationException e) {
			logger.warn("failed to load extension", e);
		}
		return extensions;
	}
	public static void main(String[] args) {
		load("/Users/lihanhui/eclipse-workspace/nezha-all/target/nezha-all-0.0.1-SNAPSHOT.jar", Extensible.class);
	}
}
