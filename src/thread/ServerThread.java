/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import domain.Korisnik;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Dusan
 */
public class ServerThread extends Thread {

    private final ServerSocket serverSocket;
    public static List<ClientThread> clients;
    public static List<Korisnik> korisniciServera;
    public static List<Korisnik> korisniciUlogovani;

    public ServerThread() throws IOException {
        serverSocket = new ServerSocket(9000);
        clients = new ArrayList<>();
        korisniciServera = new ArrayList<Korisnik>(){{
            add(new Korisnik("aleksa"));
        }};
        korisniciUlogovani = new ArrayList<>();
    }

    @Override
    public void run() {
        while (!serverSocket.isClosed()) {
            System.out.println("Waiting client");
            try {
                Socket socket = serverSocket.accept();
                ClientThread thread = new ClientThread(socket);
                clients.add(thread);
                thread.start();
                
                System.out.println("Client connected");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        stopAllThreads();
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    private void stopAllThreads() {
        for (ClientThread client : clients) {
            try {
                client.getSocket().close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
