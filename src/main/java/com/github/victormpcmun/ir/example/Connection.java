package com.github.victormpcmun.ir.example;

public class Connection {

    private final int  connectionId;

    public Connection(int connectionId) {
        this.connectionId = connectionId;
    }

    public void communicate() {
        System.out.println("Invoked communication() method. Connection Id: " + connectionId);
    }
}
