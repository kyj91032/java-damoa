package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;



public class ChatServerThread extends Thread {
	private Socket clientSocket; private BufferedWriter writer; private BufferedReader reader; private ChatView chatview; private ArrayList<ChatServerThread> threadlist;


    public ChatServerThread(Socket socket, ArrayList<ChatServerThread> threadlist) {
        this.clientSocket = socket;
        this.threadlist = threadlist;
    }

    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            String clientMessage;
            while ((clientMessage = reader.readLine()) != null) {
                // 클라이언트로부터 수신한 채팅 메시지를 채팅방에 전달
            	sendToAllClients(clientMessage);
            }

            reader.close();
            writer.close();
            clientSocket.close();
        } catch (IOException e) {
            
        }
    }

    private void sendToAllClients(String clientMessage) {
    	for(ChatServerThread chatserverthread : threadlist) {
			try {
				chatserverthread.writer.write(clientMessage + "\n");
				chatserverthread.writer.flush();
			} catch (IOException e) {
				
			}
		}
	}

	
}
