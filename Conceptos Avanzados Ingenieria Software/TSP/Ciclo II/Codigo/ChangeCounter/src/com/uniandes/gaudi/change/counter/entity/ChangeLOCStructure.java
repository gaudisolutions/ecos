package com.uniandes.gaudi.change.counter.entity;

import java.util.Date;

/**
 * This class represents a change loc file structure for the source code
 * 
 * @class ChangeLOCStructure.java
 * @author Felipe
 * @Date 6/04/2013
 * @since 1.0
 */
public class ChangeLOCStructure extends FileStructure {

	
	/**
	 * Represents the version
	 */
	private double version;
	/**
	 * Represents the change date
	 */
	private Date chageDate;
	/**
	 * Represents the change number 
	 */
	private Integer changeNumber;
	/**
	 * Represents the total loc for this structure
	 */
	private Integer total;
	/**
	 * Represents the total added loc for this structure
	 */
	private Integer totalAdded;
	/**
	 * Represents the total deleted loc for this structure
	 */
	private Integer totalDeleted;
	/**
	 * Stores the info about the user, the type of change and the
	 * comments for the change
	 */
	private CompareInfo compareInfo;
	/**
	 * @return the chageDate
	 */
	public Date getChageDate() {
		return chageDate;
	}
	/**
	 * @param chageDate the chageDate to set
	 */
	public void setChageDate(Date chageDate) {
		this.chageDate = chageDate;
	}
	/**
	 * @return the changeNumber
	 */
	public Integer getChangeNumber() {
		return changeNumber;
	}
	/**
	 * @param changeNumber the changeNumber to set
	 */
	public void setChangeNumber(Integer changeNumber) {
		this.changeNumber = changeNumber;
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
	/**
	 * @return the compareInfo
	 */
	public CompareInfo getCompareInfo() {
		return compareInfo;
	}
	/**
	 * @param compareInfo the compareInfo to set
	 */
	public void setCompareInfo(CompareInfo compareInfo) {
		this.compareInfo = compareInfo;
	}
	/**
	 * @return the version
	 */
	public double getVersion() {
		return version;
	}
	/**
	 * @param d the version to set
	 */
	public void setVersion(double d) {
		this.version = d;
	}

	
}
