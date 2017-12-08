package com.zdy.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Page {
	   // 当前页码
    private Integer page;
    // 最大页码数
    private Integer maxPage;
    // 总记录数
    private Integer totalCount;
    // 查询开始条数,翻页的开始条数位置
    private Integer beginIndex;
    // 每页的条数
    private Integer pageSize;
    // 传给前台的数据列表数据
    private List<?> data;
    // 传给前台的数据单个数据
    private Object info;
    // 从前台传入后台的查询参数
    private Map<String, Object> parm = new HashMap<String, Object>();
    // 返回码
    private boolean success;
    // 返回错误或成功的消息
    private String msg;
    
    private String sortName;
    
    private String order;
    
    public Page(){}
    
    public Page(int count, int pagesize){
    	this.maxPage = count % pagesize == 0 ? count / pagesize : count / pagesize + 1;
    }
    
	public Integer getPage() {
		return page == null ? 1 : page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(Integer maxPage) {
		this.maxPage = maxPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getBeginIndex() {
		return beginIndex;
	}
	public void setBeginIndex(Integer beginIndex) {
		this.beginIndex = beginIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
	public Object getInfo() {
		return info;
	}
	public void setInfo(Object info) {
		this.info = info;
	}
	public Map<String, Object> getParm() {
		return parm;
	}
	public void setParm(Map<String, Object> parm) {
		this.parm = parm;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
    
    
    
}
