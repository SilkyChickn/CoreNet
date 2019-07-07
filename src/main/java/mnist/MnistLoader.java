package mnist;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class MnistLoader {

    static List<MnistDigit> loadData(String imagesFile, String labelsFile)
            throws IOException, URISyntaxException {
        Path imagesPath = Paths.get(
                Objects.requireNonNull(MnistLoader.class.getClassLoader().getResource(imagesFile)).toURI());
        Path labelsPath = Paths.get(
                Objects.requireNonNull(MnistLoader.class.getClassLoader().getResource(labelsFile)).toURI());

        byte[] imagesByte = Files.readAllBytes(imagesPath);
        byte[] labelsByte = Files.readAllBytes(labelsPath);

        List<MnistDigit> digits = new ArrayList<>();

        int readHeadData = 16;
        int readHeadLabel = 8;
        while(readHeadData < imagesByte.length)
        {
            int[][] data = new int[28][28];
            for(int i = 0; i<28; i++)
            {
                for(int k = 0; k<28; k++)
                {
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
