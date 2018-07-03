package com.topica.hungvn.custumconnectionpooling;

import java.net.Socket;
import java.sql.Connection;

public class ServerThread extends Thread {
	private Socket socket;
	private DBConnection dbConnection;

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public DBConnection getDbConnection() {
		return dbConnection;
	}

	public void setDbConnection(DBConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public ServerThread(Socket socket, DBConnection dbConnection) {
		this.socket = socket;
		this.dbConnection = dbConnection;
	}

	public void run() {
		try {
			Connection conn = dbConnection.getConnection();
			double start = System.currentTimeMillis();
			while (System.currentTimeMillis() - start < 10000);
			dbConnection.deleteConnection(conn);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
