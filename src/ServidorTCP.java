import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    private boolean acertado = true;
    private static final int PORT = 8000;
    private static final int MAX_ATTEMPTS = 5;
    private static final String[] words = {"melon", "fresa", "mango", "peras","pipas"};
    public static void main(String[] args) throws Exception {


        try {
            ServerSocket welcomeSocket = new ServerSocket(PORT);

            System.out.println("Wordle server started on port " + PORT);
            System.out.println("Waiting for client connection...");

            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Client connected: " +connectionSocket);

            while (true){

                String chosenWord = words[(int)(Math.random()*words.length)];
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                GuessHandler guessHandler = new GuessHandler(chosenWord);
                outToClient.writeBytes(chosenWord.length() + "\n");





                while (!guessHandler.isWordGuessed()){

                  String clientGuess = inFromClient.readLine();
                    System.out.println("Received guess: " + clientGuess);

                    String serverResponse = guessHandler.handleGuess(clientGuess);
                    outToClient.writeBytes(serverResponse + "\n");
                    System.out.println("Server envia a cliente: " + serverResponse);


                }
                /*outToClient.close();
                inFromClient.close();
                connectionSocket.close();
                welcomeSocket.close();*/


            }
        }catch (IOException e) {
            System.err.println("Error: " + e.getMessage());

        }}}