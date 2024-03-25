import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Map;

public class Security {
    public static String generateRandomSalt() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public static void saltThePasswords(ArrayList<Map<String, String>> listOfUsers) {
        for (Map<String, String> user : listOfUsers) {
            user.put("salt", generateRandomSalt());
            user.put("hash", user.get("password") + String.valueOf(user.get("salt")).hashCode());
        }
    }

    public static void encryptEmail(ArrayList<Map<String, String>> listOfUsers) {
        int shifting = 5;
        for (Map<String, String> user : listOfUsers) {
            String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String email = user.get("email").split("@")[0];
            StringBuilder encryptedEmail = new StringBuilder();
            for (int i = 0; i < email.length(); i++) {
                if (alphabet.contains(String.valueOf(email.charAt(i))))
                    encryptedEmail.append(alphabet.charAt((alphabet.indexOf(email.charAt(i)) + shifting) % 52));

                else
                    encryptedEmail.append(email.charAt(i));

            }
            encryptedEmail.append(user.get("email").split("@")[1]);
            System.out.println(encryptedEmail);
        }
    }

}
