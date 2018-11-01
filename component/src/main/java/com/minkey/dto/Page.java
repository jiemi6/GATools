package com.minkey.dto;

import com.minkey.exception.SystemException;

import java.io.Serializable;
import java.util.List;

/**
 * 数据分页传输类， 可以用于协议传输，list必须实现Serializable接口
 * @author minkey
 * @param <E>
 *
 */
public class Page<E> implements Serializable{
	private static final long serialVersionUID = -2604733332467233750L;
		
	private static final int DEFAULT_PAGESIZE = 10;
	
	/**
	 * 当前分页数据
	 */
	private List<E> list;
	/**
	 * 每页个数
	 */
	private int pageSize = DEFAULT_PAGESIZE;
	/**
	 * 当前页
	 */
	private int page = 1;
	/**
	 * 总数
	 */
	private int total = 0;
	/**
	 * 构造函数，默认当前页为第一页
	 * @param page：当前页
	 * @param pageSize：每一页的大小
	 */
	public Page(int page ,int pageSize) {
		this.setPageSize(pageSize);
		this.setCurrentPage(page);
	}
	
	/**
	 * 构造函数，可指定当前页
	 * @param page：当前页
	 * @param pageSize：每一页的大小
	 * @param list：需要分页的数据List
	 */
	public Page(int page, int pageSize,List<E> list) {
		this.setData(list);
		this.setPageSize(pageSize);
		this.setCurrentPage(page);
	}
	
	public void setData(List<E> list) {
		this.list = list;
	}
	
	public void setTotal(int total) {
		if(total < 0){
			throw new SystemException("TotalNum must greater than 0");
		}
		this.total = total;
	}
	
	public void setCurrentPage(int page) {
		if(page <= 0){
			throw new SystemException("CurrentPage must be equal or greater than 0");
		}
		this.page = page;
	}
	
	private void setPageSize(int pageSize) {
		if(pageSize <= 0){
			throw new SystemException("PageSize must be equal or greater than 0");
		}
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return ((total % pageSize == 0) ? (total / pageSize) : (total / pageSize + 1));
	}
	
	public List<E> getList() {
		return list;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotal() {
		return total;
	}

	public int startNum(){
		return (this.page - 1 ) * this.pageSize;
	}

	@Override
	public String toString() {
		return "Page [list=" + list + ", pageSize=" + pageSize
				+ ", page=" + page + ", total=" + total
				+ "]";
	}

}
