package security.password.algorithm;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import security.password.PasswordAlgorithm;

public class Argon2Algorithm implements PasswordAlgorithm {

  private final Argon2 argon2;

  public Argon2Algorithm() {
    this.argon2 = Argon2Factory.create();
  }

  @Override
  public String hashPassword(String password) {
    return argon2.hash(2, 65536, 1, password.toCharArray());
  }

  @Override
  public boolean verifyPassword(String password, String hash) {
    return argon2.verify(hash, password.toCharArray());
  }
}