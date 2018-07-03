package com.topica.hungvn.custumconnectionpooling;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBConnection {
	private static final String DB_USERNAME = "db.username";
	private static final String DB_PASSWORD = "db.password";
	private static final String DB_URL = "db.url";
	private static final String DB_DRIVER_CLASS = "driver.class.name";
	private List<Connection> availableConnection = new ArrayList<>();
	private List<Connection> usingConnection = new ArrayList<>();
	private int MIN_CONNECTION = 1;
	private int MAX_CONNECTION = 3;
	private Properties properties = null;

	public synchronized Connection getConnection() throws InterruptedException {
		Connection conn = null;
		while (conn == null) {
			if (availableConnection.size()> 0) {
				conn = availableConnection.remove(0);
				usingConnection.add(conn);
				System.out.println("Connection is available..." );
			} else {
				initConnection();
				if (usingConnection.size() < MAX_CONNECTION) {
					conn = newConnection();
					System.out.println("New connection..");
				} else {
					System.out.println("Connection is wait...");
					wait();
					System.out.println("Connection is continue...");
				}
			}
		}
		return conn;
	}

	public synchronized Connection newConnection() {
		Connection conn = null;
		properties = new Properties();
		try {
			properties.load(new FileInputStream("src/main/java/database.properties"));
			Class.forName(properties.getProperty(DB_DRIVER_CLASS));
			conn = DriverManager.getConnection(properties.getProperty(DB_URL), properties.getProperty(DB_USERNAME),
					properties.getProperty(DB_PASSWORD));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		usingConnection.add(conn);
		return conn;
	}

	public synchronized void deleteConnection(Connection conn) {
		System.out.println("A connection is exit");
		usingConnection.remove(conn);
		availableConnection.add(conn);
		notifyAll();
	}
	public void initConnection() {
		for(int i=0;i<=MIN_CONNECTION;i++) {
			availableConnection.add(newConnection());
		}
	}
}
