package com.uniandes.gaudi.change.counter.entity;

import java.util.List;

/**
 * This class represents a block of locs
 * 
 * @class BlockLOC.java
 * @author Felipe
 * @Date 7/04/2013
 * @since 1.0
 */
public class BlockLOC extends LineCode {

	/**
	 * Stores a locs for this block
	 */
	private List<LineCode> locs;

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
	
}
