class GuessHandler {
    public static final String ANSI_RESET="\u001B[0m";
    public static final String ANSI_GREEN="\u001B[32m";
    public static final String ANSI_YELLOW="\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLACK = "\u001B[30m";


    private String chosenWord;
    private boolean[] wordGuessed;


    public GuessHandler(String chosenWord) {
        this.chosenWord = chosenWord;
        this.wordGuessed = new boolean[chosenWord.length()];

    }

    public String handleGuess(String guess) {
        boolean guessIsCorrect = false;



        for (int i = 0; i < chosenWord.length(); i++) {
            if (guess.length() == 1 && guess.charAt(0) == chosenWord.charAt(i)) {
                wordGuessed[i] = true;
                guessIsCorrect = true;
            } else if (guess.equals(chosenWord)) {
                wordGuessed[i] = true;
                guessIsCorrect = true;
                wordGuessed[i] = true;
            }
        }
        String  wordGuessedSoFar = checkWord(chosenWord,guess);


        String serverResponse;

        if (guessIsCorrect) {
                serverResponse = "Correcto! la palanra era:  " + wordGuessedSoFar;





        } else {



            serverResponse="Incorrecto. " + wordGuessedSoFar+":  Vuelve a intentarlo ";




        } if (guess.length() !=5){
            serverResponse = ANSI_RED+"solo se aceptas 5 caracteres"+ANSI_RESET;

        }

        return serverResponse;

    }
    public  String checkWord(String secretWord, String guess) {
        String correctLetters = "";
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == guess.charAt(i)) {// si la palabra es igual alas letras y ubicacion correcta este las imprime en verde
                correctLetters += ANSI_BLACK+ANSI_GREEN+ Character.toUpperCase(guess.charAt(i))+ANSI_RESET;// esta linea imprime en mayusculas laletras correctas
            } else if (secretWord.contains(String.valueOf(guess.charAt(i)))) {
                correctLetters += ANSI_YELLOW+Character.toLowerCase(guess.charAt(i))+ANSI_RESET;
            } else {
                correctLetters += "-";

            }
        }
        return correctLetters;
    }

    public boolean isWordGuessed() {
        for (boolean b : wordGuessed) {
            if (!b) {
                return false;
            }
        }
        return true;
    }


}
