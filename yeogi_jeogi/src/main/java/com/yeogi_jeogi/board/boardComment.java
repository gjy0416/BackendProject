package com.yeogi_jeogi.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class boardComment {
	private long cNum;
	private int cBNum;
	private int cBSubNum;
	private int cUserNum;
	private String cUserId;
	private String cContent;
	private int cGCount;
	private int cIsBF;
	private String cCDate;
	private String cMDate;	
	private String cUserGradeImgAdd;
}
