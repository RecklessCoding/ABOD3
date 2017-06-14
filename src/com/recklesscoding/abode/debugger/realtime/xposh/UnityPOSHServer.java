package com.recklesscoding.abode.debugger.realtime.xposh;

public class UnityPOSHServer {

    UnityPOSHServerRunnable unityPOSHServerRunnable;

    public void startServer() {
        if (unityPOSHServerRunnable == null)
            unityPOSHServerRunnable = new UnityPOSHServerRunnable();

        unityPOSHServerRunnable.start();
        new Thread(unityPOSHServerRunnable).start();
    }

    public void stopServer() {
        unityPOSHServerRunnable.stop();
    }
}
