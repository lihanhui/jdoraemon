package io.doraemon.conf;

import java.io.File;

import io.doraemon.json.JsonUtil;

public class Config {
	private String confPath = "/etc/conf";
	private static Config config = null;
	public static Config getConfig() {
		return config;
	}
	public static Config init(String confPath) {
		if(config == null) {
			config = new Config(confPath);
		}
		return config;
	}
	private Config(String confPath) {
		super();
		if(confPath != null) this.confPath = confPath;
	}

	public <T> T readConf(String confFile, Class<? extends T> clazz) {
		return JsonUtil.fromJsonFile(this.confPath + File.separator + confFile, clazz);
	}
}
