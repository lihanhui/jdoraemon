package io.doraemon.route;

public class HttpRouteRule {
	private String serviceName;
	private String path;
	private Boolean stripPrefix;
	private String url;
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Boolean getStripPrefix() {
		return stripPrefix;
	}
	public void setStripPrefix(Boolean stripPrefix) {
		this.stripPrefix = stripPrefix;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public HttpRouteRule(String serviceName, String path, Boolean stripPrefix, String url) {
		super();
		this.serviceName = serviceName;
		this.path = path;
		this.stripPrefix = stripPrefix;
		this.url = url;
	}
	@Override
	public String toString() {
		return "HttpRouteRule [serviceName=" + serviceName + ", path=" + path + ", stripPrefix=" + stripPrefix
				+ ", url=" + url + "]";
	}
	
}
