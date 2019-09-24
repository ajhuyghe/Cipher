import java.util.Arrays;

/**
 * Prog3Cipher - uses a cipher table to encode a message then decodes the encoded message
 * <p>
 * author@ Alexander Huyghe
 * <p>
 * Date modified: 9/21/2019
 */
public class Prog3Cipher {
    char[] keyList; // this stores the key which is used when encrypting messages
    char[][] cipherTable; // this is used to encypt messages

    /**
     * encode - encodes a message using cipherTable*
     *
     * @param message
     * @return
     */
    String encode(String message) {         // this uses the cipherTable to encrypt a message
        String result = "";

        int j = -1;
        for (int i = 0; i < message.length(); i++) {
            j++;
            if (j > keyList.length - 1) {
                j = 0;
            }
            if ((int) (Character.toUpperCase(message.charAt(i))) - 65 < 0) { // this will print a space if it detects a space in message
                System.out.print(" ");
            } else if ((int) (Character.toUpperCase(message.charAt(i))) - 65 >= 0) { // this uses the cipherTable to encrypt a message
                int num = (int) (Character.toUpperCase(message.charAt(i))) - 65;
                System.out.print(cipherTable[num][keyList[j] - 65]);
            }
        }
        return result;
    }


    /**
     * METHOD DESCRIPTION COMMENT
     * <p>
     * decode - decodes a message
     *
     * @param message
     * @return
     */
    String decode(String message) {
        String result = "";

        int num = 0;
        for (int i = 0; i < message.length(); i++) { // this repeats for the length of message

            if (num > keyList.length - 1) {
                num = 0;
            }
            int j = (keyList[num] - 65); // this turns a letter into an index

            if (message.charAt(i) == ' ') { // this will print a space if it detects a space in message
                System.out.print(" ");
            }

            for (int l = 0; l < cipherTable.length; l++) { // this for loop uses the column found with keyList and finds the letter in the message then uses the row value as a letter
                int index = cipherTable[l][j];

                if (index == message.charAt(i)) {
                    char out = (char) (l + 65);
                    System.out.print(out);
                }

            }
            num++;


        }
        return result;

    }

    /**
     * CONSTRUCTOR DESCRIPTION COMMENT
     * <p>
     * Prog3Cipher - makes a cipherTable with given param
     *
     * @param code
     * @param key
     */
    public Prog3Cipher(char code, String key) {

        cipherTable = new char[26][26];

        keyList = key.toCharArray();

        for (int row = 0; row < cipherTable.length; row++) {       // this for loop is used to create a 26 by 26 table used for encrypting messages

            if (code > 'Z') {            // this resets the row to A if it is one integer above Z

                code -= 26;
            }

            for (int column = 0; column < cipherTable[row].length; column++) {

                if (code > 'Z') {                // this resets the column to A if it is one integer above Z

                    code = 'A';
                }
                cipherTable[row][column] = code++;
                System.out.print(cipherTable[row][column] + " ");

            }
            code++;
            System.out.println();
        }
    }

    /**
     * METHOD DESCRIPTION COMMENT
     * <p>
     * main - tests program
     *
     * @param args
     */
    public static void main(String[] args) {
        Prog3Cipher self = new Prog3Cipher('H', "BABBAGE");
        assert "PHXXF MQYBPKNJ".equals(self.encode("Happy Birthday"));
        assert "HAPPY BIRTHDAY".equals(self.decode("PHXXF MQYBPKNJ"));
        System.out.println(self.encode("happy birthday"));
        System.out.println(self.decode("PHXXF MQYBPKNJ"));
    }
}