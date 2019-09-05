

# unzipdecryptor
A sample Java app that unzip and decrypt an input file using streaming.

#### Please note this is just a sample app that does not have any TESTS!!!!!!

# How to use

First, build the SpringBoot commandLine app.

```
$ ./gradlew clean assemble
```

The app takes 2-3 arguments depending on the mode. 

```
java -jar build/libs/FileProcessor-0.0.1-SNAPSHOT.jar mode inputFile [originalFileExtension]

```

Here are the available modes:

```
  e - encrypt the input file
  d - decrypt the input file
  u - unzip the input file
  du - decrypt the input file then unzip the result
  ud - unzip the input file then decrypt the result
```

You need to provide the `originalFileExtension` argument for any modes involved unzipping.


To create a test file that is decrypted and zipped, you can use the following sample commands. 
Basically, you can use the app to encrypt a file.
```
$ echo "My test file" > test.txt
$ java -jar build/libs/FileProcessor-0.0.1-SNAPSHOT.jar e test.txt
$ zip test.zip test.txt
```

You can now unzip then decrypt your file using the following command.

```
$ java -jar build/libs/FileProcessor-0.0.1-SNAPSHOT.jar ud test.zip txt
```

## Performance Test Result

The MacOS's `time` utility is used to capture performance data.

When unzipping using the app:

```
$ /usr/bin/time -l java -jar build/libs/FileProcessor-0.0.1-SNAPSHOT.jar

       74.16 real        39.43 user        16.31 sys
 193961984  maximum resident set size
```

When decrypting using the app:

```
$ /usr/bin/time -l java -jar build/libs/FileProcessor-0.0.1-SNAPSHOT.jar

      276.25 real        75.18 user       118.17 sys
 277356544  maximum resident set size
```

When doing both - unzipping and then decrypting - using the app:

```
/usr/bin/time -l java -jar build/libs/FileProcessor-0.0.1-SNAPSHOT.jar ud /Users/ykim/workspace/sample-files/test/original-10gb-encrypted.zip txt

      790.69 real       188.58 user       264.03 sys
 261152768  maximum resident set size
```

For reference, when using the MacOS native `unzip` command:

```
$ /usr/bin/time -l unzip test-10gb.zip

       52.73 real        31.35 user         6.31 sys

   2011136  maximum resident set size
```
