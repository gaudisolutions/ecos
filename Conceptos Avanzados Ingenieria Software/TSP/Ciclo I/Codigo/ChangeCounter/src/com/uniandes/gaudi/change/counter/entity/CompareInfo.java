package com.uniandes.gaudi.change.counter.entity;

/**
 * This class represents the compare info 
 * 
 * @class CompareInfo.java
 * @author Felipe
 * @Date 7/04/2013
 * @since 1.0
 */
public class CompareInfo {

	/**
	 * Represents the change reason
	 */
	private String changeReason;
	/**
	 * Represents the person who modified the program 
	 */
	private String modifiedBy;
	/**
	 * represents the modification type (fix, enhancement)
	 */
	private ModificationType modificationType;
	/**
	 * represents the actual project path
	 */
	private String actualPath;
	/**
	 * Represents the modified project path
	 */
	private String modifiedPath;
	
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
	 * @return the modificationType
	 */
	public ModificationType getModificationType() {
		return modificationType;
	}
	/**
	 * @param modificationType the modificationType to set
	 */
	public void setModificationType(ModificationType modificationType) {
		this.modificationType = modificationType;
	}
	/**
	 * @return the actualPath
	 */
	public String getActualPath() {
		return actualPath;
	}
	/**
	 * @param actualPath the actualPath to set
	 */
	public void setActualPath(String actualPath) {
		this.actualPath = actualPath;
	}
	/**
	 * @return the modifiedPath
	 */
	public String getModifiedPath() {
		return modifiedPath;
	}
	/**
	 * @param modifiedPath the modifiedPath to set
	 */
	public void setModifiedPath(String modifiedPath) {
		this.modifiedPath = modifiedPath;
	}
		
}
