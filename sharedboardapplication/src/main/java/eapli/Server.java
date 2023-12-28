package eapli;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Server{

    public static void main(String[] args) {

        ArrayList<byte[]> pass = new ArrayList<>();
        byte[] asciiCodes;
        for (int i = 0; i < 3; i++) {
            String username = "joao" + i;
            String password = "joao" + i;
            String data = username + '\0' + password;
            asciiCodes = data.getBytes();
            pass.add(i, asciiCodes);
        }


        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("Server started. Waiting for client connection...");

            while (true) {


                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");


                // Create input and output streams for communication with the client
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                DataOutputStream sOut = new DataOutputStream(clientSocket.getOutputStream());
                DataInputStream sIn = new DataInputStream(clientSocket.getInputStream());

                int size = sIn.read();
                byte[] auth = new byte[size];

                boolean flag = false;
                sIn.read(auth, 0, size);

                for (byte[] b:pass) {
                    if(Arrays.equals(auth,b)){
                        flag=true;
                    }
                }

                if (flag){
                    sOut.write(2);
                }else{
                    sOut.write(3);
                }

                String answer;
                while (!Objects.equals(answer = in.readLine(), "exit")) {
                    if (answer.equals("Server")) {
                        System.out.print("Enter your version: ");
                        int version=Integer.parseInt(userInput.readLine());
                        sOut.write(version);

                        System.out.print("Enter your code(0,1,4): ");
                        int code=Integer.parseInt(userInput.readLine());
                        sOut.write(code);

                        if (code ==4){


                            System.out.print("Enter your datalength1: ");
                            int datalength1=Integer.parseInt(userInput.readLine());
                            sOut.write(datalength1);

                            System.out.print("Enter your datalength2: ");
                            int datalenght2=Integer.parseInt(userInput.readLine());
                            sOut.write(datalenght2);

                            System.out.println("Username:");
                            String username1= userInput.readLine();
                            System.out.println("Password:");
                            String password1=userInput.readLine();
                            String auth1 = username1 + '\0' + password1;
                            sOut.write(auth1.length());

                            byte[]data1=auth1.getBytes();

                            sOut.write(data1);
                        }

                        int codemessage=sIn.read();


                        switch (codemessage){

                            case 2:
                                System.out.println("Success");
                                if (code==1){
                                    in.close();
                                    out.close();
                                    clientSocket.close();
                                    serverSocket.close();
                                }
                                break;
                            case 3:
                                if (code==4){
                                    int errsize= sIn.read();
                                    byte[]error=new byte[errsize];
                                    sIn.read(error, 0, errsize);
                                    String why = new String(error);
                                    System.out.println("Error "+why);
                                }else {
                                    System.out.println("Error");
                                }
                                break;
                            default:
                                break;


                        }
                    } else {
                        int version = sIn.read();
                        long unsignedVersion = version & 0xFFFFFFFFL;
                        int code = sIn.read();
                        long unsignedCode = code & 0xFFFFFFFFL;
                        long unsignedDatalength=0;
                        long unsignedDatalenght2;
                        int datalength2;
                        long unsignedDatalenght1;
                        int datalength1;
                        if (unsignedCode == 4) {
                            datalength1 = sIn.read();
                            unsignedDatalenght1 = datalength1 & 0xFFFFFFFFL;
                            datalength2 = sIn.read();
                            unsignedDatalenght2 = datalength2 & 0xFFFFFFFFL;
                            unsignedDatalength = unsignedDatalenght1 + 256 * unsignedDatalenght2;
                        }



                        switch ((int) unsignedCode) {

                            case 0:
                                System.out.println("Test Communication");
                                sOut.write(2);
                                break;
                            case 1:
                                System.out.println("Disconn");
                                sOut.write(2);
                                in.close();
                                out.close();
                                clientSocket.close();
                                serverSocket.close();
                                break;
                            case 4:
                                int size1 = sIn.read();
                                byte[] auth1 = new byte[size1];

                                boolean flag1 = false;
                                sIn.read(auth1, 0, size1);

                                for (byte[] b:pass) {
                                    if (Arrays.equals(auth1, b)) {
                                        flag1 = true;
                                        break;
                                    }
                                }

                                if (flag1){
                                    sOut.write(2);
                                }else{
                                    sOut.write(3);
                                    sOut.write((int) unsignedDatalength);
                                    String why="the user does not exist";
                                    byte[] datasend=why.getBytes();
                                    sOut.write(datasend);
                                }

                                break;
                            default:
                                sOut.write(3);
                                break;
                        }
                    }

                }
                in.close();
                out.close();
                clientSocket.close();
            }



        }catch (IOException e){
            e.printStackTrace();
        }


    }



}
