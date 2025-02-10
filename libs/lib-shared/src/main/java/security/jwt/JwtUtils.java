package security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import java.util.Date;
import java.util.Map;

/**
 * Utility class for handling JWT operations such as generating, validating, and extracting claims
 * from JWT tokens.
 */
public class JwtUtils {

  private static JwtUtils instance;

  private JwtUtils() {
    // Private constructor to prevent instantiation
  }

  /**
   * Returns the singleton instance of JwtUtils.
   *
   * @return the singleton instance of JwtUtils
   */
  public static JwtUtils getInstance() {
    if (instance == null) {
      instance = new JwtUtils();
    }
    return instance;
  }

  /**
   * Generates a JWT token.
   *
   * @param secret     the secret key used to sign the token
   * @param claims     the claims to be included in the token payload
   * @param expiration the expiration time in milliseconds
   * @return the generated JWT token as a String
   */
  public String generateJwtToken(String secret, Map<String, Object> claims, long expiration) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.create()
        .withPayload(claims)
        .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
        .sign(algorithm);
  }

  /**
   * Validates a JWT token.
   *
   * @param token  the JWT token to be validated
   * @param secret the secret key used to sign the token
   * @return true if the token is valid, false otherwise
   */
  public boolean validateJwtToken(String token, String secret) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      JWTVerifier verifier = JWT.require(algorithm).build();
      verifier.verify(token);
      return true;
    } catch (JWTVerificationException exception) {
      return false;
    }
  }

  /**
   * Extracts claims from a JWT token.
   *
   * @param token  the JWT token from which claims are to be extracted
   * @param secret the secret key used to sign the token
   * @return a Map of claims extracted from the token
   */
  public Map<String, Claim> getClaimsFromJwtToken(String token, String secret) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    JWTVerifier verifier = JWT.require(algorithm).build();
    DecodedJWT jwt = verifier.verify(token);
    return jwt.getClaims();
  }

  /**
   * Checks if a JWT token is expired.
   *
   * @param token  the JWT token to be checked
   * @param secret the secret key used to sign the token
   * @return true if the token is expired, false otherwise
   */
  public boolean isJwtTokenExpired(String token, String secret) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      JWTVerifier verifier = JWT.require(algorithm).build();
      DecodedJWT jwt = verifier.verify(token);
      return jwt.getExpiresAt().before(new Date());
    } catch (JWTVerificationException exception) {
      return true;
    }
  }

  /**
   * Checks if a JWT token is still valid (not expired and valid signature).
   *
   * @param token  the JWT token to be checked
   * @param secret the secret key used to sign the token
   * @return true if the token is still valid, false otherwise
   */
  public boolean isJwtTokenStillValid(String token, String secret) {
    return validateJwtToken(token, secret) && !isJwtTokenExpired(token, secret);
  }
}