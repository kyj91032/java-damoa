package chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
	
	private Socket socket;
	private ArrayList<ServerThread> threadList;
	private BufferedReader in;
	private BufferedWriter out;

	public ServerThread(Socket socket, ArrayList<ServerThread> threadList) {
		this.socket = socket;
		this.threadList = threadList;
	}

	@Override
	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			String inMsg = null;
			
			while(true) {
				inMsg = in.readLine();
				System.out.println(inMsg);
				if(inMsg.equalsIgnoreCase("bye")) {
					break;
				}
				sendToAllClients(inMsg);
			}
			
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

	private void sendToAllClients(String outMsg) {
		for(ServerThread serverThread : threadList) {
			try {
				serverThread.out.write(outMsg + "\n");
				serverThread.out.flush();
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
		
	}

	
	
}
