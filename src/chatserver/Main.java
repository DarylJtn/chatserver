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
        DatagramSocket    socket = new DatagramSocket(4445);
        DatagramPacket[] packets = new DatagramPacket[100];
        ChatThread[] threads = new ChatThread[100];
        
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

            
            
        }
    }
}
