package sandbox_mysql_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");

		Connection connect = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		connect = DriverManager
				.getConnection("jdbc:mysql://165.132.98.87/NBA_rev?"
						+ "user=&password=");

		statement = connect.createStatement();
		resultSet = statement.executeQuery("select * from g4_member limit 10");

		writeResultSet(resultSet);

		// Crawl example #BEGIN
		URL oracle = new URL("http://www.oracle.com/");
		BufferedReader in = new BufferedReader(new InputStreamReader(
				oracle.openStream()));

		StringBuilder sb = new StringBuilder();
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
			sb.append(inputLine);
		}
		in.close();
		// Crawl example #END

		ArrayList<String> list = pullLinks(sb.toString());

		for (String s : list) {
			System.out.println(s);
		}

		preparedStatement = connect
				.prepareStatement("insert into table_name values (?, ?, ?)");

		preparedStatement.setString(1, "data");
		preparedStatement.setString(2, "data");
		preparedStatement.setDate(3, new java.sql.Date(2009, 12, 11));
		// preparedStatement.executeUpdate();
	}

	private static void writeResultSet(ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			String mb_id = resultSet.getString("mb_id");
			Date mb_datetime = resultSet.getDate("mb_datetime");
			System.out.println("Comment: " + mb_id);
			System.out.println("Date: " + mb_datetime);
		}
	}

	// Pull all links from the body for easy retrieval
	private static ArrayList<String> pullLinks(String text) {
		ArrayList<String> links = new ArrayList<String>();

		String regex = "\\(?\\b(http://|www[.])[-A-Za-z0-9+&amp;@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&amp;@#/%=~_()|]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(text);
		while (m.find()) {
			String urlStr = m.group();
			if (urlStr.startsWith("(") && urlStr.endsWith(")")) {
				urlStr = urlStr.substring(1, urlStr.length() - 1);
			}
			links.add(urlStr);
		}
		return links;
	}
}
