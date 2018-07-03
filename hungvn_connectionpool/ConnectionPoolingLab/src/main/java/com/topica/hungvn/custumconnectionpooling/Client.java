package com.topica.hungvn.custumconnectionpooling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
@SuppressWarnings("resource")
public static void main(String[] args) throws UnknownHostException, IOException {
	final String serverHost ="localhost";
	Socket socClient = new Socket(serverHost, 9999);
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socClient.getOutputStream()));
	BufferedReader consoleRead = new BufferedReader(new InputStreamReader(System.in));
	while(true) {
		String line = consoleRead.readLine();
		System.out.println(line);
		bw.write(line);
		bw.newLine();
		bw.flush();
		if(line.equals("@exit")) {
			break;
		}
	}
	
}
}
