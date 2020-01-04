package fileio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class Save {

	public static void write(String str) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("recent.txt")));) {
			bw.write(str);
			System.out.println("Successfully written to the file.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//If destination file not created section removed since these two files will be strictly guiding
	public static void copy(File source, File destination) {
		
		try (FileInputStream in = new FileInputStream(source);
			 FileOutputStream out = new FileOutputStream(destination);
			 FileChannel from = in.getChannel();
		     FileChannel to = out.getChannel();
			 ) {
			
			to.transferFrom(from, 0, from.size());
			System.out.println("File copied successfully.");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//copy files as per transfer protocol
	public static void main(String[] args) {
		write("First.");
		String content = "Hello World!";
		copy(new File("recent.txt"), new File("previous.txt"));
		write(content);
	}

}
