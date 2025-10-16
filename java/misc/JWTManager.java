/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package misc;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 *
 * @author cfqr4byfeo8uoj4b
 */
public class JWTManager {
    public int verifyToken(String r_token){
        DecodedJWT d = JWT.decode(r_token);
        int id = d.getClaim("id").asInt();
        
        return id;
    }
}
