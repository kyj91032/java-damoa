package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class ChatServerThread extends Thread {
    private Socket clientSocket;
	private BufferedWriter writer;
	private BufferedReader reader;
	private ChatView chatview;

    public ChatServerThread(Socket socket, ChatView chatview) {
        this.clientSocket = socket;
        this.chatview = chatview;
        
    }

    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            String clientMessage;
            while ((clientMessage = reader.readLine()) != null) {
                // 클라이언트로부터 수신한 채팅 메시지를 채팅방에 전달
            	chatview.appendMessage(clientMessage);
            }

            reader.close();
            writer.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
}
