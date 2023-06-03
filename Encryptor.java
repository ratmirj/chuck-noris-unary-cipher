package chucknorris;

import chucknorris.exceptions.InvalidCipherException;

import java.util.Objects;

public class Encryptor {

    public String encodeMessage(String message){
        return binaryStringToEncodedString(CipherString.toBinaryString(message));
    }

    public String decodeMessage(String message) throws InvalidCipherException {
        return CipherString.toASCIIString(encodedStringToBinaryString(message));
    }

    private void validateEncodedString(String message) throws InvalidCipherException {
        boolean validSymbols = message.matches("^[0 ]+$");
        boolean validStart = message.startsWith("0 ") || message.startsWith("00");
        boolean evenBlocks = message.split(" ").length % 2 == 0;

        if (!(validStart && validSymbols && evenBlocks)){
            throw new InvalidCipherException("Encoded string is not valid.");
        }

    }

    private String encodedStringToBinaryString(String message) throws InvalidCipherException {
        validateEncodedString(message);
        String[] arrayMessage = message.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i  = 0; i < arrayMessage.length; i+=2){
            switch(arrayMessage[i]){
                case "0" -> result.append(arrayMessage[i+1].replace("0", "1"));
                case "00" -> result.append(arrayMessage[i+1]);
                default -> throw new InvalidCipherException("Chunk start not valid");
            }
        }
        return result.toString();
    }

    private String binaryStringToEncodedString(String binaryStringRepresentation){

        StringBuilder result = new StringBuilder();
        StringBuilder currentSequence = new StringBuilder();

        char currentSequenceCharacter = ' ';

        for (int i = 0; i < binaryStringRepresentation.length(); i++) {
            if(currentSequenceCharacter != binaryStringRepresentation.charAt(i)){
                if(!currentSequence.isEmpty()){
                    currentSequence.append(' ');
                }
                result.append(currentSequence);
                currentSequenceCharacter = binaryStringRepresentation.charAt(i);
                currentSequence = new StringBuilder();
                switch(currentSequenceCharacter){
                    case '1' -> currentSequence.append("0 0");
                    case '0' -> currentSequence.append("00 0");
                }
            }
            else{
                currentSequence.append('0');
            }

        }
        result.append(currentSequence);
        return result.toString();
    }

}