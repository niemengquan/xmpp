package com.nmq.xmpp;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.io.IOException;

/**
 * Created by niemengquan on 2016/7/26.
 */
public class XmppTest {
    public static void main(String[] args) {
        XMPPTCPConnectionConfiguration conf = XMPPTCPConnectionConfiguration.builder()
                .setServiceName("nmq.imqq.com")
                .setUsernameAndPassword("admin@172.19.2.167", "123456")
                .setHost("127.0.0.1")
                .setPort(5222)
                .setCompressionEnabled(false)
                .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                .setSendPresence(true)
                .build();
        XMPPTCPConnection connection = new XMPPTCPConnection(conf);
        try {
            connection.connect();
            connection.login("mqnie","123456");
            System.out.println("Logged in "+connection.isAuthenticated());
            Chat chat = ChatManager.getInstanceFor(connection).createChat("niemq@nmq.imqq.com", new ChatMessageListener() {
                @Override
                public void processMessage(Chat chat, Message message) {
                    System.out.println("Received message: " + message);
                    System.out.println("reply:"+message.getBody());
                }
            });
            chat.sendMessage("你好啊，What are you doing?");
            Message message = new Message();
            message.setBody("Howdy!");
            //encrypt message
            chat.sendMessage(message);
            //set you online status
            Presence presence = new Presence(Presence.Type.available);
            presence.setStatus("What's up everyone?");
            connection.sendStanza(presence);
           /* while (true) {
                Thread.sleep(50);
            }*/
        } catch (SmackException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMPPException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

    }
}
