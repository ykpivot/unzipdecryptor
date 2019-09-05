package com.yk.FileProcessor;

import org.encryptor4j.Encryptor;
import org.encryptor4j.factory.KeyFactory;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.*;
import java.security.GeneralSecurityException;

@Component
public class DecryptManager {

    public void decrypt(String inputFile, String outputFile) throws IOException, GeneralSecurityException {
        SecretKey secretKey = (SecretKey) KeyFactory.AES.keyFromPassword("abc".toCharArray());

        Encryptor encryptor = new Encryptor(secretKey, "AES/CTR/NoPadding", 16);

        InputStream is = null;
        OutputStream os = null;

        try {
            is = encryptor.wrapInputStream(new FileInputStream(inputFile));

            File outFile = new File(outputFile);
            outFile.createNewFile();

            os = new FileOutputStream(outFile, false);
            byte[] buffer = new byte[4096];
            int nRead;
            while((nRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, nRead);
            }
            os.flush();
        } finally {
            if(is != null) {
                is.close();
            }
            if(os != null) {
                os.close();
            }
        }
    }
}
