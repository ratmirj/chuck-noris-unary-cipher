package chucknorris;
import chucknorris.exceptions.InvalidCipherException;

import java.util.Scanner;


public class EncoderApp {

    private boolean isRunning;

    public void start() {
        isRunning = true;
        processInput();
    }

    private void processInput() {
        Scanner scanner = new Scanner(System.in);
        Encryptor encryptor = new Encryptor();
        while (isRunning) {
            System.out.println("Please input operation (encode/decode/exit):");
            String command = scanner.nextLine();

            switch (command) {
                case "encode" -> {
                    System.out.println("Input string:");
                    String message = scanner.nextLine();
                    System.out.printf("Encoded string:\n%s\n\n", encryptor.encodeMessage(message));
                }
                case "decode" -> {
                    try {
                        System.out.println("Input encoded string:");
                        String encodedMessage = scanner.nextLine();
                        System.out.printf("Decoded string:\n%s\n\n", encryptor.decodeMessage(encodedMessage));
                    } catch (InvalidCipherException e) {
                        System.out.print("Encoded string is not valid.\n\n");
                    }
                }
                case "exit" -> {
                    System.out.println("Bye!");
                    isRunning = false;
                }
                default -> {
                    System.out.printf("There is no '%s' operation\n\n", command);
                }
            }


        }
    }
}
