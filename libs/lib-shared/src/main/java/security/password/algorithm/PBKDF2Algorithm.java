package security.password.algorithm;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import security.password.PasswordAlgorithm;

public class PBKDF2Algorithm implements PasswordAlgorithm {

  private final byte[] salt;

  public PBKDF2Algorithm(byte[] salt) {
    this.salt = salt;
  }

  @Override
  public String hashPassword(String password) {
    try {
      final int iterations = 65536;
      final int keyLength = 128;
      final String algorithm = "PBKDF2WithHmacSHA1";
      KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);
      SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
      byte[] hash = factory.generateSecret(spec).getEncoded();
      return Base64.getEncoder().encodeToString(hash);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean verifyPassword(String password, String hash) {
    return hashPassword(password).equals(hash);
  }
}