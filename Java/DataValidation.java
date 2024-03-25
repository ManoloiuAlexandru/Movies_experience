import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Map;

public class DataValidation {
    public static boolean passwordCheck(String password) {
        boolean upper = false, lower = false, number = false;
        for (int i = 0; i < password.length(); i++) {
            if (StringUtils.isAllUpperCase(String.valueOf(password.charAt(i)))) {
                upper = true;
            } else if (StringUtils.isAllLowerCase(String.valueOf(password.charAt(i)))) {
                lower = true;
            } else if (StringUtils.isNumeric(String.valueOf(password.charAt(i)))) {
                number = true;
            }
        }
        return upper && lower && number && password.length() >= 8;
    }

    private static boolean validateUsername(String userName) {
        for (int i = 0; i < userName.length(); i++) {
            if (!StringUtils.isNoneBlank(String.valueOf(userName.charAt(i))))
                return false;
            else if (!(StringUtils.isAlphanumeric(String.valueOf(userName.charAt(i)))) && !"._".contains(String.valueOf(userName.charAt(i))))
                return false;

        }
        return true;
    }

    private static boolean validateDomain(String domain) {
        if (!domain.contains(".")) return false;
        for (int i = 0; i < domain.length(); i++) {
            if (!StringUtils.isNoneBlank(String.valueOf(domain.charAt(i)))) return false;
            else if (!(StringUtils.isAlphanumeric(String.valueOf(domain.charAt(i)))) && !".-".contains(String.valueOf(domain.charAt(i))))
                return false;
        }
        return true;
    }

    public static boolean validateEmail(String email) {
        if (!email.contains("@") || StringUtils.countMatches("@", email) > 1) {
            return false;
        } else {
            String userName = email.split("@")[0];
            String domain = email.split("@")[1];
            return validateUsername(userName) && validateDomain(domain);
        }

    }

    public static boolean cardValidation(String password) {
        int sumEven = 0, sumOdd = 0;
        for (int i = password.length() - 1; i > -1; i--) {
            if (i % 2 == 0 && StringUtils.isNumeric(String.valueOf(password.charAt(i)))) {
                int sumCf = Character.getNumericValue(password.charAt(i)) * 2;
                sumEven += sumCf % 10 + sumCf / 10;
            } else if (StringUtils.isNumeric(String.valueOf(password.charAt(i)))) {
                sumOdd += Character.getNumericValue(password.charAt(i));
            }
        }
        if ((sumEven + sumOdd) % 10 == 0) {
            return true;
        }
        return false;
    }

    public static boolean checkForDuplicateEmail(String email, ArrayList<Map<String, String>> listOfUsers) {
        for (Map<String, String> user : listOfUsers) {
            if (user.get("email").equals(email)) {
                return false;
            }
        }

        return true;
    }
}
