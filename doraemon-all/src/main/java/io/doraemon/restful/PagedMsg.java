package io.doraemon.restful;

import java.util.List;

public class PagedMsg<T> {
	Long total;
	Integer pageNumber;
	Integer pageSize;
	List<T> elements;
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getElements() {
		return elements;
	}
	public void setElements(List<T> elements) {
		this.elements = elements;
	}
	public PagedMsg(Long total, Integer pageNumber, Integer pageSize, List<T> elements) {
		super();
		this.total = total;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.elements = elements;
	}
	@Override
	public String toString() {
		return "PagedMsg [total=" + total + ", pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", elements="
				+ elements + "]";
	}
	public PagedMsg() {
		super();
	}
}
