package edu.skidmore.cs326.game.sudoku.persistence.connection;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import org.apache.log4j.Logger;

/**
 * Responsible for encrypting and decrypting the mySQL database password.
 * 
 * @author taylort
 */
public class PasswordEncryption {

    /**
     * The public key file.
     */
    private static final String PUBLIC_KEY_FILE =
        "src/config/public_key.der";

    /**
     * The private key file.
     */
    private static final String PRIVATE_KEY_FILE =
        "database/private_key.der";

    /**
     * The logger.
     */
    private static final Logger LOG;

    /**
     * Create the logger.
     */
    static {
        LOG = Logger.getLogger(PasswordEncryption.class);
    }

    // /**
    // * The public key file.
    // */
    // private static final String PRIVATE_KEY_FILE;
    //
    // /**
    // * The public key file.
    // */
    // private static final String PUBLIC_KEY_FILE;
    //
    // /**
    // * Setting the public key file
    // */
    // static {
    //
    // String publicKeyContent = null;
    //
    // try (InputStream input =
    // MySQLConfig.class.getResourceAsStream("src/config/public_key.der")) {
    // if (input == null) {
    // LOG.error("Unable to find public key");
    // System.exit(1);
    // }
    //
    // // Convert InputStream to String
    // publicKeyContent = convertInputStreamToString(input);
    // }
    // catch (IOException e) {
    // LOG.error("Error loading public key file", e);
    // }
    //
    // // Initialize PUBLIC_KEY_FILE even if an error occurred
    // PUBLIC_KEY_FILE = publicKeyContent != null ? publicKeyContent : "";
    // }
    //
    // /**
    // * Setting the public key file
    // */
    // static {
    // String privateKeyContent = null;
    //
    // try (InputStream input1 =
    // MySQLConfig.class.getResourceAsStream("private_key.der")) {
    // if (input1 == null) {
    // LOG.error("Unable to find private key");
    // System.exit(1);
    // }
    //
    // // Convert InputStream to String
    // privateKeyContent = convertInputStreamToString(input1);
    // }
    // catch (IOException e) {
    // LOG.error("Error loading private key file", e);
    // }
    //
    // // Initialize PUBLIC_KEY_FILE even if an error occurred
    // PRIVATE_KEY_FILE = privateKeyContent != null ? privateKeyContent : "";
    // }

    /**
     * Converts input stream into String.
     * 
     * @param inputStream
     * @return String
     * @throws IOException
     */
    public static String convertInputStreamToString(InputStream inputStream)
        throws IOException {
        // Create a StringBuilder to store the result
        StringBuilder stringBuilder = new StringBuilder();

        // Create a BufferedReader to read from the InputStream
        BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(inputStream));

        // Read each line from the InputStream and append it to the
        // StringBuilder
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        // Close the BufferedReader
        bufferedReader.close();

        // Return the result as a String
        return stringBuilder.toString();
    }

    /**
     * privatizing constructor.
     */
    private PasswordEncryption() {
    }

    /**
     * Encrypting the password using RSA encryption.
     * 
     * @param plainText
     * @return The encrypted password
     * @throws Exception
     */
    public static String encrypt(String plainText) throws Exception {
        PublicKey publicKey = loadPublicKey(PUBLIC_KEY_FILE);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * Decrypting the encrypted password using RSA decryption.
     * 
     * @param encryptedText
     * @return The decrypted password.
     * @throws Exception
     */
    public static String decrypt(String encryptedText) throws Exception {
        PrivateKey privateKey = loadPrivateKey(PRIVATE_KEY_FILE);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

    /**
     * Generates an RSA key pair.
     * 
     * @return the generated KeyPair
     * @throws Exception
     */
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * Saves the given key to the specified file.
     * 
     * @param key
     * @param fileName
     * @throws Exception
     */
    public static void saveKey(Key key, String fileName) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(key.getEncoded());
        }
        catch (IOException e) {
            LOG.error("Error saving key to file: " + fileName, e);
            throw e;
        }
    }

    /**
     * Loads the public key.
     *
     * @param fileName
     * @return the loaded PublicKey object
     * @throws Exception
     */
    public static PublicKey loadPublicKey(String fileName) throws Exception {
        try (InputStream is = new FileInputStream(fileName)) {
            byte[] encodedPublicKey = is.readAllBytes();
            X509EncodedKeySpec keySpec =
                new X509EncodedKeySpec(encodedPublicKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        }
        catch (IOException e) {
            LOG.error("Error loading public key from file: " + fileName, e);
            throw e;
        }
    }

    /**
     * Loads the private key from the specified file.
     * 
     * @param fileName
     * @return the loaded PrivateKey object.
     * @throws Exception
     */
    public static PrivateKey loadPrivateKey(String fileName) throws Exception {
        try (InputStream is = new FileInputStream(fileName)) {
            byte[] encodedPrivateKey = is.readAllBytes();
            PKCS8EncodedKeySpec keySpec =
                new PKCS8EncodedKeySpec(encodedPrivateKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        }
        catch (IOException e) {
            LOG.error("Error loading private key from file: " + fileName, e);
            throw e;
        }
    }
}
