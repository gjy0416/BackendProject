package com.yeogi_jeogi.login;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="CHANGEABLEUSERS")
@Table(name="USERS")
public class CHANGEABLEUSERS {
	
	@Id
	@Column
	private String ID;
	
	@Column
	private String EMAIL;
	
	@Column
	private String PHONE;
	
	@Column
	private String MBTI;
	
	@Column
	private String IMG_ADD;
}
