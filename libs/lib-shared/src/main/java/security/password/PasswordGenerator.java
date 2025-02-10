package security.password;

import java.security.SecureRandom;

/**
 * A utility class for generating passwords based on specified patterns.
 */
public class PasswordGenerator {

  private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
  private static final String DIGITS = "0123456789";
  private static final String SYMBOLS = "!@#$%^&*()-_=+[]{}|;:,.<>?";

  private final SecureRandom random = new SecureRandom();
  private final int length;
  private final boolean withUppercase;
  private final boolean withLowercase;
  private final boolean withDigits;
  private final boolean withSymbols;

  private PasswordGenerator(Builder builder) {
    this.length = builder.length;
    this.withUppercase = builder.withUppercase;
    this.withLowercase = builder.withLowercase;
    this.withDigits = builder.withDigits;
    this.withSymbols = builder.withSymbols;
  }

  /**
   * Generates a password based on the configured options.
   *
   * @return the generated password.
   * @throws IllegalArgumentException if no character categories are selected or length is invalid.
   */
  public String generatePassword() {
    if (length <= 0) {
      throw new IllegalArgumentException("Password length must be greater than 0");
    }

    StringBuilder password = new StringBuilder(length);
    StringBuilder charCategories = new StringBuilder();

    if (withUppercase) {
      charCategories.append(UPPERCASE);
    }
    if (withLowercase) {
      charCategories.append(LOWERCASE);
    }
    if (withDigits) {
      charCategories.append(DIGITS);
    }
    if (withSymbols) {
      charCategories.append(SYMBOLS);
    }

    if (charCategories.length() == 0) {
      throw new IllegalArgumentException("At least one character category must be selected");
    }

    for (int i = 0; i < length; i++) {
      int position = random.nextInt(charCategories.length());
      password.append(charCategories.charAt(position));
    }

    return password.toString();
  }

  /**
   * Builder class for PasswordGenerator.
   */
  public static class Builder {

    private int length;
    private boolean withUppercase;
    private boolean withLowercase;
    private boolean withDigits;
    private boolean withSymbols;

    /**
     * Sets the length of the password.
     *
     * @param length the length of the password.
     * @return the Builder instance.
     */
    public Builder withLength(int length) {
      this.length = length;
      return this;
    }

    /**
     * Sets whether to include uppercase letters in the password.
     *
     * @param withUppercase true to include uppercase letters, false otherwise.
     * @return the Builder instance.
     */
    public Builder withUppercase(boolean withUppercase) {
      this.withUppercase = withUppercase;
      return this;
    }

    /**
     * Sets whether to include lowercase letters in the password.
     *
     * @param withLowercase true to include lowercase letters, false otherwise.
     * @return the Builder instance.
     */
    public Builder withLowercase(boolean withLowercase) {
      this.withLowercase = withLowercase;
      return this;
    }

    /**
     * Sets whether to include digits in the password.
     *
     * @param withDigits true to include digits, false otherwise.
     * @return the Builder instance.
     */
    public Builder withDigits(boolean withDigits) {
      this.withDigits = withDigits;
      return this;
    }

    /**
     * Sets whether to include symbols in the password.
     *
     * @param withSymbols true to include symbols, false otherwise.
     * @return the Builder instance.
     */
    public Builder withSymbols(boolean withSymbols) {
      this.withSymbols = withSymbols;
      return this;
    }

    /**
     * Builds and returns a PasswordGenerator instance.
     *
     * @return a new PasswordGenerator instance.
     */
    public PasswordGenerator build() {
      return new PasswordGenerator(this);
    }
  }
}