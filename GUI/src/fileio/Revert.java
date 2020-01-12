package fileio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;

//Exchange the contents of two files
public class Revert {

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
	
	//https://docs.oracle.com/javase/tutorial/essential/io/file.html Create temporary file
	public static void swap(File source, File target) {
		File temp = new File("temp.txt");
		copy(target, temp);
		copy(source, target);
		copy(temp, source);
	}
	

	
	public static void main(String[] args) {
		write("Hello world.");
		swap(new File("recent.txt"), new File("previous.txt"));
	}

}
