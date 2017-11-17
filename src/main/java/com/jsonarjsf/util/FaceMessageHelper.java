package com.jsonarjsf.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FaceMessageHelper {
	public static void  showFaceMessage(String message, String description) {
		if(FacesContext.getCurrentInstance().getMessageList().isEmpty())
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, description
					));
	}
}
