package com.situ.crm.common;

import java.io.Serializable;
import java.util.List;

public class EasyUIDataGrideResult implements Serializable{
	private Integer total;
	private List<?> rows;
	public EasyUIDataGrideResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EasyUIDataGrideResult(Integer total, List<?> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	@Override
	public String toString() {
		return "EasyUIDataGrideResult [total=" + total + ", rows=" + rows + "]";
	}
	
}
