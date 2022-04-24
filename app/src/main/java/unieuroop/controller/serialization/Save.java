package unieuroop.controller.serialization;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;


public final class Save {
    private static final String HOME_DIRECTORY = System.getProperty("user.home");
    private static final String DATA_DIRECTORY = "/.unieurOOP";
    private static final int BUFFER_SIZE = 1024;

    private Save() {
    }
    /**
     * It creates the directory and copy the json files inside it from the resources of the program if this folder doesn't exists already.
     * @throws IOException
     */
    public static void createDirectory() throws IOException {
        final File directory = new File(HOME_DIRECTORY + DATA_DIRECTORY);
        if (!Save.checkExistingDirectory(directory)) {
            directory.mkdirs();
        }
        if (!Save.checkDirectoryContainsFiles(directory)) {
            for (final var resourceFile : Files.values()) {
                final File newFile = new File(resourceFile.getPath());
                final var file = Save.class.getResourceAsStream("/files/" + resourceFile.getName());
                try (InputStream is = file;
                        OutputStream os = new FileOutputStream(newFile)) {
                    final byte[] buffer = new byte[BUFFER_SIZE];
                    int length = is.read(buffer);
                    while (length > 0) {
                        os.write(buffer, 0, length);
                        length = is.read(buffer);
                    }
                } 
            }
        }
    }
    /**
     * It says if the passed directory contains all the files that we need.
     * @param directory
     * @return
     */
    private static boolean checkDirectoryContainsFiles(final File directory) {
        final List<String> files = List.of(directory.listFiles()).stream()
                .map(f -> f.getName()).collect(Collectors.toList());
        return List.of(Files.values()).stream().allMatch(f -> files.contains(f.getName()));
    }
    /**
     * It says if the passed directory exist and it's a directory.
     * @param directory
     * @return
     */
    private static boolean checkExistingDirectory(final File directory) {
        return directory.exists() && directory.isDirectory();
    }
}
