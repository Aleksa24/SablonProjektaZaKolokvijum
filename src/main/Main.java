/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import thread.ServerThread;

/**
 *
 * @author Aleksa
 */
public class Main {
    public static void main(String[] args) throws IOException {
        ServerThread serverThread = new ServerThread();
        serverThread.start();
    }
}
