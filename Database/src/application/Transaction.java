package application;

import javafx.beans.property.SimpleStringProperty;

public class Transaction {
	private final SimpleStringProperty nameID;
	private final SimpleStringProperty puchase;
	private final SimpleStringProperty quantityT;
	private final SimpleStringProperty portions;
	
	Transaction(String nNameID, String nPuchase, String nQuantityT, String nPortion) {
		this.nameID = new SimpleStringProperty(nNameID);
		this.puchase = new SimpleStringProperty(nPuchase);
		this.quantityT = new SimpleStringProperty(nQuantityT);
		this.portions = new SimpleStringProperty(nPortion);
	}
	
	public String getNameID() {
		return nameID.get();
	}
	
	public void setNameID(String nNameID) {
		nameID.set(nNameID);
	}
	
	public String getPurchase() {
		return puchase.get();
	}
	
	public void setPurchase(String nPuchase) {
		puchase.set(nPuchase);
	}
	
	public String getQuantityT() {
		return quantityT.get();
	}
	
	public void setQuantityT(String nQuantityT) {
		quantityT.set(nQuantityT);
	}
	
	public String getPortions() {
		return portions.get();
	}
	
	public void setPortions(String nPortion) {
		portions.set(nPortion);
	}
}
