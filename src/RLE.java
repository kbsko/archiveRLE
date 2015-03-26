/**
 * Created by Kubish on 26.03.15.
 */
public class RLE {
    static String encodeRLE(String code) {
        String encode = "";
        char[] charcode = code.toCharArray();
        char currentsymbol = charcode[0];
        for (int i = 0; i < charcode.length; i++) {
            if (charcode[i]=='(') charcode[i]='°';
        }

        int count = 1;
        for (int i = 1; i < charcode.length; i++) {
            if (charcode[i] == currentsymbol) {
                count += 1;

            } else {
                if (count != 1) {
                    encode = encode + currentsymbol + "(" + count + ")";
                } else encode = encode + currentsymbol;
                currentsymbol = charcode[i];
                count = 1;

            }

            if (i == charcode.length - 1) {
                if (count != 1) {
                    encode = encode + currentsymbol + "(" + count + ")";
                } else encode = encode + currentsymbol;
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
                if (charcode[i+2]!='(')
                {  
                    int size = Character.getNumericValue(charcode[i+2]);
                    int count=1;
                    while (count<size)
                    {
                        decode=decode+currentsymbol;
                        count++;
                    }
                    i=i+2;

                } else
                {
                    charcode[i+1]='°';
                    i--;
                }

            }

        }

        decode = decode + currentsymbol;

       //ekran
        char[] chardecode = decode.toCharArray();
        decode="";
        for (int i = 0; i < chardecode.length; i++) {
            if (chardecode[i]=='°') chardecode[i]='(';
            decode=decode+chardecode[i];
        }


   return decode; }


    public static void main(String argc[]) {
        String code = "I am sko(((oblaa( (4)";
        String encode =encodeRLE(code);
        System.out.println("Source string:");
        System.out.println(code);
        System.out.println("\nEncode string:");
        System.out.println(encode);
      String decode=decodeRLE(encode);
        System.out.println("\nDecode string:");
      System.out.print(decode);
    }
}
