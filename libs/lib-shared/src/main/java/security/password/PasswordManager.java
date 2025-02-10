package security.password;

import security.password.algorithm.BCryptAlgorithm;

/**
 * Singleton class for managing password hashing and verification.
 */
public class PasswordManager {

  private static PasswordManager instance;
  private PasswordAlgorithm algorithm;

  private PasswordManager() {
    this.algorithm = new BCryptAlgorithm();
  }

  /**
   * Returns the singleton instance of PasswordManager.
   *
   * @return the singleton instance of PasswordManager
   */
  public static PasswordManager getInstance() {
    if (instance == null) {
      instance = new PasswordManager();
    }
    return instance;
  }

  /**
   * Sets the password hashing algorithm to be used.
   *
   * @param algorithm the password hashing algorithm
   */
  public void setAlgorithm(PasswordAlgorithm algorithm) {
    this.algorithm = algorithm;
  }

  /**
   * Hashes the given password using the configured algorithm.
   *
   * @param password the password to be hashed
   * @return the hashed password
   */
  public String hashPassword(String password) {
    return algorithm.hashPassword(password);
  }

  /**
   * Verifies the given password against the given hash using the configured algorithm.
   *
   * @param password the password to be verified
   * @param hash     the hash to verify the password against
   * @return true if the password matches the hash, false otherwise
   */
  public boolean verifyPassword(String password, String hash) {
    return algorithm.verifyPassword(password, hash);
  }
}