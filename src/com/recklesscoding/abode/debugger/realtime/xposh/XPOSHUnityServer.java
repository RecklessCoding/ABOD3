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

package com.recklesscoding.abode.debugger.realtime.xposh;

import com.recklesscoding.abode.core.plan.Plan;
import com.recklesscoding.abode.core.plan.planelements.PlanElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class XPOSHUnityServer implements Runnable {

    private int serverPort = 3000;
    private ServerSocket serverSocket = null;
    private boolean isStopped = false;
    private Thread runningThread = null;

    public XPOSHUnityServer() {
    }

    public XPOSHUnityServer(int port) {
        this.serverPort = port;
    }

    public void run() {
        synchronized (this) {
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();
        while (!isStopped()) {
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
                System.out.printf("New connection");
            } catch (IOException e) {
                if (isStopped()) {
                    System.out.println("Server Stopped.");
                    return;
                }
                throw new RuntimeException(
                        "Error accepting client connection", e);
            }
            new Thread(new WorkerRunnable(clientSocket)).start();
        }
        System.out.println("Server Stopped.");
    }

    public synchronized void stop() {
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }


    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port 8080", e);
        }
    }
}

class WorkerRunnable implements Runnable {

    private Socket clientSocket = null;
    private boolean isStopped = false;


    public WorkerRunnable(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            BufferedReader clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String line;

            while (!isStopped && (line = clientInput.readLine()) != null) {
              //  System.out.println(line);
                handlePlanElementUpdate(line);
            }
            clientInput.close();
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }

    public synchronized void stop() {
        this.isStopped = true;
    }

    private void handlePlanElementUpdate(String line) {
        String cvsSplitBy = ",";

        String[] splittedLine = line.split(cvsSplitBy);
        PlanElement planElement;
        String typeOfPlanElement;
        String planElementName;

        // Making sure the search is not pointless
        if (isValidLine(splittedLine)) {
            planElementName = splittedLine[0];
            typeOfPlanElement = splittedLine[1];
            planElement = getPlanElement(planElementName, typeOfPlanElement);

            if (planElement != null) {
                planElement.setToUpdate();
            }
        }
    }

    private boolean isValidLine(String[] splittedLine) {
        return splittedLine.length >= 1;
    }

    private PlanElement getPlanElement(String planElementName, String typeOfPlanElement) {
        PlanElement planElement = null;

        if (isAction(typeOfPlanElement)) {
            System.out.println(planElementName);
            planElement = Plan.getInstance().findAction(planElementName);
        } else if (isActionPattern(typeOfPlanElement)) {
            planElement = Plan.getInstance().findActionPattern(planElementName);
        } else if (isCompetence(typeOfPlanElement)) {
            planElement = Plan.getInstance().findCompetence(planElementName);
        } else if (isCompetenceElement(typeOfPlanElement)) {
            planElement = Plan.getInstance().findCompetenceElementXPOSH(planElementName);
        } else if (isDriveElement(typeOfPlanElement)) {
            planElement = Plan.getInstance().findDriveElementXPOSH(planElementName);
        } else if (isDrive(typeOfPlanElement)) {
            planElement = Plan.getInstance().findDriveCollection(planElementName);
        }

        return planElement;
    }

    private boolean isAction(String typeOfPlanElement) {
        return typeOfPlanElement.equals("A");
    }

    private boolean isActionPattern(String typeOfPlanElement) {
        return typeOfPlanElement.equals("AP");
    }

    private boolean isCompetence(String typeOfPlanElement) {
        return typeOfPlanElement.equals("C");
    }

    private boolean isCompetenceElement(String typeOfPlanElement) {
        return typeOfPlanElement.equals("CE");
    }

    private boolean isDriveElement(String typeOfPlanElement) {
        return typeOfPlanElement.equals("DE");
    }

    private boolean isDrive(String typeOfPlanElement) {
        return typeOfPlanElement.equals("D");
    }
}