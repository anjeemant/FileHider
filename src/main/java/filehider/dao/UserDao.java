package filehider.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import filehider.db.MyConnection;
import filehider.model.User;

public class UserDao {
	public static boolean isPresent(String email) {
		Connection con = MyConnection.getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("select email from users");
			ResultSet resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				String e = resultSet.getString(1);
				if(e.equals(email)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static int saveUser(User user) {
		int status = 0;
		Connection con = MyConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("insert into users values(default, ?, ?)");
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			
			status = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
}
