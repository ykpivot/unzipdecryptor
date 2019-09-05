package com.yk.FileProcessor;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Component
public class CommandlineStarter implements CommandLineRunner {

    @Autowired
    Unzipper unzipper;

    @Autowired
    EncryptManager encryptManager;

    @Autowired
    DecryptManager decryptManager;

    @Override
    public void run(String... args) throws Exception {
        String option = args[0];
        String inputFile = args[1];

        String originalFileExtention = args[2];

        switch (option) {
            case "e":
                encrypt(inputFile);
                break;
            case "d":
                decrypt(inputFile);
                break;
            case "u":
                unzip(inputFile, originalFileExtention);
                break;
            case "du":
                unzip(decrypt(inputFile), originalFileExtention);
                break;
            case "ud":
                decrypt(unzip(inputFile, originalFileExtention));
                break;
            default:
                break;
        }
    }

    private String encrypt(String inputFile) throws IOException, GeneralSecurityException {
        String filePath = FilenameUtils.getFullPath(inputFile) + FilenameUtils.getBaseName(inputFile);
        String fileExtension = FilenameUtils.getExtension(inputFile);
        String outputFile = filePath + "-encrypted." + fileExtension;

        System.out.println("Starting encryption process: " + inputFile);
        encryptManager.encrypt(inputFile, outputFile);
        System.out.println("Finished encryption process: " + outputFile);

        return outputFile;
    }

    private String decrypt(String inputFile) throws IOException, GeneralSecurityException {
        String filePath = FilenameUtils.getFullPath(inputFile) + FilenameUtils.getBaseName(inputFile);
        String fileExtension = FilenameUtils.getExtension(inputFile);
        String outputFile = filePath + "-decrypted." + fileExtension;

        System.out.println("Starting decryption process: " + inputFile);
        decryptManager.decrypt(inputFile, outputFile);
        System.out.println("Finished decryption process: " + outputFile);

        return outputFile;
    }

    private String unzip(String inputFile, String originalFileExtention) throws IOException {
        String filePath = FilenameUtils.getFullPath(inputFile) + FilenameUtils.getBaseName(inputFile);
        String outputFile = filePath + "-unzipped." + originalFileExtention;

        System.out.println("Starting unzipping process: " + inputFile);
        unzipper.unzip(inputFile, outputFile);
        System.out.println("Finished unzipping process: " + outputFile);

        return outputFile;
    }
}
