package com.company;



import java.net.*;
        import java.io.*;

public class MsgServer
{
    //initialize socket and input stream
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       =  null;

    public static void main(String args[])
    {
        MsgServer server = new MsgServer(5000);
    }

    // constructor with port
    public MsgServer(int port)
    {
        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(port);
            System.out.println("\n*********************************************");
            System.out.println("                 SERVER STARTED                ");
            System.out.println("***********************************************\n");

            System.out.println("waiting for client connection ...");

            socket = server.accept();
            System.out.println("CLIENT CONNECTION FOUND\n\nClient:");

            // takes input from the client socket
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            String msg = "";

            // reads message from client until "bye" is sent
            while (!msg.toLowerCase().equals("bye"))
            {
                try
                {
                    msg = in.readUTF();
                    System.out.println("       "+ msg);

                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
            System.out.println("\n****************");
            System.out.println("CLOSING CONNECTION\n");

            // close connection
            socket.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }


}
