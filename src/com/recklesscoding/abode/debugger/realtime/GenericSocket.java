package com.recklesscoding.abode.debugger.realtime;


import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 * Athor: Andreas
 * Date: 19/01/2016.
 */
public abstract class GenericSocket implements ISocketListener {

    public static int DEFAULT_PORT = 2010;
    public int port;
    protected Socket socketConnection = null;
    private BufferedWriter output = null;
    private BufferedReader input = null;
    private boolean ready = false;
    private Thread socketReaderThread;
    private Thread setupThread;

    /*
     * Debug flags are a multiple of 2
     */
    public static final int DEBUG_NONE = 0x0;
    public static final int DEBUG_IO = 0x1;
    public static final int DEBUG_EXCEPTIONS = 0x2;
    public static final int DEBUG_STATUS = 0x4;
    public static final int DEBUG_ALL =
            DEBUG_IO | DEBUG_EXCEPTIONS | DEBUG_STATUS;
    protected int debugFlags = 0x0;

    public GenericSocket() {
        this(DEFAULT_PORT, DEBUG_NONE);
    }

    public GenericSocket(int port) {
        this(port, DEBUG_NONE);
    }

    public GenericSocket(int port, int debugFlags) {
        this.port = port;
        this.debugFlags = debugFlags;
    }

    /**
     * Set up a connection in the background.  This method returns no status,
     * however the onClosedStatus(Boolean) method will be called when the
     * status of the socket changes, either opened or closed (for whatever
     * reason).
     */
    public void connect() {
        try {
            /*
             * Background thread to set up and open the input and
             * output data streams.
             */
            setupThread = new SetupThread();
            setupThread.start();
            /*
             * Background thread to continuously read from the input stream.
             */
            socketReaderThread = new SocketReaderThread();
            socketReaderThread.start();
        } catch (Exception e) {
            if (debugFlagIsSet(DEBUG_EXCEPTIONS)) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Shutdown and close GenericSocket instance in an orderly fashion.
     * As per the Java Socket API, once a Socket has been closed, it is not
     * available for further networking use (i.e. can't be reconnected
     * or rebound) A new Socket needs to be created.
     */
    public void shutdown() {
        close();
    }

    /**
     * Close down the GenericSocket infrastructure.  As per the Java Socket
     * API, once a Socket has been closed, it is not available for
     * further networking use (i.e. can't be reconnected or rebound).
     * A new Socket needs to be created.
     * <p>
     * For certain implementations (e.g. ProviderSocket), the
     * closeAdditionalSockets() method may need to be more than just a
     * null method.
     */
    private void close() {
        try {
            if (socketConnection != null && !socketConnection.isClosed()) {
                socketConnection.close();
            }
            /*
             * closeAdditionalSockets() has to be implemented in a subclass.
             * In some cases nothing may be requied (null method), but for
             * others (e.g. SocketServer), the method can be used to
             * close additional sockets.
             */
            closeAdditionalSockets();
            if (debugFlagIsSet(DEBUG_STATUS)) {
                System.out.println("Connection closed");
            }
            /*
             * The onClosedStatus() method has to be implemented by
             * a sublclass.  If used in conjunction with JavaFX,
             * use Entry.deferAction() to force this method to run
             * on the main thread.
             */
            onClosedStatus(Boolean.TRUE);
        } catch (Exception e) {
            if (debugFlagIsSet(DEBUG_EXCEPTIONS)) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is invoked to do instance-specific socket initialization.
     * Due to the different ways that sockets are set up (e.g.
     * ServerSocket vs Socket), the implementation details go here.
     * Initialization up to and including either accept() or connect() take
     * place here.
     */
    public abstract void initSocketConnection() throws SocketException;

    /**
     * This method is called to close any additional sockets that are
     * internally used.  In some cases (e.g. SocketClient), this method
     * should do nothing.  In others (e.g. SocketServer), this method should
     * close the internal ServerSocket instance.
     */
    public abstract void closeAdditionalSockets();

    /*
     * Synchronized method set up to wait until the SetupThread is
     * sufficiently initialized.  When notifyReady() is called, waiting
     * will cease.
     */
    private synchronized void waitForReady() {
        while (!ready) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
    }

    /*
     * Synchronized method responsible for notifying waitForReady()
     * method that it's OK to stop waiting.
     */
    private synchronized void notifyReady() {
        ready = true;
        notifyAll();
    }

    /**
     * Send a message in the form of a String to the socket.  A NEWLINE will
     * automatically be appended to the message.
     *
     * @param msg The String message to send
     */
    public void sendMessage(String msg) {
        try {
            output.write(msg, 0, msg.length());
            output.newLine();
            output.flush();
            if (debugFlagIsSet(DEBUG_IO)) {
                System.out.println("send> " + msg);
            }
        } catch (IOException ioException) {
            if (debugFlagIsSet(DEBUG_EXCEPTIONS)) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * Returns true if the specified debug flag is set.
     *
     * @param flag Debug flag in question
     * @return true if the debug flag 'flag' is set.
     */
    public boolean debugFlagIsSet(int flag) {
        return ((flag & debugFlags) != 0) ? true : false;
    }

    /**
     * Turn on debugging option.
     *
     * @param flag The debugging flag to enable
     */
    public void setDebugFlag(int flag) {
        debugFlags |= flag;
    }

    /**
     * Turn off debugging option.
     *
     * @param flag The debugging flag to disable
     */
    public void clearDebugFlag(int flag) {
        debugFlags &= flag;
    }


    class SetupThread extends Thread {

        @Override
        public void run() {
            try {
                initSocketConnection();
                if (socketConnection != null && !socketConnection.isClosed()) {
                    /*
                     * Get input and output streams
                     */
                    input = new BufferedReader(new InputStreamReader(
                            socketConnection.getInputStream()));
                    output = new BufferedWriter(new OutputStreamWriter(
                            socketConnection.getOutputStream()));
                    output.flush();
                }
                /*
                 * Notify SocketReaderThread that it can now start.
                 */
                notifyReady();
            } catch (Exception e) {
                if (debugFlagIsSet(DEBUG_EXCEPTIONS)) {
                    e.printStackTrace();
                }
                /*
                 * This will notify the SocketReaderThread that it should exit.
                 */
                notifyReady();
            }
        }
    }

    class SocketReaderThread extends Thread {

        @Override
        public void run() {
            /*
             * Wait until the socket is set up before beginning to read.
             */
            waitForReady();
            /*
             * Now that the readerThread has started, it's safe to inform
             * the world that the socket is open, if in fact, it is open.
             * If used in conjunction with JavaFX, use Entry.deferAction()
             * when implementing this method to force it to run on the main
             * thread.
             */
            if (socketConnection != null && socketConnection.isConnected()) {
                onClosedStatus(Boolean.FALSE);
            }
            /*
             * Read from from input stream one line at a time
             */
            try {
                if (input != null) {
                    String line;
                    while ((line = input.readLine()) != null) {
                        if (debugFlagIsSet(DEBUG_IO)) {
                            System.out.println("recv> " + line);
                        }
                        /*
                         * The onMessage() method has to be implemented by
                         * a sublclass.  If used in conjunction with JavaFX,
                         * use Entry.deferAction() to force this method to run
                         * on the main thread.
                         */
                        onMessage(line);
                    }
                }
            } catch (Exception e) {
                if (debugFlagIsSet(DEBUG_EXCEPTIONS)) {
                    e.printStackTrace();
                }
            } finally {
                close();
            }
        }
    }
}
