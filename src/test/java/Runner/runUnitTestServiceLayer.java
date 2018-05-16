package Runner;

import ServiceLayer.ScanDirectory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.FileDetails;

import java.io.IOException;
import java.util.List;

@RunWith(Parameterized.class)
public class runUnitTestServiceLayer {
    final static Logger logger = LoggerFactory.getLogger(runUnitTestServiceLayer.class);

    private List<FileDetails> ListOfFiles;

    @Parameterized.Parameters(name = "{index}: File - {0}")
    public static Iterable<? extends Object> data() throws IOException {
        List<FileDetails> listOfFiles =  new ScanDirectory().crawlDirectory();
        return listOfFiles;
        }

    @Test
    public void shouldValidatePresenceOfFolders() throws IOException {
        List<FileDetails> fileSampler = new ScanDirectory().crawlDirectory();
    }





}
