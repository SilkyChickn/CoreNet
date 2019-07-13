package mnist;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.spi.FileSystemProvider;
import java.util.*;

class MnistLoader {

    static List<MnistDigit> loadData(String imagesFile, String labelsFile)
            throws IOException, URISyntaxException {

        //Get uris
        URI imagesUri = Objects.requireNonNull(MnistLoader.class.getClassLoader().getResource(imagesFile)).toURI();
        URI labelsUri = Objects.requireNonNull(MnistLoader.class.getClassLoader().getResource(labelsFile)).toURI();

        //Create filesystem
        if("jar".equals(imagesUri.getScheme())){
            for (FileSystemProvider provider: FileSystemProvider.installedProviders()) {
                if (provider.getScheme().equalsIgnoreCase("jar")) {
                    try {
                        provider.getFileSystem(imagesUri);
                    } catch (FileSystemNotFoundException e) {
                        // in this case we need to initialize it first:
                        provider.newFileSystem(imagesUri, Collections.emptyMap());
                    }
                }
            }
        }

        //Get paths from file systems
        Path imagesPath = Paths.get(imagesUri);
        Path labelsPath = Paths.get(labelsUri);

        byte[] imagesByte = Files.readAllBytes(imagesPath);
        byte[] labelsByte = Files.readAllBytes(labelsPath);

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
