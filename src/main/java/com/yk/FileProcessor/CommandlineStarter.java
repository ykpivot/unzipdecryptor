package com.yk.FileProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandlineStarter implements CommandLineRunner {

    @Autowired
    Unzipper unzipper;

    @Override
    public void run(String...args) throws Exception {
        String inputFile = "/Users/ykim/workspace/sample-files/test-10gb.zip";
        String outputFile = "/Users/ykim/workspace/sample-files/test-10gb-out.txt";

        System.out.println("Starting unzipping process");

        unzipper.unzip(inputFile, outputFile);

        System.out.println("Finished unzipping process");
    }
}
