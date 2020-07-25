package io.doraemon.route;

import java.util.List;

public class HttpRouteTable {
	private List<HttpRouteRule> routeRules;

	public List<HttpRouteRule> getRouteRules() {
		return routeRules;
	}

	public void setRouteRules(List<HttpRouteRule> routeRules) {
		this.routeRules = routeRules;
	}

	public HttpRouteTable(List<HttpRouteRule> routeRules) {
		super();
		this.routeRules = routeRules;
	}

	@Override
	public String toString() {
		return "HttpRouteTable [routeRules=" + routeRules + "]";
	}

	public HttpRouteTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
