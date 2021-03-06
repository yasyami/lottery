package com.meyasy.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Map;
import java.util.UUID;

public class JwtTokenUtils {

    public static void main(String[] args) {
        System.out.println(DateTime.now().plusDays(1));
    }

    private static Key generatorKey(){
        SignatureAlgorithm ssa = SignatureAlgorithm.ES256;
        byte[] bytes = DatatypeConverter.parseBase64Binary("f3973b64918e4324ad85acea1b6cbec5");
        Key key =new SecretKeySpec(bytes,ssa.getJcaName());
        return key;
    }

    public static String generatorToken(Map<String,Object> payLoad){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return Jwts.builder().setPayload(objectMapper.writeValueAsString(payLoad))
                    .signWith(SignatureAlgorithm.ES256,generatorKey()).compact();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Claims phaseToken(String token){
        Jws<Claims> claimsJwt=Jwts.parser().setSigningKey(generatorKey()).parseClaimsJws(token);

        return claimsJwt.getBody();
    }

}
