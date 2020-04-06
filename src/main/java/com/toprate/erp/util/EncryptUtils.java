package com.toprate.erp.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;
import java.util.Base64;

public class EncryptUtils {
    private static String UPLOAD_ENCRYPT_KEY = "12345678";

    public static String decryptFileUploadPath(String encryptedPath) throws Exception {
        //return decrypt(encryptedPath, UPLOAD_ENCRYPT_KEY);
        return new String(Base64.getDecoder().decode(encryptedPath));
    }

    public static String encryptFileUploadPath(String path) throws Exception {
        //return encrypt(path, UPLOAD_ENCRYPT_KEY);
        return Base64.getEncoder().encodeToString(path.getBytes());
    }


    public static String encrypt(String decryptMessage, String encryptKey) throws Exception {
        try {
            byte[] shaKey = Arrays.copyOf(encryptKey.getBytes(), 16);
            SecretKeySpec key = new SecretKeySpec(shaKey, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] input = decryptMessage.getBytes();
            byte[] encryptedByte = cipher.doFinal(input);

            return Base64.getEncoder().encodeToString(encryptedByte);
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException ex) {
            throw new Exception(ex);
        }
    }

    public static String decrypt(String encryptedMessage, String encryptKey) throws Exception {

        try {
            byte[] shaKey = Arrays.copyOf(encryptKey.getBytes(), 16);
            SecretKeySpec key = new SecretKeySpec(shaKey, "AES");
            Cipher cipher;

            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");

            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] input = Base64.getDecoder().decode(encryptedMessage);
            byte[] encryptedByte = cipher.doFinal(input);
            return new String(encryptedByte);
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException ex) {
            throw new Exception(ex);
        }

    }


}
