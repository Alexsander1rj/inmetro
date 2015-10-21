package br.gov.inmetro.validation;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class EmailValida implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher matcher = pattern.matcher((String) value);
		
		if(!matcher.matches()){
			((UIInput)component).setValid(false);
			
			ResourceBundle bundle = ResourceBundle.getBundle("messages_labels");
			
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("validacao.emailInvalido"), bundle.getString("validacao.emailInvalido"));
			
			context.addMessage(null, msg);
		}
	}
}