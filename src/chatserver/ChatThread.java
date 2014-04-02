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
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daryljohnston
 */
public class ChatThread extends Thread {
    //Socket sock = null;
    int ticket = 0;
    DatagramSocket sock;
    DatagramPacket packet;
    byte[] buf = new byte[256];;
    Boolean con = true;
    String name = null;
    String message;
    BufferedReader stdIn  = new BufferedReader(
                                new InputStreamReader(System.in));
   
    public ChatThread(DatagramSocket sock,DatagramPacket pack, int ticket) {
        this.sock = sock;
        this.packet = pack;
        this.ticket = ticket;
        buf = new byte[256];

    }   

    ChatThread(DatagramPacket pct, DatagramSocket sck) {
        this.packet = pct;
        this.sock = sck;
        
    }
    
    
    public void run() {
        System.out.println("Thread Started");
        while(name == null){
        String request = new String(packet.getData());
        System.out.println("Recieving Name From " + request);
        name = request;
        request = null;
        }
        System.out.println("Connected");
        while(con){
          

                InetAddress address = packet.getAddress();
                int         port    = packet.getPort();
                System.out.println("Port Number: " + port);
                packet = new DatagramPacket(buf, buf.length, address, port);
                String response = new String(packet.getData());
                 System.out.println(response);  
                    
                    
                    
                    //   buf = packet.getData();
               // packet.getData();
               //System.out.println(packet.getData());
               //System.out.println(packet.getData());

                  
              //  System.out.println("recieved");
             //   String request = new String(packet.getData());
             //   System.out.println(name + " Said " + request);
                Delay.skip(10);
              //  System.out.println("done");
      
    
}

}
}
/*
 *
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
 * */
 