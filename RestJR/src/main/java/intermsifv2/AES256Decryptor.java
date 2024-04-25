package intermsifv2;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;

public class AES256Decryptor {
    private static final String AES_ALGORITHM = "AES";
    private static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String HASH_ALGORITHM = "SHA-256";

    // Método para generar una clave secreta a partir de una contraseña
    private static SecretKeySpec generateSecretKey(String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
        byte[] keyBytes = digest.digest(password.getBytes());
        return new SecretKeySpec(keyBytes, AES_ALGORITHM);
    }

    // Método para desencriptar una contraseña cifrada con AES-256
    public String decrypt(String encryptedPassword, String password) {
        try {
            byte[] combinedBytes = Base64.getDecoder().decode(encryptedPassword);
            byte[] ivBytes = new byte[16]; // El vector de inicialización debe tener 16 bytes
            byte[] encryptedBytes = new byte[combinedBytes.length - ivBytes.length];
            System.arraycopy(combinedBytes, 0, ivBytes, 0, ivBytes.length);
            System.arraycopy(combinedBytes, ivBytes.length, encryptedBytes, 0, encryptedBytes.length);

            SecretKeySpec secretKeySpec = generateSecretKey(password);
            Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(ivBytes));

            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            return new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace(); // Registra la excepción en el registro de errores
            return null;
        }
    }
}