import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Kubish on 26.03.15.
 */
public class RLE {
    static String encodeRLE(String code) {
        String encode = "";
        char[] charcode = code.toCharArray();
        char currentsymbol = charcode[0];

        int count = 1;
        for (int i = 1; i < charcode.length; i++) {
            // symbol equ
            if (charcode[i] == currentsymbol) {
                count += 1;


            } else {


                if (count != 1) {
                    encode = encode + currentsymbol + "(" + count + ")";  //many symbol

                } else {
                    if (currentsymbol == '(') {    // find skobka
                        encode = encode + currentsymbol + "(" + count + ")";

                    } else encode = encode + currentsymbol;  // one symbol
                }
                currentsymbol = charcode[i];
                count = 1;

            }
            // end symbol
            if (i == charcode.length - 1) {
                if (count != 1) {
                    encode = encode + currentsymbol + "(" + count + ")";
                } else {
                    if (currentsymbol == '(') {    // find skobka
                        encode = encode + currentsymbol + "(" + count + ")";

                    } else encode = encode + currentsymbol;  // one symbol

                }
            }


        }
        return encode;
    }


    static String decodeRLE(String encode) {
        String decode = "";
        char[] charcode = encode.toCharArray();
        char currentsymbol = charcode[0];


        for (int i = 0; i < charcode.length - 1; i++) {
            if (charcode[i + 1] != '(') {
                decode = decode + currentsymbol;
                currentsymbol = charcode[i + 1];
            } else {
                if (charcode[i + 2] != '(') {
                    int size = Character.getNumericValue(charcode[i + 2]);
                    int count = 1;
                    while (count < size) {
                        decode = decode + currentsymbol;
                        count++;
                    }
                    i = i + 2;

                } else {
                    charcode[i + 1] = '°';
                    i--;
                }

            }


        }


        decode = decode + currentsymbol;


        char[] chardecode = decode.toCharArray();
        decode = "";
        for (int i = 0; i < chardecode.length; i++) {
            if (chardecode[i] == '°') chardecode[i] = '(';
            decode = decode + chardecode[i];
        }

        return decode;
    }


    public static void main(String argc[]) throws IOException {

        byte[] buffer = new byte[128];
        BufferedInputStream bufferedInput = new BufferedInputStream(new FileInputStream("G:\\filename.txt"));
        int bytesRead = 0;
        while ((bytesRead = bufferedInput.read(buffer)) != -1) {
            String code = new String(buffer, 0, bytesRead);
            System.out.println("\nSource string:");
            System.out.println(code);
            String encode = encodeRLE(code);
            System.out.println("\nEncode string:");
            System.out.println(encode);
            String decode = decodeRLE(encode);
            System.out.println("\nDecode string:");
            System.out.print(decode);
        }
        bufferedInput.close();

    }
}
