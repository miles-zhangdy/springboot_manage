package com.zdy.util;

import java.util.List;

public class BaseList<B> {
	
	private List<B> list;
	
	private int curPage;
	
	private int pageSize;
	
	private long totalPage;
	
	private long totalRows;


	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}

	public List<B> getList() {
		return list;
	}

	public void setList(List<B> list) {
		this.list = list;
	}
	
}
