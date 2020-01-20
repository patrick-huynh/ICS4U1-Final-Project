package utility;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

public final class TransferProtocol {
	
	/**Copies the contents of a source file to a target file.
	 * @param source - The source File object to copy from.
	 * @param target - The target File object to copy to.*/
    public static void copy(File source, File target) {
        try(FileInputStream in = new FileInputStream(source);
            FileOutputStream out = new FileOutputStream(target);
            FileChannel from = in.getChannel();
            FileChannel to = out.getChannel();
                ){
            
            to.transferFrom(from, 0, from.size());
            
        } catch (IOException e) {
            Logger.getLogger(TransferProtocol.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            Logger.getLogger(TransferProtocol.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /**Swaps the contents of two File objects using TransferProtocol.copy().
     * @param path - The path to a temporary file in the project source folder.*/
    public static void swap(File source, File target, String path) {
    	try {
        	File temp = new File(path);
        	copy(target, temp);
        	copy(source, target);
        	copy(temp, source);
        	FileChannel.open(Paths.get(temp.toURI()), StandardOpenOption.WRITE).truncate(0).close();
    	} catch (IOException e) {
    		Logger.getLogger(TransferProtocol.class.getName()).log(Level.SEVERE, null, e);
    	}
    }
    
    public static void updateChoicesSenior(ChoiceBox<Long> box, Senior senior, boolean add) {
    	if (add) {
    		box.getItems().add(senior.getHID());
    	} else {
    		box.getItems().remove(senior.getHID()); 
    	}
    }
    
    /**Saves the contents of ObservableList of type Senior to a designated source file.*/
    public static void saveSenior(File source, File target, ObservableList<Senior> list) {
    	copy(source, target);
    	
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter(source));
    			){
    		for (Senior senior : list) {
    			writer.write(senior.getFName());
    			writer.write(System.getProperty("line.separator"));
    			writer.write(senior.getLName());
    			writer.write(System.getProperty("line.separator"));
    			writer.write(senior.getDOB());
    			writer.write(System.getProperty("line.separator"));
    			writer.write(Integer.toString(senior.getAge()));
    			writer.write(System.getProperty("line.separator"));
    			writer.write(Integer.toString(senior.getRoomID()));
    			writer.write(System.getProperty("line.separator"));
    			writer.write(Double.toString(senior.getHours()));
    			writer.write(System.getProperty("line.separator"));
    			writer.write(Long.toString(senior.getHID()));
    			writer.write(System.getProperty("line.separator"));
    		}
    		
    	} catch (IOException e) {
    		Logger.getLogger(TransferProtocol.class.getName()).log(Level.SEVERE, null, e);
    		System.err.println("Error saving senior data. Ensure that recent_senior.txt and previous_senior.txt exist.");
    	}
    }
    
    public static void loadSenior(File source, ObservableList<Senior> list, ChoiceBox<Long> box) {
    	box.getItems().removeAll(box.getItems());
    	list.removeAll(list);
    	
    	try (BufferedReader reader = new BufferedReader(new FileReader(source));
    			){
    		String line;
    		
    		while ((line = reader.readLine()) != null) {
    			Senior toAdd = new Senior(line, reader.readLine(), reader.readLine(), 
    					Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()), 
    					Double.parseDouble(reader.readLine()), Long.parseLong(reader.readLine()));
    			list.add(toAdd);
    			updateChoicesSenior(box, toAdd, true);
    		}
    		
    	} catch (IOException e) {
    		Logger.getLogger(TransferProtocol.class.getName()).log(Level.SEVERE, null, e);
    		System.err.println("Error configuring senior data to table. Check that recent_senior.txt exists.");
    	}
    }
    
    public static void reloadSenior(File source, File target, ObservableList<Senior> list, ChoiceBox<Long> box) {
    	box.getItems().removeAll(box.getItems());
    	list.removeAll(list);
    	swap(source, target, "temp.txt");
    	loadSenior(source, list, box);
    }
    
    public static void updateChoicesCaregiver(ChoiceBox<Long> box, Caregiver caregiver, boolean add) {
    	if (add) {
    		box.getItems().add(caregiver.getEmpNum());
    	} else {
    		box.getItems().remove(caregiver.getEmpNum());
    	}
    }
    
    public static void saveCaregiver(File source, File target, ObservableList<Caregiver> list) {
    	copy(source, target);
    	
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter(source))
    			){
    		for (Caregiver caregiver : list) {
    			writer.write(caregiver.getFName());
    			writer.write(System.getProperty("line.separator"));
    			writer.write(caregiver.getLName());
    			writer.write(System.getProperty("line.separator"));
    			writer.write(caregiver.getDOB());
    			writer.write(System.getProperty("line.separator"));
    			writer.write(Integer.toString(caregiver.getAge()));
    			writer.write(System.getProperty("line.separator"));
    			writer.write(Integer.toString(caregiver.getRoomID()));
    			writer.write(System.getProperty("line.separator"));
    			writer.write(Long.toString(caregiver.getEmpNum()));
    			writer.write(System.getProperty("line.separator"));
    			writer.write(Double.toString(caregiver.getHours()));
    			writer.write(System.getProperty("line.separator"));
    			writer.write(Double.toString(caregiver.getWage()));
    		}
    		
    	} catch (IOException e) {
    		Logger.getLogger(TransferProtocol.class.getName()).log(Level.SEVERE, null, e);
    		System.err.println("Error saving caregiver data. Ensure that recent_caregiver.txt and previous_caregiver.txt"
    				+ " exist.");
    	}
    	
    }
    
    public static void loadCaregiver(File source, ObservableList<Caregiver> list, ChoiceBox<Long> box) {
    	box.getItems().removeAll(box.getItems());
    	list.removeAll(list);
    	
    	try (BufferedReader reader = new BufferedReader(new FileReader(source))
    			){
    		String line;
    		
    		while ((line = reader.readLine()) != null) {
    			Caregiver toAdd = new Caregiver(line, reader.readLine(), reader.readLine(), 
    					Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()), 
    					Long.parseLong(reader.readLine()), Double.parseDouble(reader.readLine()), 
    					Double.parseDouble(reader.readLine()));
    			list.add(toAdd);
    			updateChoicesCaregiver(box, toAdd, true);
    		}
    		
    	} catch (IOException e) {
    		Logger.getLogger(TransferProtocol.class.getName()).log(Level.SEVERE, null, e);
    		System.err.println("Error configuring caregiver data. Ensure that recent_caregiver.txt exists.");
    	}
    }
    
    public static void reloadCaregiver(File source, File target, ObservableList<Caregiver> list, ChoiceBox<Long> box) {
    	box.getItems().removeAll(box.getItems());
    	list.removeAll(list);
    	swap(source, target, "temp.txt");
    	loadCaregiver(source, list, box);
    }
    
    public static void loadSupplier(File source, ObservableList<Supplier> list) {
    	try (BufferedReader reader = new BufferedReader(new FileReader(source));
    			) {
    		String line;
    		
    		while((line = reader.readLine()) != null) {
    			Supplier toAdd = new Supplier(line, reader.readLine(), reader.readLine(), 
    					Double.parseDouble(reader.readLine()), Integer.parseInt(reader.readLine()),
    							Double.parseDouble(reader.readLine()));
    			list.add(toAdd);
    		}
    		
    	} catch (IOException e) {
    		Logger.getLogger(TransferProtocol.class.getName()).log(Level.SEVERE, null, e);
    		System.err.println("Error configuring suppliers list to table. Check that suppliers.txt exists.");
    	}
    }
    
    public static void updateChoicesInventory(ChoiceBox<String> box, FoodItem item, boolean add) {
    	if (add) {
    		box.getItems().add(item.getName());
    	} else {
    		box.getItems().remove(item.getName());
    	}
    }
    
    public static void saveInventory(File source, File target, ObservableList<FoodItem> list) {
    	copy(source, target);
    	
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter(source));
    			) {
    		for (FoodItem item : list) {
    			//FOODITEM PARAMETERS
    			writer.write(item.getName());
    			writer.write(System.getProperty("line.separator"));
    			writer.write(item.getExpiryDate());
    			writer.write(System.getProperty("line.separator"));
    			
    			//FOODGROUP PARAMETERS IN MOST-POPULATED CONSTRUCTOR
    			writer.write(item.getGroupName());	
    			writer.write(System.getProperty("line.separator"));
    			
    			//SUPPLIER INFORMATION NECESSARY TO CREATE NEW FOODGROUP AND PRESERVe SUPPLIER INFORMATION ON LOAD
    			writer.write(item.getGroup().getSupplier().getName());
    			writer.write(System.getProperty("line.separator"));
    			writer.write(item.getGroup().getSupplier().getAddress());
    			writer.write(System.getProperty("line.separator"));
    			writer.write(item.getGroup().getSupplier().getPhone());
    			writer.write(System.getProperty("line.separator"));
    			writer.write(Double.toString(item.getGroup().getSupplier().getStdCost()));
    			writer.write(System.getProperty("line.separator"));
    			writer.write(Integer.toString(item.getGroup().getSupplier().getStdQty()));
    			writer.write(System.getProperty("line.separator"));
    			writer.write(Double.toString(item.getGroup().getSupplier().getFlatFee()));
    			writer.write(System.getProperty("line.separator"));
    			
    			//FOODITEM PARAMETERS
    			writer.write(Integer.toString(item.getQuantity()));
    			writer.write(System.getProperty("line.separator"));
    			writer.write(Integer.toString(item.getStock()));
    			writer.write(System.getProperty("line.separator"));
    		}
    		
    	} catch (IOException e) {
    		System.err.println("Error saving to file. Ensure recent_inventory.txt and previous_inventory.txt exist.");
    		Logger.getLogger(TransferProtocol.class.getName()).log(Level.SEVERE, null, e);
    	}
    }
    
    public static void loadInventory(File source, Inventory inventory, ObservableList<FoodItem> list, 
    		ChoiceBox<String> box) {
    	
    	box.getItems().removeAll(box.getItems());
    	list.removeAll(list);
    	
    	try (BufferedReader reader = new BufferedReader(new FileReader(source));	
    			) {
    		
    		String line;
    		while((line = reader.readLine()) != null) {
    			FoodItem toAdd = new FoodItem(line, reader.readLine(), new FoodGroup(reader.readLine(), 
    					new Supplier(reader.readLine(), reader.readLine(), reader.readLine(), 
    							Double.parseDouble(reader.readLine()), Integer.parseInt(reader.readLine()),
    							Double.parseDouble(reader.readLine()))), 
    					Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()));
    			inventory.addItem(toAdd);
    			list.add(toAdd);
    			updateChoicesInventory(box, toAdd, true);
    		}
    	} catch (IOException e) {
    		Logger.getLogger(TransferProtocol.class.getName()).log(Level.SEVERE, null, e);
    		System.err.println("Error configuring inventory list to table. Check that recent_inventory.txt exists.");
    	}
    }
    
    public static void reloadInventory(File source, File target, Inventory inventory, ObservableList<FoodItem> list,
    		ChoiceBox<String> box) {
    	box.getItems().removeAll(box.getItems());
    	list.removeAll(list);
    	swap(source, target, "temp.txt");
    	loadInventory(source, inventory, list, box);
    }
    
}
