package utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DirCommand {
    final static Logger logger = LoggerFactory.getLogger(DirCommand.class);

    public static void main (String...cmdArgs) throws IOException {
        //   String folderName = "C:\\Users\\goldfish\\IdeaProjects\\showcase\\src\\main\\resources\\";
        String folderName = "C:\\jdk1.8.0_151\\";
        List<String> csvLineList = new ArrayList<>();
        File currentDirectory = new File(folderName);
        File[] files = currentDirectory.listFiles();
        MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();

        for (File file : files) {

            if (file.isFile()) {
                ///  System.out.print("File");
                /// System.out.print("\t");
                DecimalFormat RoundVal = new DecimalFormat("0.00");
                double KB = (double) (file.length()) / 1024;
                double MB = (double) (KB / 1024);
                double GB = (double) (MB / 1024);
                double TB = (double) (GB / 1024);
                double fileSize ;
                String fileSizeInd;

                if (KB <= 1) {
                    ////   System.out.print("\t" + (file.length() + "Bytes"));
                    fileSizeInd = file.length() + "Bytes";
                } else {
                    if (KB >= 1024) {
                        if (MB >= 1024) {
                            if (GB >= 1024) {
                                ///   System.out.print("\t" + RoundVal.format(TB) + "TB");
                                fileSizeInd = RoundVal.format(TB) + "TB";
                            } else {
                                ///   System.out.print("\t" + RoundVal.format(GB) + "GB");
                                fileSizeInd = RoundVal.format(GB) + "GB";
                            }
                        } else {
                            ///      System.out.print("\t" + RoundVal.format(MB) + "MB");
                            fileSizeInd = RoundVal.format(MB) + "MB";
                        }
                    } else {
                        fileSizeInd = RoundVal.format(KB) + "KB";
                        ///  System.out.print("\t" + RoundVal.format(KB) + "KB");
                    }
                }

                ////   String mimeType = mimeTypesMap.getContentType(file);
                /// System.out.print("\t" +Files.probeContentType(file.toPath()));
                /// System.out.println("\t\t\t" +"" +  + "     ");
                String fileMimeType =  "TO_BE_IDENTIFIED";
                fileMimeType =  Files.probeContentType(file.toPath());


                String fileExtension = file.getName().substring(file.getName().lastIndexOf(".")+1);
                logger.info ( "INFO2       <{}>    <{}>     <{}> <{}>",file.getName(), fileExtension,fileSizeInd , fileMimeType );
            }
            if (file.isDirectory()) {
                /*
                    System.out.print("Directory");
                    System.out.print("\t");
                    System.out.println("\t\t\t" +"" + file.getName() + "     ");
                */
            }




        }
    }
}
