/*
 * Main.java
 */

package chatserver;

import java.io.*;
import java.net.*;
import java.util.*;
//Daryl
//Server
public class Main {
    
    public static void main(String[] argc) throws Exception {
        BufferedReader stdIn  = new BufferedReader(
                                new InputStreamReader(System.in));
        byte[]          buf = null;
       // ServerSocket    serverSocket = new ServerSocket(4445);
        //Socket          sock = null;
        int             ticket = 0;
        DatagramSocket    socket = new DatagramSocket(4445);
        DatagramPacket[] packets = new DatagramPacket[100];
        ChatThread[] threads = new ChatThread[100];
        String userName;
        
        int numClients = 0;

        while (true) {
            numClients++;
            buf = new byte[256];
            System.out.println("Waiting for packet");
            packets[numClients] = new DatagramPacket(buf, buf.length);
            System.out.println(packets[numClients]);
            socket.receive(packets[numClients]);
            System.out.println(packets[numClients]);

            threads[numClients] = new ChatThread(packets[numClients], socket);
            threads[numClients].start();
           // System.out.println("Packet Recieved- Port: " + port);

            
            
        }
    }
        
        
        
        
        
        
        
        
        
        
        /*
        BufferedReader stdIn  = new BufferedReader(
                                new InputStreamReader(System.in));
        byte[] buf = null;
        DatagramSocket    socket = new DatagramSocket(4445);
        DatagramPacket    packet = null;

        while (true) {
            try {                
                buf = new byte[256];
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);         // receive request

                String request = new String(packet.getData());
                System.out.println("ChatServer: Request from client: " + request);
                System.out.println("ChatServer: Type in your response:");
                
                String serverResponse = stdIn.readLine();
                buf = serverResponse.getBytes();

                InetAddress address = packet.getAddress();
                int         port    = packet.getPort();

                packet = new DatagramPacket(buf, buf.length, address, port);

                String response = new String(packet.getData());
                System.out.println("ChatServer: Sending response:" + response);
                socket.send(packet);
    
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }
