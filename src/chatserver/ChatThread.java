/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

/**
 *
 * @author daryljohnston
 */
public class ChatThread extends Thread {
    //Socket sock = null;
    int ticket = 0;
    DatagramSocket sock;
    DatagramPacket packet;
    byte[] buf = null;
    public ChatThread(DatagramSocket sock,DatagramPacket pack, int ticket) {
        this.sock = sock;
        this.packet = pack;
        this.ticket = ticket;
        buf = new byte[256];

    }   
    public void run() {
        PrintWriter    out = null;
        BufferedReader in  = null;       

        BufferedReader stdIn  = new BufferedReader(
                                new InputStreamReader(System.in)); 
        try { 
           
            packet = new DatagramPacket(buf, buf.length);
            sock.receive(packet);         // receive request
            boolean going = true;
            
            
            while (going) {
                System.out.println("Chat:" + ticket + " waiting for request");
                sock.receive(packet);
                String request = new String(packet.getData());
                
        
                System.out.println("Chat:" + ticket + 
                                  " Request from client: " + request);
                if (request.startsWith("BYE") ) {
                    break;
                }            
                
                
                String serverResponse = stdIn.readLine();
                buf = serverResponse.getBytes();
                
                System.out.println("Chat: Type in your response:");
                
                System.out.println("Chat: Sending response:" + serverResponse);               
                out.println(serverResponse);
            }
    
        } catch (IOException e) {
                e.printStackTrace();
        }
    
}

}
