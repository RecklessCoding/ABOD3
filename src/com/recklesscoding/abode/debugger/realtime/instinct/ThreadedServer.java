//  Instinct Server - ThreadedServer
//  Copyright (c) 2016  Robert H. Wortham <r.h.wortham@gmail.com>
//	
//  This program is free software; you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation; either version 2 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
//
//  You should have received a copy of the GNU General Public License
//  along with this program; if not, write to the Free Software
//  Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.

package com.recklesscoding.abode.debugger.realtime.instinct;

import com.sun.corba.se.spi.activation.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @See: https://github.com/rwortham/Instinct-Server
 */
// provides support for a multi-threaded tcp server
public class ThreadedServer implements Runnable {

    protected int serverPort = 3000;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;
    protected Thread runningThread = null;
    protected TcpHandler lastHandler = null;

    public ThreadedServer(int port) {
        this.serverPort = port;
    }

    public void run() {
        synchronized (this) {
            this.runningThread = Thread.currentThread();
        }
        new Thread(new ServerConsoleReader(this)).start();

        openServerSocket();
        while (!isStopped()) {
            Socket clientSocket;
            try {
                System.out.println("Waiting for connection(s).");
                clientSocket = this.serverSocket.accept();
                System.out.println("Accepted.");
            } catch (IOException e) {
                if (isStopped()) {
                    System.out.println("Server Stopped.");
                    System.out.println(e);
                    return;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }
            // each handler gets its own log file
            lastHandler = new TcpHandler(clientSocket, "cmdfile.txt");
            new Thread(lastHandler).start();
        }
        System.out.println("Server Stopped.");
    }

    public synchronized void sendCmd(String cmd) {
        if (!this.isStopped && lastHandler != null) {
            lastHandler.sendCmd(cmd);
        }
    }

    public synchronized void toggleLogDisplay() {
        if (!this.isStopped && (lastHandler != null)) {
            lastHandler.toggleLogDisplay();
        }
    }

    public boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop() {
        this.isStopped = true;
        try {
            this.serverSocket.close();
            if (this.lastHandler != null)
                this.lastHandler.stop();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port " + this.serverPort, e);
        }
    }
}

