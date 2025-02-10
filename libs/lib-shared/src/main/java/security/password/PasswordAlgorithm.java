package security.password;

public interface PasswordAlgorithm {

  String hashPassword(String password);

  boolean verifyPassword(String password, String hash);
}