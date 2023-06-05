package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatManager {
    private List<ChatServerThread> chatThreads;

    public ChatManager() {
        chatThreads = new ArrayList<>();
    }

    public void addChatThread(ChatServerThread chatThread) {
        chatThreads.add(chatThread);
    }

    public void removeChatThread(ChatServerThread chatThread) {
        chatThreads.remove(chatThread);
    }

    public void broadcastMessage(String message, ChatView chatview) {
        for (ChatServerThread chatThread : chatThreads) {
            chatThread.sendMessage(message);
        }
    }
}
