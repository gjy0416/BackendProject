package com.yeogi_jeogi.login;

import java.sql.SQLException;

public interface loginDAO {
	
	public USERS getUser(String username) throws SQLException;
}
