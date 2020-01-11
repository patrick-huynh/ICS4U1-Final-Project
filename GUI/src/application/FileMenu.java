package application;

import java.net.URISyntaxException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public abstract class FileMenu {
	private final MenuButton button;
	protected String prev_path, recent_path;
	
	public FileMenu() {
		button = new MenuButton("");
		
		try {
			ImageView image = new ImageView(new Image(getClass().getResource("/IMAGES/hamburger.png").toURI().toString()));
			button.setGraphic(image);
			//Loads recent.txt to the tables
			MenuItem load = new MenuItem("Load");
			//File transfer protocol
			MenuItem save = new MenuItem("Save");
			MenuItem reload = new MenuItem("Reload");
			MenuItem retrieve = new MenuItem("Retrieve");
			
			button.getItems().addAll(load, save, reload, retrieve);
			
		} catch (URISyntaxException use) {
			use.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public FileMenu(String prev_path, String recent_path) {
		this();
		this.prev_path = prev_path;
		this.recent_path = recent_path;
	}
	
	public MenuButton getMenuButton() {
		return button;
	}
	
	/*public String getPrevPath() {
		return prev_path;
	}
	
	public void setPrevPath(String prev_path) {
		this.prev_path = prev_path;
	}
	
	public String getRecentPath() {
		return recent_path;
	}
	
	public void setRecentPath(String recent_path) {
		this.recent_path = recent_path;
	}*/
	
	protected void copy(File source, File destination) {
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
	
	public abstract void save();
	public abstract void load();
	public abstract void reload();
	public abstract void retrieve();
}
