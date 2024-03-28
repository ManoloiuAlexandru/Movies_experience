import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Map;

public class Security {
    public static String generateRandomSalt() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public static void saltThePasswords(ArrayList<User> listOfUsers) {
        for (User user : listOfUsers) {
            user.setSalt(generateRandomSalt());
            user.setHash(user.getPassword() + String.valueOf(user.getSalt()).hashCode());
        }
    }

    public static void encryptEmail(ArrayList<User> listOfUsers) {
        int shifting = 5;
        for (User user : listOfUsers) {
            String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String email = user.getEmail().split("@")[0];
            StringBuilder encryptedEmail = new StringBuilder();
            for (int i = 0; i < email.length(); i++) {
                if (alphabet.contains(String.valueOf(email.charAt(i))))
                    encryptedEmail.append(alphabet.charAt((alphabet.indexOf(email.charAt(i)) + shifting) % 52));

                else
                    encryptedEmail.append(email.charAt(i));

            }
            encryptedEmail.append(user.getEmail().split("@")[1]);
            System.out.println(encryptedEmail);
        }
    }

}
