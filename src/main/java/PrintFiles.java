import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.stream.Stream;

public class PrintFiles {
    public static void main(String[] args) throws IOException {
        DecimalFormat df2 = new DecimalFormat(".##");
        Path path = Paths.get(".", "");
        try (Stream<Path> stream = Files.walk(path)) {
            stream.filter(path2 -> path2.toFile().isFile()).forEach(path2 -> {
                double fileSize = 0.0;
                String fileSizeNotation = "NA";
                double bytes = path2.toFile().length();
                if (bytes > 1024.00) {
                    double kilobytes = (bytes / 1024);
                    if (kilobytes > 1024.00) {
                        double megabytes = (kilobytes / 1024);
                        if (megabytes > 1024.00) {
                            double gigabytes = (megabytes / 1024);
                            if (gigabytes > 1024.00) {
                                double terabytes = (gigabytes / 1024);
                                if (terabytes > 1024.00) {
                                    double petabytes = (terabytes / 1024);
                                    if (petabytes > 1024.00) {
                                        double exabytes = (petabytes / 1024);
                                        if (exabytes > 1024.00) {
                                            double zettabytes = (exabytes / 1024);
                                            if (zettabytes > 1024.00) {
                                                double yottabytes = (zettabytes / 1024);
                                                if (yottabytes > 1024.00) {
                                                    fileSize = yottabytes;
                                                    fileSizeNotation = "MASSIVE";
                                                } else {
                                                    fileSize = yottabytes;
                                                    fileSizeNotation = "YB";
                                                }
                                            } else {
                                                fileSize = zettabytes;
                                                fileSizeNotation = "ZB";
                                            }
                                        } else {
                                            fileSize = exabytes;
                                            fileSizeNotation = "EB";
                                        }
                                    } else {
                                        fileSize = petabytes;
                                        fileSizeNotation = "PB";
                                    }
                                } else {
                                    fileSize = terabytes;
                                    fileSizeNotation = "TB";
                                }
                            } else {
                                fileSize = gigabytes;
                                fileSizeNotation = "GB";
                            }
                        } else {
                            fileSize = megabytes;
                            fileSizeNotation = "MB";
                        }
                    } else {
                        fileSize = kilobytes;
                        fileSizeNotation = "KB";
                    }
                } else {
                    fileSize = bytes;
                    fileSizeNotation = "bytes";
                }
                String fileType = "UNKNOWN";

                try {
                    fileType = Files.probeContentType(path2);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (fileType == null) {
                    fileType = "UNKNOWN";
                }
                String fileExtension = path2.getFileName().toString().substring(path2.getFileName().toString().lastIndexOf(".") + 1);
                System.out.println("FileName  is <" + path2.getFileName() + ">   Size is : <" + df2.format(fileSize) + " " + fileSizeNotation + ">  <" + fileType + "> <" + fileExtension + ">");
            });
        }
    }
}

