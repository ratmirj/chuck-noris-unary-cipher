package chucknorris;

public class CipherCharacter {

    public static String toBinaryString(char character){
         String binary = Integer.toBinaryString(character);
         return String.format("%7s", binary).replace(' ', '0');
    }

    public static char toChar(String binatryCharString){
         return (char) Integer.parseInt(binatryCharString, 2);
    }
}
