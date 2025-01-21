package vault;

import encrypt.CaesarCipher;
import encrypt.Encryptor;
import exceptions.*;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * The PasswordVault implements Vault interface and adds new user or new site,
 * update site password, retrieve site password.
 */
public class PasswordVault implements Vault {
    private Map<String, User> userVault;
    private Encryptor encryptor;
    private Random random;

    /**
     * Constructor.
     */
    public PasswordVault( ) {
        userVault = new HashMap<>();
        encryptor = new CaesarCipher();
        random = new Random();
    }

    /**
     * Constructor.
     * @param e Encryptor object
     */
    public PasswordVault(Encryptor e) {
        this.encryptor = e;
    }

    /**
     * It validates if the username or site name matches the requirements.
     * @param name The input name.
     * @return True if matches. Otherwise, false.
     */
    private boolean validateName(String name) {
        String name_pattern = "^[a-z]{6,12}$";  // Only contains lowercase letter, length between 6 and 12
        Pattern namePattern = Pattern.compile(name_pattern);
        Matcher nameMatcher = namePattern.matcher(name);
        return nameMatcher.matches();
    }

    /**
     * It validates if the site password matches the requirements.
     * @param password The input site password.
     * @return True if matches. Otherwise, false.
     */
    private boolean validatePassword(String password) {
        // Check if length is between 6 and 15
        if(password.length() < 6 || password.length() > 15) {
            return false;
        }
        // at lease one letter and digit
        boolean containsLetter = false;
        boolean containsDigit = false;
        // at least one special
        boolean containsSpecial = false;
        for(char c : password.toCharArray()) {
            if( 32 <= c && c <= 126 ) {
                if(48 <= c && c <= 57) {
                    containsDigit = true;
                } else if(65 <= c && c <= 90) {
                    containsLetter = true;
                } else if(97 <= c && c <= 122) {
                    containsLetter = true;
                } else if(c == '!' || c == '@' || c == '#' || c == '$' || c == '%' || c == '^' || c == '&') {
                    containsSpecial = true;
                }
            } else {
                return false; // contains invalid character
            }
        }
        return containsLetter && containsDigit && containsSpecial;
    }


    /**
     * It adds the input name and password into User object.
     * @param username The username to be added
     * @param password The password to be associated with this user
     * @throws InvalidUsernameException The username is invalid.
     * @throws InvalidPasswordException The password is invalid.
     * @throws DuplicateUserException The username already exists.
     */
    @Override
    public void addNewUser(String username, String password) throws InvalidUsernameException,
            InvalidPasswordException,
            DuplicateUserException {
        // Check if the username is valid
        if (!validateName(username)) {
            throw new InvalidUsernameException();
        }
        // Check if the password is valid
        if (!validatePassword(password)) {
            throw new InvalidPasswordException();
        }
        // Check if the username already exists
        if (!userVault.isEmpty()) {
            if (userVault.containsKey(username)) {
                throw new DuplicateUserException();
            }
        }
        // Store username and password into User object
        userVault.put(username, new User(password));
    }

    /**
     * It adds the new site into user's sitePasswordMap.
     * @param username The username requesting the new site password
     * @param password Password for the username
     * @param siteName Name of the site for which the user is requesting a password
     * @return The generated password.
     * @throws DuplicateSiteException The site name already exists.
     * @throws UserNotFoundException The user doesn't exist.
     * @throws UserLockedOutException The user is blocked due to more than 3 times failed login.
     * @throws PasswordMismatchException The password is incorrect.
     * @throws InvalidSiteException The site name is invalid.
     */
    @Override
    public String addNewSite(String username, String password, String siteName)
            throws DuplicateSiteException,
            UserNotFoundException, UserLockedOutException,
            PasswordMismatchException, InvalidSiteException {

        String sitePassword = generateSitePassword();  // generate a site password
        // Check if the username exists
        if(!userVault.containsKey(username)) {
            throw new UserNotFoundException();
        }
        // Create a User object
        User user = userVault.get(username);
        // Check if the user is blocked
        if (!user.isBlocked()) {
            // Check if the password is correct
            if (!user.getPassword().equals(password)) {
                user.incrementLoginAttempts();
                throw new PasswordMismatchException();
            }
        } else
            throw new UserLockedOutException();

        // Reset login attempts to 0
        user.resetLoginAttempts();

        // Check if the site name already exists
        if(user.duplicateSite(siteName)) {
            throw new DuplicateSiteException();
        }

        // Check if the site name is valid
        if (validateName(siteName)) {
            // Encrypt the password
            String encryptedSitePassword = encryptor.encrypt(sitePassword);
            // Store the encrypted password into the User object
            user.setSitePassword(siteName, encryptedSitePassword);
        } else {
            throw new InvalidSiteException();
        }
        return sitePassword;
    }

    /**
     * It generates random password that meets all the requirements.
     * @return The generated password.
     */
    private String generateSitePassword() {
        while(true) {
            int length = 6 + random.nextInt(10); // Length between 6 - 15
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                // Only characters between 32 and 126 are considered
                sb.append((char)(32 + random.nextInt(95)));
            }
            String randomPassword = sb.toString();
            // Check if the password generated is valid
            if(validatePassword(randomPassword)) {
                return randomPassword;
            }
        }
    }

    /**
     * It updates the site password for a specific site and user.
     * @param username The username requesting the new site password
     * @param password Password for the username
     * @param siteName Name of the site for which the user is requesting a password
     * @return The updated password.
     * @throws SiteNotFoundException The site name doesn't exist.
     * @throws UserNotFoundException The username doesn't exist.
     * @throws UserLockedOutException The user is blocked.
     * @throws PasswordMismatchException The user's password is incorrect.
     */
    @Override
    public String updateSitePassword(String username, String password, String siteName)
            throws SiteNotFoundException, UserNotFoundException,
            UserLockedOutException, PasswordMismatchException {
        // Generate new password
        String newPassword = generateSitePassword();
        // Check if the username exists
        if (userVault.containsKey(username)) {
            // Create a User object
            User user = userVault.get(username);
            // Check if the site name exists
            if (!user.duplicateSite(siteName)) {
                throw new SiteNotFoundException();
            }
            // Check if the user is blocked
            if (!user.isBlocked()) {
                // Check is the password is correct
                if (!user.getPassword().equals(password)) {
                    user.incrementLoginAttempts(); // add 1 to the failed attempt counter
                    throw new PasswordMismatchException();
                }
            } else {
                throw new UserLockedOutException();
            }
            // Reset the loginAttempt to 0
            user.resetLoginAttempts();
            // Encrypt the updated password
            String encryptedNewPassword = encryptor.encrypt(newPassword);
            // Store the updated encrypted password
            user.updateSitePassword(siteName, encryptedNewPassword);
        } else {
            throw new UserNotFoundException();
        }
        return newPassword;
    }

    /**
     * It retrieves the site password.
     * @param username The username requesting the site password
     * @param password Password for the username
     * @param siteName Name of the site for which the user is requesting a password
     * @return The decrypted password.
     * @throws SiteNotFoundException The site name doesn't exist.
     * @throws UserNotFoundException The username doesn't exist.
     * @throws UserLockedOutException The user is blocked.
     * @throws PasswordMismatchException The user's password is incorrect.
     */
    @Override
    public String retrieveSitePassword(String username, String password, String siteName)
            throws SiteNotFoundException, UserNotFoundException,
            UserLockedOutException, PasswordMismatchException {
        // Check if the username exists
        User user;
        if (userVault.containsKey(username)) {
            // Create a User object
            user = userVault.get(username);
            // Check if the site name exists
            if (!user.duplicateSite(siteName)) {
                throw new SiteNotFoundException();
            }
            // Check if the user is blocked
            if (!user.isBlocked()) {
                // Check if the password is correct
                if (!user.getPassword().equals(password)) {
                    user.incrementLoginAttempts();
                    throw new PasswordMismatchException();
                }
            } else {
                throw new UserLockedOutException();
            }
            // Reset the loginAttempts to 0
            user.resetLoginAttempts();
        } else {
            throw new UserNotFoundException();
        }
        // Return the decrypted site password
        return encryptor.decrypt(user.getSitePassword(siteName));
    }

    /**
     * The User class stores user's password, site names and site passwords(in pair).
     */
    private static class User {
        private String password;
        private Map<String, String> sitePasswordMap;
        int loginAttempts;

        /***
         * Constructor.
         * @param password User's password.
         */
        public User(String password) {
            this.password = password;
            this.sitePasswordMap = new HashMap<>();
            this.loginAttempts = 0;
        }

        /**
         * Getter for user's password.
         * @return The user's password.
         */
        public String getPassword() {
            return password;
        }

        /**
         * Setter for site password.
         * @param site Site name.
         * @param sitePassword Site password.
         */
        public void setSitePassword(String site, String sitePassword) {
            sitePasswordMap.put(site, sitePassword);
        }

        /**
         *  Getter for site password.
         * @param site Site name.
         * @return The site password.
         */
        public String getSitePassword(String site) {
            return sitePasswordMap.get(site);
        }

        /**
         * Updates the site password.
         * @param site Site name.
         * @param sitePassword The updated site password.
         */
        public void updateSitePassword(String site, String sitePassword) {
            sitePasswordMap.replace(site, sitePassword);
        }

        /**
         * Checks if the site name already exists.
         * @param site Site name.
         * @return True if it exists.Otherwise, false.
         */
        public boolean duplicateSite (String site) {
            return sitePasswordMap.containsKey(site);
        }

        /**
         * Resets the login attempts to 0.
         */
        public void resetLoginAttempts() {
            loginAttempts = 0;
        }

        /**
         * Increments the login attempts by 1.
         */
        public void incrementLoginAttempts() {
            loginAttempts++;
        }

        /**
         * Checks if the user fails 3 times to login.
         * @return True if the user is blocked. Otherwise, false.
         */
        public boolean isBlocked() {
            return loginAttempts >= 3;
        }
    }
}
