import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTCP {
    public static void main(String[] args) throws Exception {
        Socket clientSocket =null;
        BufferedReader inFromServer = null;
        DataOutputStream outToServer = null;

        Scanner scanner=new Scanner(System.in);
//        System.out.println("Seleccione una opci贸n:");
//        System.out.println("0. conectar");
//        System.out.println("1. Jugar");
//        System.out.println("2. Salir");


        boolean connected = false;

        while(true) {
            System.out.println("Seleccione una opcion:");
            System.out.println("0. conectar");
            System.out.println("1. Jugar");
            System.out.println("2. Salir");

            int opcion=scanner.nextInt();
            if (opcion==0){
                if (!connected) {
                    clientSocket = new Socket("localhost", 8000);// Crear un objeto BufferedReader para leer datos del servidor
                    inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    // Crear un objeto DataOutputStream para enviar datos al servidor
                    outToServer = new DataOutputStream(clientSocket.getOutputStream());

                    connected =true;
                }else {
                    System.out.println("ya estas conectado");


                }


            }

            else if (opcion==1){
                if (connected){
                    int wordLength = Integer.parseInt(inFromServer.readLine());
                    System.out.println("la palabra tiene   "+wordLength+"   Letras  -----");

                    while (true){//inicio
                        System.out.println("\nAdivina una letra o la palabra: ");

                        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                        String clientGuess = inFromUser.readLine();
                        outToServer.writeBytes(clientGuess + "\n");
                        String serverResponse = inFromServer.readLine();
                        System.out.println(serverResponse);



                        if(serverResponse.startsWith("ganaste")) {
                            System.out.println("ganaste");
                            break;


                        }



                    }//final whild
                }else {
                    System.out.println("Debes conectarte al servidor primero");

                }



            } else if (opcion==2) {
                System.out.println("saliendo");
                break;


            }

        }

        // Cerrar los sockets y los streams
        outToServer.close();
        inFromServer.close();
        clientSocket.close();

    }}






