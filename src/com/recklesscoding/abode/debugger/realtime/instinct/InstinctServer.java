//  Instinct Server
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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InstinctServer {

    private int serverPort = 3000;

    private ThreadedServer server;

    public InstinctServer() {
        readServerPort();
    }

    public void startServer() {
        runServer();
    }

    public void stopServer() {
        server.stop();
    }

    public boolean isRunning() {
        return !server.isStopped();
    }

    private void runServer() {
        System.out.println("Instinct Server started on port: " + serverPort);

        server = new ThreadedServer(serverPort);

        new Thread(server).start();
    }

    private void readServerPort() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("serverport.txt"));
            if (br != null)
            serverPort = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {

                ex.printStackTrace();
            }
        }
    }
}

