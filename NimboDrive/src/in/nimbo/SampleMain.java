package in.nimbo;

import in.nimbo.file.BinaryFile;
import in.nimbo.file.NimboFile;

import java.util.Comparator;
import java.util.List;

public class SampleMain {
    public static void main(String[] args) {
        UserStorageRepository storageRepository = new UserStorageRepository();
        FileScanner scanner = new FScanner();
        FileRepository fileRepository = new FileRepository(storageRepository, scanner);
        storageRepository.increaseStorageOfUser("ali", 5000);
        storageRepository.increaseStorageOfUser("gholi", 50000);
        NimboFile file = new BinaryFile("test-file.exe", "/user/ali/", 2500, "ali");
        NimboFile gholiFile = new BinaryFile("tdwae.exe", "/user/gholi/", 3123, "gholi");
        NimboFile gholiFile2 = new BinaryFile("tdwdwaae.exe", "/user/gholi/", 1000, "gholi");
        fileRepository.addFile(file);
        fileRepository.addFile(gholiFile);
        fileRepository.addFile(gholiFile2);
        NimboFile[] sorted = fileRepository.sort(Comparator.comparingInt(NimboFile::getSize));
        List<NimboFile> fileList = fileRepository.searchByName("t-f");
        fileRepository.scan();
        System.out.println(fileList);
    }
}

// Sample stupid implementation of File Scanner
class FScanner implements FileScanner {
    @Override
    public void scanFile(NimboFile file) throws MalformedFileException {
    }
}
