package com.yeogi_jeogi.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class boardRcm {
	private int rUserNum;
	private int rBNum;
	
	public int getrUserNum() {
		return rUserNum;
	}
	public void setrUserNum(int rUserNum) {
		this.rUserNum = rUserNum;
	}
	public int getrBNum() {
		return rBNum;
	}
	public void setrBNum(int rBNum) {
		this.rBNum = rBNum;
	}
	
}
