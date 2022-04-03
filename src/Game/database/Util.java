package Game.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Game.view.Puzzle;

public class Util {

	public static String DRIVER_PATH = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String DB_URL = "jdbc:sqlserver://DESKTOP-UCIH129\\VANH:1433;databaseName=BTLJAVA";
	public Puzzle pz;

	public static Connection dbConn() {

		Connection conn = null;
		// load driver
		try {
			Class.forName(DRIVER_PATH);

			conn = DriverManager.getConnection(DB_URL, "sa", "123");
			System.out.println("OK - Connection is created! " + conn);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}
	
	//========== Đưa dữ liệu lên SQL Server============
	
	public static void insert(String time, String Count) {
		Connection c = dbConn();

		int C = Integer.parseInt(Count);
		try {
			String sql = "INSERT INTO result(Tg, Clicks) VALUES(?,?);";
			PreparedStatement pt = c.prepareStatement(sql);
			pt.setString(1, time);
			pt.setInt(2, C);
			pt.executeUpdate();
			pt.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//========== Đưa dữ liệu từ SQL Server vào trong ArrayList<String>
	
	public static ArrayList<String> loadDataToCombobox() {
		Statement st = null;
		ArrayList<String> List = new ArrayList<>();
		Connection c = dbConn();
		
		try {
			st = c.createStatement();
			// Step 4: Communication with MYSQL via: SQL.
			String sql = "Select ID from Combobox";
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				String id = rs.getNString("ID");
				List.add(id);
				System.out.println(id);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Step 5: close connection
			try {
				st.close();
				c.close();
				System.out.println("OK - Statement and connection is closed.");
			} catch (SQLException | NullPointerException e) {
				// N.A
			}
		}

		return List;
	}
	
	
}
