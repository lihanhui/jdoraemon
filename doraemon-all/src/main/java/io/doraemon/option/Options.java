package io.doraemon.option;

import java.util.HashMap;
import java.util.Map;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import io.doraemon.logging.Logger;
import io.doraemon.logging.LoggerFactory;

public class Options {
	private static Logger logger = LoggerFactory.getLogger(Options.class);
	
	@Parameter(names = {"-h", "--help"}, description = "to print help ...")
	private Boolean help = false;
	
	@Parameter(names = {"-o", "--log"}, description = "log root path")
	private String logRoot = "./log";
	
	@Parameter(names = {"-l", "--lib"}, description = "library root path")
	private String libRoot = "./lib";
	
	@Parameter(names = {"-c", "--conf"}, description = "config root path")
	private String confRoot = "./etc/conf";
	
	@DynamicParameter(names = "-D", description = "dynamic options ...")
	private Map<String, String> dynamicOptions = new HashMap<>() ;
	
	public static Options parseCmdLine(Class<? extends Options> clazz, String ...args) {
		Options options = null;
		try {
			options = clazz.newInstance();
			JCommander.newBuilder().acceptUnknownOptions(true).addObject(options).build().parse(args);
		} catch (InstantiationException | IllegalAccessException e) {
			logger.warn("failed to parse command line ...", e);
			options = null;
		}
		
		return options;
	}
	public String getDynamicOpt(String opt) {
		return this.dynamicOptions.get(opt);
	}

	public String getLogRoot() {
		return logRoot;
	}

	public String getLibRoot() {
		return libRoot;
	}

	public String getConfRoot() {
		return confRoot;
	}
	public Boolean getHelp() {
		return help;
	}  
	
}
