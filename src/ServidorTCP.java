import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    public static void main(String[] args) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(8000);
        System.out.println("Waiting for client connection...");
        Socket connectionSocket = welcomeSocket.accept();
        System.out.println("Client connected: " +connectionSocket);




        String[] words = {"hello", "world", "miami", "table","award"};
        String chosenWord = words[(int)(Math.random()*words.length)];






        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

        GuessHandler guessHandler = new GuessHandler(chosenWord);

        outToClient.writeBytes(chosenWord.length() + "\n");



        while(!guessHandler.isWordGuessed()) {


            String clientGuess = inFromClient.readLine();
            System.out.println("Received guess: " + clientGuess);


            String serverResponse = guessHandler.handleGuess(clientGuess);
            outToClient.writeBytes(serverResponse + "\n");
            System.out.println("Server responde cl cliente con: " + serverResponse);

        }

        outToClient.close();
        inFromClient.close();
        connectionSocket.close();
        welcomeSocket.close();
    }
}

