package io.doraemon.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

	public static String compressFile(String file) throws IOException {
		//String sourceFile = "test1.txt";
		String compressedFile = file + ".zip";
        FileOutputStream fos = new FileOutputStream(compressedFile);
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        File fileToZip = new File(file);
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zipOut.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }

        zipOut.close();
        fis.close();
        fos.close();
        
        return compressedFile;
	}
	
	public static String compressFiles(String ...files) throws IOException {
        Path path = Paths.get(files[0]);
        Path filePath = path.getFileName();
        String fileName = filePath.toString() + ".zip";
        String compressedFile = path.getParent().toAbsolutePath() + "/" + fileName;
        final FileOutputStream fos = new FileOutputStream(compressedFile);
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        for (String srcFile : files) {
            File fileToZip = new File(srcFile);
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        }

        zipOut.close();
        fos.close();
        
        return compressedFile;
	}
	
	private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
	    if (fileToZip.isHidden()) {
	        return;
	    }
	    if (fileToZip.isDirectory()) {
	        if (fileName.endsWith("/")) {
	            zipOut.putNextEntry(new ZipEntry(fileName));
	            zipOut.closeEntry();
	        } else {
	            zipOut.putNextEntry(new ZipEntry(fileName + "/"));
	            zipOut.closeEntry();
	        }
	        File[] children = fileToZip.listFiles();
	        for (File childFile : children) {
	            zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
	        }
	        return;
	    }
	    FileInputStream fis = new FileInputStream(fileToZip);
	    ZipEntry zipEntry = new ZipEntry(fileName);
	    zipOut.putNextEntry(zipEntry);
	    byte[] bytes = new byte[1024];
	    int length;
	    while ((length = fis.read(bytes)) >= 0) {
	        zipOut.write(bytes, 0, length);
	    }
	    fis.close();
	}
	
	public static String compressDirectory(String directory) throws IOException {
		String compressedFile = directory + ".zip";
        FileOutputStream fos = new FileOutputStream(compressedFile);
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        File fileToZip = new File(directory);
        zipFile(fileToZip, fileToZip.getName(), zipOut);
        zipOut.close();
        fos.close();
        
        return compressedFile;
	}
	
	public static String uncompress(String zipFile) throws IOException {
		
		Path path = Paths.get(zipFile);
        Path filePath = path.getFileName();
        String fileName = filePath.toString();
        if(fileName.endsWith(".zip")) {
        	fileName = fileName.substring(0, fileName.length() - 4);
        }else {
        	fileName = fileName + ".unzip";
        }
        //String compressedFile = path.getParent().toAbsolutePath() + "/" + fileName;
        String uncompressDir = path.getParent().toAbsolutePath() + "/" + fileName;
        File destDir = new File(uncompressDir);

        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
        	File newFile = newFile(destDir, zipEntry);
            if (zipEntry.isDirectory()) {
                if (!newFile.isDirectory() && !newFile.mkdirs()) {
                    throw new IOException("Failed to create directory " + newFile);
                }
            } else {
                // fix for Windows-created archives
                File parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    throw new IOException("Failed to create directory " + parent);
                }

                // write file content
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zipEntry = zis.getNextEntry();
        }

        zis.closeEntry();
        zis.close();
        
        return uncompressDir;
	}
	
	private static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
	    File destFile = new File(destinationDir, zipEntry.getName());

	    String destDirPath = destinationDir.getCanonicalPath();
	    String destFilePath = destFile.getCanonicalPath();

	    if (!destFilePath.startsWith(destDirPath + File.separator)) {
	        throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
	    }

	    return destFile;
	}
	
}
