package com.example.oc.parkingapp;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by RzvN on 5/24/2016.
 */
public class SocketSingleton {

    private static Socket socket;
    private static SocketSingleton s = new SocketSingleton();


    private SocketSingleton() {
        try {
            this.socket = IO.socket("http://192.168.43.82:3000/");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        socket.connect();
    }

    public static Socket getSocket() {
        return SocketSingleton.socket;
    }
}
