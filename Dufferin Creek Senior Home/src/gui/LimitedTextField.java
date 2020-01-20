package gui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class LimitedTextField extends TextField {
	
	private IntegerProperty maxLength;
	private BooleanProperty isNumeric;
	
	/**Creates a TextField with default length and input type properties.*/
	public LimitedTextField() {
		super();
		maxLength = new SimpleIntegerProperty(255);
		isNumeric = new SimpleBooleanProperty();
		isNumeric.set(false);
	}
	
	/**Creates a TextField with default length and input type properties with prompt text.
	@param text - The text to append to the TextField space.*/
	public LimitedTextField(String text) {
		super(text);
		maxLength = new SimpleIntegerProperty(255);
	}
	
	/**Gets the maximum length property of this LimitedTextField.
	@return IntegerProperty*/
	public IntegerProperty maxLengthProperty() {
		return maxLength;
	}
	
	/**Gets the value of the maximum length property as a primitive int type.
	@return int*/
	public final int getMaxLength() {
		return maxLength.get();
	}
	
	/**Returns whether or not this field is numeric.
	@return boolean*/
	public final boolean isNumeric() {
		return isNumeric.get();
	}
	
	/**Sets the maximum length of this LimitedTextField (overrides the initial commit) if it is greater than or equal to 0.
	@param maximum - The upper limit of the length property.
	@return boolean*/
	public final boolean setMaxLength(int maximum) {
		if (maximum >= 0) {
			this.maxLength.set(maximum);
			return true;
		}
		System.err.println("Maximum length of the LimitedTextField cannot be less than 0.");
		return false;
	}
	
	/**Replaces the current text in the LimitedTextField if it exceeds the current length.
	@param start - The initial index of the target String object.
	@param end - The end index of the target String object.
	@param insertedText - The String object representing the text of interest.*/
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
	
	//Workaround to scope problems in the succeeding methods, in order.
	private LimitedTextField getThis() {
		return this;
	}
	
	/**Sets the LimitedTextField as numeric only (accepts only number symbols). Adds a listener to the text property.*/
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
        
	/**Sets the LimitedTextField as alphabetic only (accepts only ISO Latin Alphabetic characters). 
	Adds a listener to the text property.*/
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
