package io.doraemon.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import io.doraemon.logging.Logger;
import io.doraemon.logging.LoggerFactory;

public class Process {
	private static Logger logger = LoggerFactory.getLogger(Process.class);
	public static boolean run(List<String> cmds) {
		ProcessBuilder processBuilder = new ProcessBuilder(cmds);
		return run(processBuilder);
	}
	
	public static boolean run(String[] cmds) {
		ProcessBuilder processBuilder = new ProcessBuilder(cmds);
		return run(processBuilder);
	}
	
	private static boolean run(ProcessBuilder processBuilder) {
		processBuilder.redirectErrorStream(true);
		try {
			java.lang.Process proc = processBuilder.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = null;
			while((line = reader.readLine()) != null) {
				logger.debug(line);
			}
			reader.close();
			int exitVal = proc.waitFor();
			if(exitVal != 0) {
				logger.info("failed to run command with exit value {}", exitVal);
				return false;
			}
			return true;
		} catch (IOException | InterruptedException e) {
			logger.warn("exception when runnind command {}", e);
			return false;
		}
	}
	
	public static Integer pid() {
		String name = ManagementFactory.getRuntimeMXBean().getName();
		int index = name.indexOf("@");
		if(index < 0){
			return null;
		}
		return Integer.valueOf(name.substring(0, index));
	}
	
	public static void createPidFile(String fileName) {
		try {
			List<String> lines = Arrays.asList(String.valueOf(pid()));
			Path file = Paths.get(fileName);
			Files.write(file, lines, Charset.forName("UTF-8"));
			
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){
				@Override
				public void run() {
					try {
						Files.delete(Paths.get(fileName));
					} catch (IOException e) {
						logger.warn("failed to delete pid file from "+fileName, e);
					}
					
				}}));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
