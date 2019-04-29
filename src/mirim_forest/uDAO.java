package mirim_forest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class uDAO {
	private static Connection conn = null; //���������� sql�� ���ؾ� �ϱ� ������ connection ��ü ����
	private static uDAO uDAO = null;
	private static final String USER_TABLE_NAME = "user_table";
	
	// �̱��� ���� �����Ͽ� ������ ���� �Ұ�
	private uDAO() {}
	
	public static void init(Connection c) {
		conn = c;
	}
	
 	//���ο� ������� ������ ���� ����
	public static User add(User user) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(String.format("INSERT INTO %s VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", USER_TABLE_NAME));
		ps.setString(1, user.name);
		ps.setInt(2, user.gender);
		ps.setLong(3, user.fitime);
		ps.setLong(4, user.stime);
		ps.setInt(5, 0);
		ps.setInt(6, 0);
		ps.setInt(7, 0);
		ps.setInt(8, 0);
		ps.setInt(9, 0);
		ps.setInt(10, 0);
		ps.setInt(11, 0);
		ps.setInt(12, 0);
		ps.setInt(13, 0);
		ps.setInt(14, 0);
		ps.setInt(15, 0);
		ps.setInt(16, 0);
		ps.setInt(17, 0);
		ps.setInt(18, 0);
		ps.setInt(19, 0);
		ps.setInt(20, 0);
		ps.setInt(21, 0);
		ps.setInt(22, 0);
		ps.setInt(23, 0);
		ps.setInt(24, 0);
		ps.setInt(25, 0);
		int res = ps.executeUpdate();
		ps.close();
		
		return res == 1 ? user : null;
	}
	
	//���忡�� �̸��� ���ؼ� insert�� update�� �����Ͽ� �����ϱ� ���� DB��� �ҷ��ð���
	public static List<User> getLists() throws SQLException {
		List<User> lst = new ArrayList<>();
		Statement s = conn.createStatement();
		ResultSet rs = s.executeQuery(String.format("SELECT * FROM %s", USER_TABLE_NAME));
		System.out.println("����Ʈ ���� ����!");
		while(rs.next()) {
			lst.add(new User(rs.getString("name"), rs.getInt("gender"), rs.getLong("fitime"), rs.getLong("stime"), rs.getInt("point"), rs.getInt("desk"),
			rs.getInt("bed"), rs.getInt("chair"), rs.getInt("closet"), rs.getInt("sofa"), rs.getInt("potato"), rs.getInt("deskX"), rs.getInt("deskY"),
			rs.getInt("bedX"), rs.getInt("bedY"), rs.getInt("chairX"), rs.getInt("chairY"), rs.getInt("closetX"), rs.getInt("closetY"), rs.getInt("sofaX"), 
			rs.getInt("sofaY"), rs.getInt("potatoX"), rs.getInt("potatoY"), rs.getInt("gift"), rs.getInt("clothes")));
		}
		s.close();
		rs.close();
		return lst;
	}
	
//	//��ϵ� ������� ��� => ���� �⸥ �ð��� ������Ʈ
//	public static boolean update(User user) throws SQLException {
//		PreparedStatement ps = conn.prepareStatement(String.format("UPDATE %s SET itime = ? WHERE name = ?", USER_TABLE_NAME));
//		ps.setLong(1, user.itime);
//		ps.setString(2, Mainpanel.name);
//		//ps.setLong(4, user.otime);
//		int res = ps.executeUpdate();
//		ps.close();
//		
//		return res == 1 ? true : false;
//	}
//	
	//����� ����?
//	public static boolean delete(User user) throws SQLException {
//		PreparedStatement ps = conn.prepareStatement(String.format("DELETE FROM %s WHERE id = ?", USER_TABLE_NAME));
//		ps.setInt(1, user.id);
//		int res = ps.executeUpdate();
//		ps.close();
//		
//		return res == 1 ? true : false;
//	}
	
//	public static User get(String name) throws SQLException {
//	User user = null;
//	PreparedStatement ps = conn.prepareStatement(String.format("SELECT * FROM %s WHERE name = ?", USER_TABLE_NAME));
//	ps.setString(1, name);
//	ResultSet rs = ps.executeQuery();
//	if(rs.next()) {
//		user = new User(rs.getString("name"), rs.getInt("gender"), rs.getLong("itime"), rs.getLong("otime"));
//	}
//	ps.close();
//	rs.close();
//	return user;
//}

}
