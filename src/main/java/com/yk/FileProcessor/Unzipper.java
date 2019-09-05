package com.yk.FileProcessor;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Component
public class Unzipper {
    public void unzip(String inputFile, String outputFile) throws IOException {
        FileInputStream fis = new FileInputStream(inputFile);

        ZipInputStream zis = new ZipInputStream(fis);
        ZipEntry entry;

        while ((entry = zis.getNextEntry()) != null)
        {
            System.out.println("entry: " + entry.getName() + ", " + entry.getSize());

            FileOutputStream fout = new FileOutputStream(outputFile);

            while (zis.available() > 0) {
                fout.write(zis.read());
            }
            fout.close();
        }
    }
}
