package com.company;


        import java.io.DataInputStream;
        import java.io.DataOutputStream;
        import java.io.IOException;
        import java.net.InetAddress;
        import java.net.Socket;
        import java.net.UnknownHostException;
        import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("\n---------------------------------------------");
        System.out.println("POLL SYSTEM IMPLEMENTED WITH SOCKET PROGRAMMING");
        System.out.println("-----------------------------------------------\n\n");

        voter();
        result();

    }


    public static void voter(){
        try{
            // create an object of Scanner class for user input
            Scanner sok = new Scanner(System.in);

            // initialize port value
            int port = 7000;

            // getting localhost ip
            InetAddress ip = InetAddress.getByName("localhost");

            // establish the connection with server port
            Socket s_sok = new Socket(ip, port);


            // create an object of DataInputStream class for reading current votes
            DataInputStream inStream = new DataInputStream(s_sok.getInputStream());
            // create an object of DataOutputStream class
            DataOutputStream out = new DataOutputStream(s_sok.getOutputStream());

            int vote ;

            // display current votes of poll issue for cient
            System.out.println("*** CURRENT VOTES RESULTS ***");
            System.out.println("------------------------------");

            System.out.println("Yes: "+ inStream.readUTF());
            System.out.println("No : "+inStream.readUTF());
            System.out.println("i don't care : "+inStream.readUTF());

            System.out.println();





            do{

                System.out.println("**** TIME TO VOTE! ****");
                System.out.println("------------------------");

                System.out.println("\nYou're required to enter your vote \n(choose 1, 2, or 3)");
                // display poll question
                System.out.println("1. yes\n2. no\n3. i don't care\n");
                vote = sok.nextInt();




                switch(vote){
                    // now send vote to the server which store all type of votes
                    case 1 :
                        // now send vote to the server
                        out.writeUTF("yes");
                        break;

                    case 2 :
                        // now send vote to the server
                        out.writeUTF("no");
                        break;

                    case 3:
                        out.writeUTF("i don't care");
                        break;

                    default :
                        System.out.println("Invalid Choice");

                }

            }while(!(vote == 1 || vote == 2 || vote==3));

            s_sok.close();
            inStream.close();
            out.close();
        }
        catch(UnknownHostException u) {
            System.out.println(u);
        }
        catch(IOException i) {
            System.out.println(i);
        }

    }
    public static void result(){
        try{

            // create an object of Scanner class for user input
            Scanner input = new Scanner(System.in);

            // initialize port value
            int port = 7000;

            // getting localhost ip
            InetAddress ip = InetAddress.getByName("localhost");

            // establish the connection with server port
            Socket sok = new Socket(ip, port);


            // create an object of DataInputStream class for reading current votes
            DataInputStream inStream = new DataInputStream(sok.getInputStream());
            // create an object of DataOutputStream class
            DataOutputStream outStream = new DataOutputStream(sok.getOutputStream());

            int _vote ;
            String vote= "";

            // display current votes of poll issue for client
            System.out.println("**** RESULTS ****");
            System.out.println("------------------------------");

            System.out.println("Yes: "+ inStream.readUTF());
            System.out.println("No : "+inStream.readUTF());
            System.out.println("i don't care : "+inStream.readUTF());

            input.close();
            inStream.close();
            outStream.close();
        }
        catch(UnknownHostException u) {
            System.out.println(u);
        }
        catch(IOException i) {
            System.out.println(i);
        }

    }
}