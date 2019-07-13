package mnist;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.spi.FileSystemProvider;
import java.util.*;

class MnistLoader {

    static List<MnistDigit> loadData(String imagesFile, String labelsFile)
            throws IOException {

        byte[] imagesByte = IOUtils.toByteArray(
                Objects.requireNonNull(
                        MnistLoader.class.getClassLoader().getResourceAsStream(imagesFile)));
        byte[] labelsByte = IOUtils.toByteArray(
                Objects.requireNonNull(
                        MnistLoader.class.getClassLoader().getResourceAsStream(labelsFile)));

        List<MnistDigit> digits = new ArrayList<>();

        int readHeadData = 16;
        int readHeadLabel = 8;
        while(readHeadData < imagesByte.length) {
            int[][] data = new int[28][28];
            for(int i = 0; i<28; i++) {
                for(int k = 0; k<28; k++) {
                    data[i][k] = toUnsignedByte(imagesByte[readHeadData++]);
                }
            }
            int label = toUnsignedByte(labelsByte[readHeadLabel++]);

            digits.add(new MnistDigit(label, data));
        }

        return digits;
    }

    private static int toUnsignedByte(byte b) {
        return b & 0xFF;
    }
}
