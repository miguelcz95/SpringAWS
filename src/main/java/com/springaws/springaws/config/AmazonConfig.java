package com.springaws.springaws.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.logging.Logger;

@Configuration
public class AmazonConfig {

    private static final String KEY = "aesEncryptionKey";
    private static final String INIT_VECTOR = "encryptionIntVec";

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Value("${aws.accesskey}")
    private String accesKey;

    @Value("${aws.secretkey}")
    private String secretKey;

    @Bean
    public AmazonS3 s3() {

        Optional<String> localAcccessKey = decrypt(accesKey);
        Optional<String> localSecretKey = decrypt(secretKey);

        String dAccessKey = "";
        String dSecretKey = "";

        if (localAcccessKey.isPresent()) {
            dAccessKey = localAcccessKey.get();
        }
        if (localSecretKey.isPresent()) {
            dSecretKey = localSecretKey.get();
        }

        AWSCredentials awsCredentials = new BasicAWSCredentials(
                dAccessKey,
                dSecretKey
        );

        logger.info("Conexion a AWS correcta");

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion("South America (São Paulo)")
                .enableUseArnRegion()
                .build();
    }

    public Optional<String> decrypt(String encrypted) {

        try {

            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return Optional.of(new String(original));

        } catch (Exception ex) {

            logger.info(ex.getMessage());

        }

        return Optional.empty();
    }

    //Metodo de Encripcion
    /*public Optional<String> encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Optional.ofNullable(Base64.encodeBase64String(encrypted));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }*/
}
