package io.doraemon.geo;

public class Location {
	private float lng;  // 经度
	private float lat;  // 纬度
	private float ele;  // 海拔
	public float getLng() {
		return lng;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getEle() {
		return ele;
	}
	public void setEle(float ele) {
		this.ele = ele;
	}
	@Override
	public String toString() {
		return "Location [lng=" + lng + ", lat=" + lat + ", ele=" + ele + "]";
	}
	public Location(float lng, float lat, float ele) {
		super();
		this.lng = lng;
		this.lat = lat;
		this.ele = ele;
	}
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
