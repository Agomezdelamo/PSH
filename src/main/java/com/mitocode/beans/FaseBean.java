package com.mitocode.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;

@ManagedBean
@RequestScoped
public class FaseBean {

	public void evaluarFase(PhaseEvent evt) throws Exception {
		try {
			if (PhaseId.APPLY_REQUEST_VALUES.equals(evt.getPhaseId())) {
				System.out.println("DENTRO fase: ---------------> " + PhaseId.APPLY_REQUEST_VALUES);
			}
			if (PhaseId.INVOKE_APPLICATION.equals(evt.getPhaseId())) {
				System.out.println("DENTRO fase: ---------------> " + PhaseId.INVOKE_APPLICATION);
			}
			if (PhaseId.RENDER_RESPONSE.equals(evt.getPhaseId())) {
				System.out.println("DENTRO fase: ---------------> " + PhaseId.RENDER_RESPONSE);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String submit() {
		System.out.println("Acci�n enviada");
		return "index.xhtml";
	}

}
