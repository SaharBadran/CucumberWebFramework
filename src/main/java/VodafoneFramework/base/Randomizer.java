package VodafoneFramework.base;

import java.nio.charset.Charset;
import java.util.Random;

public class Randomizer {

    public String randomPattern;
    public String number;
    public String hexaNumber;

    public Randomizer(int patternWidth) {
        this.number = generateRandomizedNumber();
        this.randomPattern = generateRandomizedPattern(patternWidth);
        this.hexaNumber = generateRandomizedHexaNumber();
    }

    static String generateRandomizedPattern(int patternWidth) {
        // length is bounded by 256 Character
        byte[] array = new byte[256];
        new Random().nextBytes(array);
        String randomString = new String(array, Charset.forName("UTF-8"));
        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer();
        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < randomString.length(); k++) {
            char ch = randomString.charAt(k);
            if (((ch >= 'a' && ch <= 'z')
                    || (ch >= 'A' && ch <= 'Z')
                    || (ch >= '0' && ch <= '9'))
                    && (patternWidth > 0)) {

                r.append(ch);
                patternWidth--;
            }
        }
        // return the resultant string
        return r.toString();
    }
    static String generateRandomizedNumber(){
        Random r = new Random();
        int low = 1;
        int high = 1000;
        int result = r.nextInt(high-low)+low;
        return String.valueOf(result);
    }
    static String generateRandomizedHexaNumber(){
        return String.format("%x",(Integer.valueOf(generateRandomizedNumber())));
    }
}
