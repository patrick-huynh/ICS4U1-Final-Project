package fileio;

import java.io.BufferedInputStream;
import java.net.URL;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.net.MalformedURLException;

import java.nio.channels.FileChannel;

public class Retrieve {

	/*Method improvement: set up new File in local directory, and then copy contents to it.*/
	public static void bufferRetrieve(File file) {
		try (BufferedInputStream input = new BufferedInputStream(new URL(file.toURI().toURL().toString()).openStream());
			 FileOutputStream output = new FileOutputStream(file.getName());
				) {
			
			byte[] data_buffer = new byte[1024];
			int bytes_read;
			
			while ((bytes_read = input.read(data_buffer, 0, 1024)) != -1) {
				output.write(data_buffer, 0, bytes_read);
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*https://stackoverflow.com/questions/9658297/java-how-to-create-a-file-in-a-directory-using-relative-path/9658353*/
	public static void channelRetrieve() {
		
	}
	
	public static void main(String[] args) {
		bufferRetrieve(new File("recent.txt"));

	}

}
