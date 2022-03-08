

import java.net.*;
import java.io.*;

public class MsgingClient
{
    // initialize socket and input output streams
    private Socket socket            = null;
    private DataInputStream data_in = null;
    private DataOutputStream data_out = null;


    public static void main(String args[])
    {
        MsgingClient client = new MsgingClient("127.0.0.1", 5000);
    }


    // constructor to put ip address and port
    public MsgingClient(String address, int port)
    {
        // establish a connection
        try
        {
            socket = new Socket(address, port);
            System.out.println("\n***************************************************");
            System.out.println("BINGO! YOU'RE SUCCESSFULLY CONNECTED TO THE SERVER");
            System.out.println("***************************************************\n");
            System.out.println("Message:\n");

            // takes input from terminal
            data_in = new DataInputStream(System.in);

            // sends output to the socket
            data_out = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }

        // string to read message from input
        String msg = "";

        // keep reading until "bye" is input
        while (!msg.toLowerCase().equals("bye"))
        {
            try
            {
                msg = data_in.readLine();
                data_out.writeUTF(msg);
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }

        // close the connection
        try
        {
            data_in.close();
            data_out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }


}