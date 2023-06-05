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
    private ChatManager chatManager;
	private ChatView chatview;

    public ChatServerThread(Socket socket, ChatView chatview, ChatManager chatManager) {
        this.clientSocket = socket;
        this.chatManager = chatManager;
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
            	System.out.println("serverThread의 실행 " + clientMessage + "\n");
                chatManager.broadcastMessage(clientMessage, chatview);
                
            }

            reader.close();
            writer.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try {
            chatview.appendMessage(message);
            System.out.println("serverthread의 sendmessage실행");
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
