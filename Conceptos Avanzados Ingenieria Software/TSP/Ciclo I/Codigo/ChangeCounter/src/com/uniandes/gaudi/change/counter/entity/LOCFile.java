package com.uniandes.gaudi.change.counter.entity;

import java.util.List;

/**
 * This class represents a file containing locs
 * 
 * @class LOCFile.java
 * @author Felipe
 * @Date 7/04/2013
 * @since 1.0
 */
public class LOCFile implements Cloneable {

	/**
	 * Stores the locs for this file
	 */
	private List<LineCode> locs;
	/**
	 * Stores the file name
	 */
	private String name;
	/**
	 * stores the total locs for this file
	 */
	private Integer total;
	/**
	 * stores the total added loc for this file
	 */
	private Integer totalAdded;
	/**
	 * stores the total deleted loc for this file
	 */
	private Integer totalDeleted;
	/**
	 * @return the locs
	 */
	public List<LineCode> getLocs() {
		return locs;
	}
	/**
	 * @param locs the locs to set
	 */
	public void setLocs(List<LineCode> locs) {
		this.locs = locs;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}
	/**
	 * @return the totalAdded
	 */
	public Integer getTotalAdded() {
		return totalAdded;
	}
	/**
	 * @param totalAdded the totalAdded to set
	 */
	public void setTotalAdded(Integer totalAdded) {
		this.totalAdded = totalAdded;
	}
	/**
	 * @return the totalDeleted
	 */
	public Integer getTotalDeleted() {
		return totalDeleted;
	}
	/**
	 * @param totalDeleted the totalDeleted to set
	 */
	public void setTotalDeleted(Integer totalDeleted) {
		this.totalDeleted = totalDeleted;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}
