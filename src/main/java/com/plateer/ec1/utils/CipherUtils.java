package com.plateer.ec1.utils;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class CipherUtils {
    public static String encrypt(String text) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");

            md.update(text.getBytes());
            byte[] digest = md.digest();
            log.info("digest: {}", digest);

            String hexEncrypt = DatatypeConverter.printHexBinary(digest);
            return hexEncrypt.toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            log.info("Encrypt Error: {}", e);

            return "";
        }
    }
}
