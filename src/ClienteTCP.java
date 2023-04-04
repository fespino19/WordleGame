import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTCP {
    public static final String ANSI_RESET="\u001B[0m";

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static void main(String[] args) throws Exception {
        int maxAttempts = 5; // número máximo de intentos permitidos
        int numAttempts = 0; // número actual de intentos
        System.out.println(ANSI_CYAN+"----------------------"+ANSI_RESET);
        System.out.println(ANSI_PURPLE+"BIENVENIDO A WORDLE"+ANSI_RESET);
        System.out.println(ANSI_CYAN+"----------------------"+ANSI_RESET);


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

            System.out.println(ANSI_CYAN+"----------------------"+ANSI_RESET);

            System.out.println("Seleccione una opcion:");
            System.out.println("0. Conectar al servidor");
            System.out.println("1. Jugar una partida");
            System.out.println("2. Salir");
            System.out.println("Opcion: ");
            int opcion=scanner.nextInt();

            System.out.println(ANSI_CYAN+"----------------------"+ANSI_RESET);

            if (opcion==0){
                if (!connected) {
                    clientSocket = new Socket("localhost", 8000);// Crear un objeto BufferedReader para leer datos del servidor
                    inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    // Crear un objeto DataOutputStream para enviar datos al servidor
                    outToServer = new DataOutputStream(clientSocket.getOutputStream());

                    connected =true;
                    System.out.println(ANSI_BLUE+"Coneccion con exito"+ANSI_RESET);

                }else {
                    System.out.println(ANSI_BLUE+"ya estas conectado"+ANSI_RESET);


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







                    }//final whild
                }else {
                    System.out.println("Debes conectarte al servidor primero");

                }



            } else if (opcion==2) {
                System.out.println("saliendo");
                break;


            }else {
                System.out.println("Opcion no valida");
            }




        }

        // Cerrar los sockets y los streams
        outToServer.close();
        inFromServer.close();
        clientSocket.close();

    }}






