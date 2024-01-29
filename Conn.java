package Hotalmanagement;

import java.sql.*;

public class Conn {

	Connection c;
	Statement s;

	Conn() {
		try {
			c = DriverManager.getConnection("jdbc:mysql:///hotelmanagement", "root", "manager");
			s = c.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
