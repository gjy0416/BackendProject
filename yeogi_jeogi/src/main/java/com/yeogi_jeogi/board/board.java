package com.yeogi_jeogi.board;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class board implements Serializable {
	private int bNum;
	private int bSubNum;
	private String bSort;
	private String bUserId;
	private String bTitle;
	private String bContent;
	private String bImgAdd;
	private int bCount;
	private String bCDate;
	private String bMDate;
	private int bRcm;
	private String bMgrId;
	private String bImgListAdd;
	private String bUserGradeImgAdd;
	
}
