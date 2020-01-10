package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class LimitedTextField extends TextField {
	
	private IntegerProperty maxLength;
	
	public LimitedTextField() {
		super();
		maxLength = new SimpleIntegerProperty(255);
	}
	
	public LimitedTextField(String text) {
		super(text);
		maxLength = new SimpleIntegerProperty(255);
	}
	
	public IntegerProperty maxLentghProperty() {
		return maxLength;
	}
	
	public final int getMaxLength() {
		return maxLength.get();
	}
	
	public final boolean setMaxLength(int maximum) {
		if (maximum >= 0) {
			this.maxLength.set(maximum);
			return true;
		}
		System.err.println("Maximum length of the LimitedTextField cannot be less than 0.");
		return false;
	}
	
	@Override
	public void replaceText(int start, int end, String insertedText) {
		String currentText = this.getText() == null ? "" : this.getText();
		String finalText = currentText.substring(0, start).concat(insertedText).concat(currentText.substring(end));
		
		int exceeding = finalText.length() - this.getMaxLength();
		
		if (exceeding <= 0) {
			super.replaceText(start, end, insertedText);
		} else {
			String cutInsertedText = insertedText.substring(0, insertedText.length() - exceeding);
			super.replaceText(start,  end, cutInsertedText);
		}
	}
	
	//Scope problems
	private LimitedTextField getThis() {
		return this;
	}
	
	public void setAsNumericOnly() {
		this.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.matches("\\d*")) {
					getThis().setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
	}
        
        public void setAsAlphaOnly() {
            this.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue,
                        String newValue) {
                    if(!newValue.matches("/^[A-Za-z]+$/")) {
                        getThis().setText(newValue.replaceAll("[^a-zA-Z]", ""));
                    }
                }
            });
        }
}
