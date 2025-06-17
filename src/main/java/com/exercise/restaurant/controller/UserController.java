package com.exercise.restaurant.controller;

import com.exercise.restaurant.service.JwtService;
import com.exercise.restaurant.vo.SignIn;
import com.exercise.restaurant.vo.UserVo;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Path("user")
public class UserController {

    JwtService jwtService;

    public UserController() {
        this.jwtService = new JwtService();
    }

    @POST
    @Path("signup")
    public void signup(UserVo userVo) {
        //persist User to JPA

    }

    @POST
    @Path("/signin")
    public String signIn(@RequestBody SignIn signIn) {
        //validate password , assuming the stored password in DB is using MD5

        String psswordStrinmd5 = getMd5(signIn.getPassword());

        //compare the MD5 string between SignIn password and the one stored in DB

        //if the password is correct, then return the JWT token

        return jwtService.generateToken(signIn.getUserName());
    }

    public static String getMd5(String input) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }  // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}
