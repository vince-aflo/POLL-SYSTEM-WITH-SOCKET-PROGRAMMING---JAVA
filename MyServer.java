package com.company;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class MyServer {


    public static void main(String[] args) throws IOException{

        // initialize port number where the server is created
        int port = 7000;

        // create an object of Socket class with port number
        ServerSocket server = new ServerSocket(port);



        // create an object Scanner class
        Scanner in = new Scanner(System.in);

        // Enter the Poll issue
        System.out.println("DO YOU WISH TO START THE POLL? yes/no ");
        String poll = in.nextLine();

        // running infinite loop for getting
        // client request
        while (true)
        {
            Socket sok = null;
            int voter = 0;
            try
            {
                // socket object to receive incoming client requests
                sok = server.accept();

                System.out.println("voter "+voter+" : " + sok);

                // obtaining input and out streams
                DataInputStream inStream = new DataInputStream(sok.getInputStream());
                DataOutputStream outStream = new DataOutputStream(sok.getOutputStream());

                System.out.println("Assigning new thread for this voter "+voter);

                // create a new thread object
                Thread t = new MultiClient(sok, inStream, outStream, in, poll);

                // Invoking the start() method
                t.start();

                in.close();
            }
            catch (Exception e){
                sok.close();
                e.printStackTrace();
            }

        }

    }
}


// MultiClient class
class MultiClient extends Thread
{
    final Scanner in;
    final DataInputStream inStream;
    final DataOutputStream outStream;
    final Socket sok;
    String poll;
    static int yes;
    static int no;
    static int dont_care;

    // Constructor
    public MultiClient(Socket s, DataInputStream inStream, DataOutputStream outStream, Scanner in, String poll)
    {
        this.sok = s;
        this.inStream = inStream;
        this.outStream = outStream;
        this.in = in;
        this.poll = poll;

    }

    @Override
    public void run()
    {

        try {



            // send current votes to the client for display purpose
            outStream.writeUTF(String.valueOf(yes));
            outStream.writeUTF(String.valueOf(no));
            outStream.writeUTF(String.valueOf(dont_care));


            // now send the question to the connected client
            outStream.writeUTF(poll);

            // get the poll response from the client

            // store response in variable
            String res = inStream.readUTF();

            // now increment votes according to client response
            if(res.compareToIgnoreCase("yes") == 0){

                // increment yes votes
                yes++;

            }
            else if(res.compareToIgnoreCase("no") == 0){

                // increment yes votes
                no++;

            }
            else if(res.compareToIgnoreCase("i don't care") == 0){
                dont_care++;
            }

            inStream.close();
            outStream.close();
            in.close();
        }catch(IOException i){

            System.out.println(i);
        }

    }
}
