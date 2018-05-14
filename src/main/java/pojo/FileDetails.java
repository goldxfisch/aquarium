package pojo;

public class FileDetails {
    public FileDetails(String fileName, String fileSize, String fileMimeType, String fileExtension, String directoryName) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileMimeType = fileMimeType;
        this.fileExtension = fileExtension;
        this.DirectoryName = directoryName;
    }

    public String getFileName() {

        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileMimeType() {
        return fileMimeType;
    }

    public void setFileMimeType(String fileMimeType) {
        this.fileMimeType = fileMimeType;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    String fileName;
    String fileSize;
    String fileMimeType;
    String fileExtension;
    String DirectoryName ;

    public String getDirectoryName() {
        return DirectoryName;
    }

    public void setDirectoryName(String directoryName) {
        DirectoryName = directoryName;
    }

    @Override
    public String toString() {
        return "FileDetails{" +
                "fileName='" + fileName + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", fileMimeType='" + fileMimeType + '\'' +
                ", fileExtension='" + fileExtension + '\'' +
                ", DirectoryName='" + DirectoryName + '\'' +
                '}';
    }
}
