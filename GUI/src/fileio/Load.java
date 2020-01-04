package fileio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

public class Load {

	public static void load(File target) {
		try (BufferedReader br = new BufferedReader(new FileReader(target));
				) {
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new FileReader(new File("recent.txt")));) {
			
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);	//realistically, I/O processing would be more complex than this
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();	//use Logger instead to handle error tracing and transfer
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
