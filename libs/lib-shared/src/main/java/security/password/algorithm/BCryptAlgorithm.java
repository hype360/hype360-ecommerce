package security.password.algorithm;

import org.mindrot.jbcrypt.BCrypt;
import security.password.PasswordAlgorithm;

public class BCryptAlgorithm implements PasswordAlgorithm {

  @Override
  public String hashPassword(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }

  @Override
  public boolean verifyPassword(String password, String hash) {
    return BCrypt.checkpw(password, hash);
  }
}