package com.yeogi_jeogi.login;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="USERS")
public class USERS {
	
	@Id
	private int USER_NO;
	
	@Column
	private int GRADE_NO;
	
	@Column
	private String ID;
	
	@Column
	private String NAME;
	
	@Column
	private String PASSWORD;
	
	@Column
	private String EMAIL;
	
	@Column
	private String PHONE;
	
	@Column
	private String MBTI;
	
	@Column
	private String IMG_ADD;
	
	@Column
	private String ROLE;
	
}
