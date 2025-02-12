package security.jwt;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class JwtConfig {

  @Value("${jwt.secret-key}")
  private String secretKey;

  @Value("${jwt.expired-at}")
  private long expirationTime;

  @Value("${jwt.refresh-token-expired-at}")
  private long refreshTokenExpirationTime;

}