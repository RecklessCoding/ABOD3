package com.recklesscoding.abode.debugger.prerecorded;

import com.recklesscoding.abode.util.IReader;

/**
 * Synchronizers the map extracted by reading a local log file with the running ABODE, by poking the diagram view.
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public abstract class LogReader implements IReader {
    public abstract void readFile(String file);
}
