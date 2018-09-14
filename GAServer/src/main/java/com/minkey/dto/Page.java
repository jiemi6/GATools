package com.minkey.dto;

import com.minkey.exception.SystemException;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 数据分页传输类， 可以用于协议传输，data必须实现Serializable接口
 * @author minkey
 * @param <E>
 *
 */
public class Page<E extends Serializable> implements Serializable{
	private static final long serialVersionUID = -2604733332467233750L;
		
	private static final int DEFAULT_PAGESIZE = 10;
	
	/**
	 * 当前分页数据
	 */
	private List<E> data;
	/**
	 * 每页个数
	 */
	private int pageSize = DEFAULT_PAGESIZE;
	/**
	 * 当前页
	 */
	private int currentPage = 1;
	/**
	 * 总数
	 */
	private int totalNum = 0;
	/**
	 * 构造函数，默认当前页为第一页
	 * @param currentPage：当前页
	 * @param pageSize：每一页的大小
	 */
	public Page(int currentPage ,int pageSize) {
		this.setPageSize(pageSize);
		this.setCurrentPage(currentPage);
	}
	
	/**
	 * 构造函数，可指定当前页
	 * @param currentPage：当前页
	 * @param pageSize：每一页的大小
	 * @param data：需要分页的数据List
	 */
	public Page(int currentPage, int pageSize,List<E> data) {
		this.setData(data);
		this.setPageSize(pageSize);
		this.setCurrentPage(currentPage);
	}
	
	public void setData(List<E> data) {
		if(CollectionUtils.isEmpty(data)){
			this.totalNum = 0;
		}else{
			this.totalNum = data.size();
			this.data = data;
		}
	}
	
	public void setTotalNum(int totalNum) {
		if(totalNum < 0){
			throw new SystemException("TotalNum must greater than 0");
		}
		this.totalNum = totalNum;
	}
	
	public void setCurrentPage(int currentPage) {
		if(currentPage <= 0){
			throw new SystemException("CurrentPage must be equal or greater than 0");
		}
		this.currentPage = currentPage;
	}
	
	private void setPageSize(int pageSize) {
		if(pageSize <= 0){
			throw new SystemException("PageSize must be equal or greater than 0");
		}
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return ((totalNum % pageSize == 0) ? (totalNum / pageSize) : (totalNum / pageSize + 1));
	}
	
	public List<E> getData() {
		return data;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public int startNum(){
		return (this.currentPage - 1 ) * this.pageSize;
	}

	@Override
	public String toString() {
		return "Page [data=" + data + ", pageSize=" + pageSize
				+ ", currentPage=" + currentPage + ", totalNum=" + totalNum
				+ "]";
	}

}
