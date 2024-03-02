package com.example.carecareforeldres.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

  // Déclaration de la clé secrète utilisée pour signer et vérifier les jetons JWT
  private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

  // Méthode pour extraire le nom d'utilisateur à partir du jeton JWT
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  // Méthode générique pour extraire toute réclamation spécifiée à partir du jeton JWT
  //**Hedhi methode generic bch tnajem t'extract minha bel haja bel haja
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  // Méthode pour générer un jeton JWT en utilisant les informations de l'utilisateur
  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  // Méthode surchargée pour générer un jeton JWT avec des réclamations supplémentaires
  public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // 24 heures d'expiration
            .signWith(getSignInKey(), SignatureAlgorithm.HS256) // Signature avec la clé secrète
            .compact();
  }

  // Méthode pour vérifier si un jeton JWT est valide pour un utilisateur donné
  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  // Méthode privée pour vérifier si un jeton JWT a expiré
  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  // Méthode privée pour extraire la date d'expiration d'un jeton JWT
  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  // Méthode privée pour extraire toutes les réclamations d'un jeton JWT
  private Claims extractAllClaims(String token) {
    return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
  }

  // Méthode privée pour obtenir la clé de signature à partir de la clé secrète
  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
