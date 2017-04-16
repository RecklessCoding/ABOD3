package com.recklesscoding.abode.debugger.realtime;

/**
 * Athor: Andreas
 * Date: 19/01/2016.
 */
public interface ISocketListener {

    void onMessage(String line);

    void onClosedStatus(Boolean isClosed);
}