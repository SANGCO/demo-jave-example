package com.example.demojavaexample.baeldung.javaIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class JavaXToInputStreamUnitTest {

    // tests - String to InputStream

    @Test
    public void givenUsingPlainJava_whenConvertingStringToInputStream_thenCorrect() throws IOException {
        String initialString = "text";
        InputStream targetStream = new ByteArrayInputStream(initialString.getBytes());
    }

    @Test
    public void givenUsingCommonsIO_whenConvertingStringToInputStream_thenCorrect() throws IOException {
        String initialString = "text";
        InputStream targetStream = IOUtils.toInputStream(initialString, Charset.defaultCharset());
    }

    // tests - Java Byte Array to InputStream

    @Test
    public void givenUsingPlainJava_whenConvertingByteArrayToInputStream_thenCorrect()
            throws IOException {
        byte[] initialArray = {0, 1, 2};
        InputStream targetStream = new ByteArrayInputStream(initialArray);
    }

    // tests - Convert File to InputStream

    @Test
    public void givenUsingPlainJava_whenConvertingFileToInputStream_thenCorrect() throws IOException {
        File initialFile = new File("src/main/resources/sample.txt");
        InputStream targetStream = new FileInputStream(initialFile);
    }

    @Test
    public final void givenUsingPlainJava_whenConvertingFileToDataInputStream_thenCorrect() throws IOException {
        final File initialFile = new File("src/test/resources/sample.txt");
        final InputStream targetStream = new DataInputStream(new FileInputStream(initialFile));
    }

    @Test
    public final void givenUsingPlainJava_whenConvertingFileToSequenceInputStream_thenCorrect() throws IOException {
        final File initialFile = new File("src/test/resources/sample.txt");
        final File anotherFile = new File("src/test/resources/anothersample.txt");
        final InputStream targetStream = new FileInputStream(initialFile);
        final InputStream anotherTargetStream = new FileInputStream(anotherFile);
        InputStream sequenceTargetStream = new SequenceInputStream(targetStream, anotherTargetStream);
        System.out.println("sequenceTargetStream : " + IOUtils.toString(sequenceTargetStream, StandardCharsets.UTF_8.name()));
    }

    @Test
    public void givenUsingCommonsIO_whenConvertingFileToInputStream_thenCorrect() throws IOException {
        File initialFile = new File("src/main/resources/sample.txt");
        InputStream targetStream = FileUtils.openInputStream(initialFile);
    }

    // tests - Reader to InputStream

    @Test
    public void givenUsingPlainJava_whenConvertingReaderIntoInputStream_thenCorrect() throws IOException {
        Reader initialReader = new StringReader("With Java");
        char[] charBuffer = new char[8 * 1024];
        StringBuilder builder = new StringBuilder();
        int numCharsRead;
        while ((numCharsRead = initialReader.read(charBuffer, 0, charBuffer.length)) != -1) {
            builder.append(charBuffer, 0, numCharsRead);
        }

        InputStream targetStream = new ByteArrayInputStream(builder.toString().getBytes(StandardCharsets.UTF_8));
        initialReader.close();
        targetStream.close();
    }

    @Test
    public void givenUsingCommonsIO_whenConvertingReaderIntoInputStream() throws IOException {
        Reader initialReader = new StringReader("With Commons IO");
        InputStream targetStream = IOUtils.toInputStream(IOUtils.toString(initialReader), StandardCharsets.UTF_8.name());
        initialReader.close();
        targetStream.close();
    }
}
