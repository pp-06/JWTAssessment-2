package com.MongoDBAssessment.MongoDBAssessment.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class TokenService {
    // Secret key for signing and verifying tokens
    public static final String tokenSecret = "dvlkbalbalsaklsvnaawnco";
    //Creating a Token
    public String createToken(ObjectId userId){
        try{
            // Algorithm used for token creation
            Algorithm algo = Algorithm.HMAC256(tokenSecret);
            // Creating a token with user-specific claims
            String token = JWT.
                    create().
                    withClaim("userId", userId.toString()).
                    withClaim("createdAt", new Date()).
                    sign(algo);
            return token;
        } catch (UnsupportedEncodingException | JWTCreationException e){
            e.printStackTrace();
        }
        return null;
    }
    // Extracting user ID from the token
    public String getUserIdToken(String token){
        try {
            // Algorithm used for token verification
            Algorithm algo = Algorithm.HMAC256(tokenSecret);
            // Verifying the token and extracting claims
            JWTVerifier jwtVerifier = JWT.require(algo).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            // Extracting the user ID claim from the decoded token
            return decodedJWT.getClaim("userId").asString();
        }catch (UnsupportedEncodingException|JWTCreationException e){
            e.printStackTrace();
        }
        return null;
    }

    // Checking if a token is valid
    public boolean isTokenValid(String token){
        // Extracting user ID from the token
        String userId = this.getUserIdToken(token);
        // Checking if the user ID is present (token is valid)
        return userId != null;
    }

}
