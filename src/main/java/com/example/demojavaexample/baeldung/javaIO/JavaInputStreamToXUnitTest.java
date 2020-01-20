package com.example.demojavaexample.baeldung.javaIO;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

import org.apache.commons.io.IOUtils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class JavaInputStreamToXUnitTest {
    private static final int DEFAULT_SIZE = 1500000;

    // tests - InputStream to String

    @Test
    final void givenUsingJava5_whenConvertingAnInputStreamToAString_thenCorrect() throws IOException {
        final String originalString = randomAlphabetic(DEFAULT_SIZE);
        final InputStream inputStream = new ByteArrayInputStream(originalString.getBytes());
        final StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        assertEquals(textBuilder.toString(), originalString);
    }

    @Test
    final void givenUsingCommonsIo_whenConvertingAnInputStreamToAString_thenCorrect() throws IOException {
        final String originalString = randomAlphabetic(DEFAULT_SIZE);
        final InputStream inputStream = new ByteArrayInputStream(originalString.getBytes());

        // When
        final String text = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
        assertThat(text, equalTo(originalString));
    }

//    // tests - InputStream to byte[]
//
//    @Test
//    public final void givenUsingPlainJavaOnFixedSizeStream_whenConvertingAnInputStreamToAByteArray_thenCorrect() throws IOException {
//        final InputStream initialStream = new ByteArrayInputStream(new byte[] { 0, 1, 2 });
//        final byte[] targetArray = new byte[initialStream.available()];
//        initialStream.read(targetArray);
//    }
//
//    @Test
//    public final void givenUsingPlainJavaOnUnknownSizeStream_whenConvertingAnInputStreamToAByteArray_thenCorrect() throws IOException {
//        final InputStream is = new ByteArrayInputStream(new byte[] { 0, 1, 2 });
//
//        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//        int nRead;
//        final byte[] data = new byte[1024];
//        while ((nRead = is.read(data, 0, data.length)) != -1) {
//            buffer.write(data, 0, nRead);
//        }
//
//        buffer.flush();
//        final byte[] byteArray = buffer.toByteArray();
//    }
//
//    @Test
//    public final void givenUsingGuava_whenConvertingAnInputStreamToAByteArray_thenCorrect() throws IOException {
//        final InputStream initialStream = ByteSource.wrap(new byte[] { 0, 1, 2 }).openStream();
//        final byte[] targetArray = ByteStreams.toByteArray(initialStream);
//    }
//
//    @Test
//    public final void givenUsingCommonsIO_whenConvertingAnInputStreamToAByteArray_thenCorrect() throws IOException {
//        final InputStream initialStream = new ByteArrayInputStream(new byte[] { 0, 1, 2 });
//        final byte[] targetArray = IOUtils.toByteArray(initialStream);
//    }
//
//    // tests - InputStream to File
//
//    @Test
//    public final void whenConvertingToFile_thenCorrect() throws IOException {
//        final InputStream initialStream = new FileInputStream(new File("src/test/resources/sample.txt"));
//        final byte[] buffer = new byte[initialStream.available()];
//        initialStream.read(buffer);
//
//        final File targetFile = new File("src/test/resources/targetFile.tmp");
//        final OutputStream outStream = new FileOutputStream(targetFile);
//        outStream.write(buffer);
//
//        IOUtils.closeQuietly(initialStream);
//        IOUtils.closeQuietly(outStream);
//    }
//
//    @Test
//    public final void whenConvertingInProgressToFile_thenCorrect() throws IOException {
//        final InputStream initialStream = new FileInputStream(new File("src/test/resources/sample.txt"));
//        final File targetFile = new File("src/test/resources/targetFile.tmp");
//        final OutputStream outStream = new FileOutputStream(targetFile);
//
//        final byte[] buffer = new byte[8 * 1024];
//        int bytesRead;
//        while ((bytesRead = initialStream.read(buffer)) != -1) {
//            outStream.write(buffer, 0, bytesRead);
//        }
//
//        IOUtils.closeQuietly(initialStream);
//        IOUtils.closeQuietly(outStream);
//    }
//
//    @Test
//    public final void whenConvertingAnInProgressInputStreamToFile_thenCorrect2() throws IOException {
//        final InputStream initialStream = new FileInputStream(new File("src/test/resources/sample.txt"));
//        final File targetFile = new File("src/test/resources/targetFile.tmp");
//
//        java.nio.file.Files.copy(initialStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//
//        IOUtils.closeQuietly(initialStream);
//    }
//
//    @Test
//    public final void whenConvertingInputStreamToFile_thenCorrect3() throws IOException {
//        final InputStream initialStream = new FileInputStream(new File("src/test/resources/sample.txt"));
//        final byte[] buffer = new byte[initialStream.available()];
//        initialStream.read(buffer);
//
//        final File targetFile = new File("src/test/resources/targetFile.tmp");
//        Files.write(buffer, targetFile);
//
//        IOUtils.closeQuietly(initialStream);
//    }
//
//    @Test
//    public final void whenConvertingInputStreamToFile_thenCorrect4() throws IOException {
//        final InputStream initialStream = FileUtils.openInputStream(new File("src/test/resources/sample.txt"));
//
//        final File targetFile = new File("src/test/resources/targetFile.tmp");
//
//        FileUtils.copyInputStreamToFile(initialStream, targetFile);
//    }
//
//    @Test
//    public final void givenUsingPlainJava_whenConvertingAnInputStreamToString_thenCorrect() throws IOException {
//        String originalString = randomAlphabetic(8);
//        InputStream inputStream = new ByteArrayInputStream(originalString.getBytes());
//
//        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//        int nRead;
//        byte[] data = new byte[1024];
//        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
//            buffer.write(data, 0, nRead);
//        }
//
//        buffer.flush();
//        byte[] byteArray = buffer.toByteArray();
//
//        String text = new String(byteArray, StandardCharsets.UTF_8);
//        assertThat(text, equalTo(originalString));
//    }

}
