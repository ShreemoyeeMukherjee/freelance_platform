package com.example.freelance_platform.service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.management.RuntimeErrorException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.freelance_platform.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.lang.Object;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class JwtService {

    private String secretKey = "fjksjjjjjjjjjjjjjjjjjjjjjjjjjjjjhugygvggdcdfxchfxfhhffhhhhjfffffffffffffffffffgfgncgcjcjjcjcjcjjjjjjjjjgr243444";
    // public JwtService()
    // {
    //     try{
    //       KeyGenerator keyGen  =  KeyGenerator.getInstance("HmacSHA256");
    //       SecretKey secretKeyBKey = keyGen.generateKey();
    //       secretKey = Base64.getEncoder().encodeToString(secretKeyBKey.getEncoded());
    //     }
    //     catch(NoSuchAlgorithmException e)
    //     {
    //         throw new RuntimeErrorException(null, "Algorithm not found");
    //     }
        
    // 

    public String generateToken(User currentUser)
    {

        System.out.println(currentUser.getTaskList());
        Map<String ,Object> claimMap = new HashMap<>();
        claimMap.put("currentUser",currentUser);
        
        
        Date today = new Date();
        System.out.println(today);
        Long time_in_ms = System.currentTimeMillis();
        Long expiration_buffer_in_ms =  300000000000L;
         Long expired_time_in_ms = time_in_ms+expiration_buffer_in_ms;
         Date expirationDate = new Date(expired_time_in_ms);



        SecretKey secretKey = Jwts.SIG.HS256.key().build();
        String token = Jwts.builder()
                
        .subject(currentUser.getEmail())
              .issuedAt(today)
              .expiration(expirationDate)
              
            //    .claims(claimMap)
               .signWith(getKey())
               .compact();
        return(token);
// 
    }
    public  SecretKey getKey()
    {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return(Keys.hmacShaKeyFor(keyBytes));
    }
    // public String  extractUsername(String jwtToken)
    // {
    //     String[] chunks = jwtToken.split("\\.");// dot is a special character in regex which would match any character. to treat it as a normal dot, we escape it
    //     System.out.println(chunks);
    //     Base64.Decoder decoder = Base64.getUrlDecoder();
    //     String header = new String(decoder.decode(chunks[0]));// decoder.decode returns a byte array;

    //     String payload = new String (decoder.decode(chunks[1]));
    //     System.out.println("Header"+header);
    //     System.out.println("Payload"+payload);
    //     String username = payload.substring(8);
    //     return(username);



    // }

    public String extractUsername(String jwttoken) throws Exception
    {
        Claims receivedClaims = ValidateTokenExtractClaims(jwttoken);
        String username = (String)receivedClaims.getSubject();
        //System.out.println("Username extracted " +username);
        return(username);
    }
    public boolean isTokenExpired(String token) throws Exception
    {
        Date expiration = ValidateTokenExtractClaims(token).getExpiration();
        Date now = new Date();
        
        if(expiration.before(now) == true)
        {
            return true;
        }
        
        else
        {  
            return false;

        }
    }


    public Claims ValidateTokenExtractClaims(String token) throws Exception
    {
        
        JwtParser jwtParser = Jwts.parser()
        .verifyWith(getKey())
        .build();
        Claims receivedClaims = null;
        try{
        receivedClaims =  (Claims) jwtParser.parse(token).getPayload();
        //System.out.println("Received claims "+receivedClaims);
        
        }
        catch(Exception e)
        {
            System.out.println("Token manipulated" +e.getMessage());
        }
        return(receivedClaims);
    }
       
    public boolean validateToken(String token , UserDetails userDetails) throws Exception 
    {
       
        if(!isTokenExpired(token) && (userDetails.getUsername().equals(extractUsername(token))))
        {
            System.out.println("Token verified");
            return(true);
        }
        else
        {
            return(false);
        }
    }

    
}

