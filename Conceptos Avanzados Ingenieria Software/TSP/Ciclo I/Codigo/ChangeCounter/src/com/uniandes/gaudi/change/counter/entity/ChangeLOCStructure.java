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
	 * Represents the change date
	 */
	private Date chageDate;
	/**
	 * Represents the change number 
	 */
	private Integer changeNumber;
	/**
	 * Represents the change reason
	 */
	private String changeReason;
	/**
	 * Represents the person who modified the program 
	 */
	private String modifiedBy;
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
	 * @return the changeReason
	 */
	public String getChangeReason() {
		return changeReason;
	}
	/**
	 * @param changeReason the changeReason to set
	 */
	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}
	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}
	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
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
	
}
