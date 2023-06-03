package chucknorris;

import chucknorris.exceptions.InvalidCipherException;

public class CipherString {

    private static void validateCipherString(String cipherString) throws InvalidCipherException {
        boolean wrongChunkSize = cipherString.length() % 7 > 0;
        if(wrongChunkSize){
            throw new InvalidCipherException("Encoded string is not valid.");
        }
    }

    public static String toASCIIString(String string) throws InvalidCipherException {
        validateCipherString(string);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < string.length(); i+=7){
            result.append(CipherCharacter.toChar(string.substring(i, i+7)));
        }
        return result.toString();
    }


    public static String toBinaryString(String string){
        StringBuilder binaryMessage = new StringBuilder();
        for(int i = 0; i < string.length(); i++){
            binaryMessage.append(CipherCharacter.toBinaryString(string.charAt(i)));
        }
        return binaryMessage.toString();
    }
}
