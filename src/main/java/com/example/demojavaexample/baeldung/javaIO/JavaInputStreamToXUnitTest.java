package com.example.demojavaexample.baeldung.javaIO;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.CharSequenceReader;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardCopyOption;

import static java.nio.channels.Channels.newChannel;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JavaInputStreamToXUnitTest {
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

    // tests - InputStream to byte[]

    @Test
    final void givenUsingPlainJavaOnUnknownSizeStream_whenConvertingAnInputStreamToAByteArray_thenCorrect() throws IOException {
        final InputStream is = new ByteArrayInputStream(new byte[] { 0, 1, 2 });

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int nRead;
        final byte[] data = new byte[1024];
        while ((nRead = is.read(data, 0, data.length)) != -1) {
            baos.write(data, 0, nRead);
        }

        baos.flush();
        final byte[] byteArray = baos.toByteArray();
    }

    @Test
    final void givenUsingCommonsIO_whenConvertingAnInputStreamToAByteArray_thenCorrect() throws IOException {
        final InputStream initialStream = new ByteArrayInputStream(new byte[] { 0, 1, 2 });
        final byte[] targetArray = IOUtils.toByteArray(initialStream);
    }

    @Test
    void givenUsingCoreClasses_whenByteArrayInputStreamToAByteBuffer_thenLengthMustMatch()
            throws IOException {
        byte[] input = new byte[] { 0, 1, 2 };
        InputStream initialStream = new ByteArrayInputStream(input);
        ByteBuffer byteBuffer = ByteBuffer.allocate(3);
        while (initialStream.available() > 0) {
            byteBuffer.put((byte) initialStream.read());
        }

        assertEquals(byteBuffer.position(), input.length);
    }

    @Test
    void givenUsingCommonsIo_whenByteArrayInputStreamToAByteBuffer_thenLengthMustMatch()
            throws IOException {
        byte[] input = new byte[] { 0, 1, 2 };
        InputStream initialStream = new ByteArrayInputStream(input);
        ByteBuffer byteBuffer = ByteBuffer.allocate(3);
        ReadableByteChannel channel = newChannel(initialStream);
        IOUtils.readFully(channel, byteBuffer);

        assertEquals(byteBuffer.position(), input.length);
    }

    // tests - InputStream to File

    @Test
    final void whenConvertingToFile_thenCorrect() throws IOException {
        final InputStream initialStream = new FileInputStream(new File("src/test/resources/sample.txt"));
        final byte[] buffer = new byte[initialStream.available()];
        initialStream.read(buffer);

        final File targetFile = new File("src/test/resources/targetFile.tmp");
        final OutputStream outStream = new FileOutputStream(targetFile);
        outStream.write(buffer);

        IOUtils.closeQuietly(initialStream);
        IOUtils.closeQuietly(outStream);
    }

    @Test
    final void whenConvertingInProgressToFile_thenCorrect() throws IOException {
        final InputStream initialStream = new FileInputStream(new File("src/test/resources/sample.txt"));
        final File targetFile = new File("src/test/resources/targetFile.tmp");
        final OutputStream outStream = new FileOutputStream(targetFile);

        final byte[] buffer = new byte[8 * 1024];
        int bytesRead;
        while ((bytesRead = initialStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        IOUtils.closeQuietly(initialStream);
        IOUtils.closeQuietly(outStream);
    }

    @Test
    final void whenConvertingAnInProgressInputStreamToFile_thenCorrect2() throws IOException {
        final InputStream initialStream = new FileInputStream(new File("src/test/resources/sample.txt"));
        final File targetFile = new File("src/test/resources/targetFile.tmp");

        java.nio.file.Files.copy(initialStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        IOUtils.closeQuietly(initialStream);
    }

    @Test
    final void whenConvertingInputStreamToFile_thenCorrect4() throws IOException {
        final InputStream initialStream = FileUtils.openInputStream(new File("src/test/resources/sample.txt"));

        final File targetFile = new File("src/test/resources/targetFile.tmp");

        FileUtils.copyInputStreamToFile(initialStream, targetFile);
    }

    // tests - InputStream to Reader

    @Test
    public void givenUsingPlainJava_whenConvertingInputStreamIntoReader_thenCorrect()
            throws IOException {
        InputStream initialStream = new ByteArrayInputStream("With Java".getBytes());
//        Reader reader = new StringReader("With Java");

        Reader targetReader = new InputStreamReader(initialStream);

        targetReader.close();
    }

    @Test
    public void givenUsingCommonsIO_whenConvertingInputStreamIntoReader_thenCorrect()
            throws IOException {
        InputStream initialStream = IOUtils.toInputStream("With Commons IO");

        byte[] buffer = IOUtils.toByteArray(initialStream);
        Reader targetReader = new CharSequenceReader(new String(buffer));
//        Reader targetReader = new CharSequenceReader(new String("With Commons IO".getBytes()));
//        Reader targetReader = new CharSequenceReader("With Commons IO");

        targetReader.close();
    }
}
