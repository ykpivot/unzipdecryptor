

# unzipdecryptor
A sample Java app that unzip and decrypt an input file using streaming

#### Please note this is just a sample app that does not have any TESTS!!!!!!

# How to use

You first need to create a test file.

1. Create a file with some contents in it
2. Encrypt the file

## Performance Test Result


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
