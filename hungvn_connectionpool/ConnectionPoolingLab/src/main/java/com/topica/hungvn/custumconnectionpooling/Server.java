package com.topica.hungvn.custumconnectionpooling;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] arg) throws IOException {
		ServerSocket listener = new ServerSocket(9999);
		DBConnection dbConnection = new DBConnection();
		while(true) {
			Socket socket = listener.accept();
			ServerThread user = new ServerThread(socket,dbConnection);
			user.start();
		}
		
	}
}
