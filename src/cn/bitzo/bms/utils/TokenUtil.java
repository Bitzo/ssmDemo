package cn.bitzo.bms.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class TokenUtil {
    private static TokenUtil ourInstance = new TokenUtil();

    public static TokenUtil getInstance() {
        return ourInstance;
    }

    private TokenUtil() {
    }

    private static final String author = "Bitzo";
    private static final String secret = "MySecret944bitzo";

    public String getToken(String str){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer(author)
                    .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 30 * 1000))
                    .withClaim("username", str)
                    .sign(algorithm);
            return token;
        } catch (UnsupportedEncodingException exception){
            //UTF-8 encoding not supported
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return null;
    }

    public String verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(author)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            Claim decode = jwt.getClaim("username");
//            System.out.println("==>:" + decode.asString());
            return decode.asString();
        } catch (UnsupportedEncodingException exception){
            //UTF-8 encoding not supported
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
        }
        return null;
    }
}
