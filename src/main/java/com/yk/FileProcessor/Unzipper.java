package com.yk.FileProcessor;

import org.springframework.stereotype.Component;

import java.io.*;
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
            BufferedOutputStream bufout = new BufferedOutputStream(fout);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = zis.read(buffer)) != -1) {
                bufout.write(buffer, 0, read);
            }

            zis.closeEntry();
            bufout.close();
            fout.close();
        }
        zis.close();
    }
}
