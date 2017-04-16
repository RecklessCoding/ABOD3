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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @See: https://github.com/rwortham/Instinct-Server
 */
// provides support for a multi-threaded tcp server
public class ServerConsoleReader implements Runnable {

    private ThreadedServer server;

    public ServerConsoleReader(ThreadedServer server) {
        this.server = server;
    }

    public void run() {
        String str;
        System.out.println("'exit' stops the server. '!' toggles display to console.");
        BufferedReader br;

        try {
            while(true) {
                br = new BufferedReader(new InputStreamReader(System.in));

                if((str = br.readLine())!= null) {
                    System.out.println(str);
                    if (str.equals("exit"))
                        break;
                    else if (str.equals("!")) {
                        System.out.println("Toggling display");
                        server.toggleLogDisplay();
                    } else {
                        server.sendCmd(str);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Stopping Server");
        server.stop();
    }
}

