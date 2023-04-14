import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
public class ClienteTCP {
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET="\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static void main(String[] args) throws Exception {

        System.out.println(ANSI_PURPLE+"---------------------------------------"+ANSI_RESET);
        System.out.println(ANSI_PURPLE+"----------BIENVENIDO A WORDLE----------"+ANSI_RESET);
        System.out.println(ANSI_PURPLE+"---------------------------------------"+ANSI_RESET);


        Socket clientSocket =null;
        BufferedReader inFromServer = null;
        DataOutputStream outToServer = null;

        Scanner scanner=new Scanner(System.in);



        boolean connected = false;


        while(true) {


            System.out.println(ANSI_CYAN+"---------------------------------------"+ANSI_RESET);
            System.out.println("Seleccione una opcion:");
            System.out.println("0. Conectar al servidor");
            System.out.println("1. Jugar una partida");
            System.out.println("2. Salir");
            System.out.println("Opcion: ");
            int opcion=scanner.nextInt();
            System.out.println(ANSI_CYAN+"---------------------------------------"+ANSI_RESET);

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
                    System.out.println(ANSI_PURPLE+"------------JUGANDO A WORDLE-----------"+ANSI_RESET);

                    System.out.println(ANSI_BLACK+"La palabra Tiene  "+wordLength+" Letras "+ANSI_RESET);
                    int intentos = 0;
                    String serverResponse;

                    while (intentos<6){//inicio
                        System.out.println(ANSI_CYAN+"---------------------------------------"+ANSI_RESET);

                        System.out.println("\nAdivina la palabra secreta: ");

                        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                        String clientGuess = inFromUser.readLine();
                        outToServer.writeBytes(clientGuess + "\n");
                         serverResponse = inFromServer.readLine();
                        System.out.println(serverResponse);

                        if(serverResponse.startsWith("Correcto")){
                            System.out.println("Ganaste en hora buena");

                                break;

                        }


                        intentos++;
                        System.out.println(ANSI_PURPLE+"Intentos  "+ANSI_RESET+intentos);
                        System.out.println(ANSI_CYAN+"---------------------------------------"+ANSI_RESET);


                        if (intentos==6){
                            System.out.println(ANSI_RED+"Lo siento, has alcanzado el límite de intentos. El juego ha terminado. "+ANSI_RESET);
                            System.exit(0);
                           // break;
                        }

                    }
                }else {
                    System.out.println(ANSI_RED+"Debes conectarte al servidor primero"+ANSI_RESET);

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






