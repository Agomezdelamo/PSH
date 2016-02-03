package com.mitocode.listeners;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class FaseListener implements PhaseListener {

	private static final long serialVersionUID = 1L;
	private String idPhase = "";
	private static int countCycle;

	@Override
	public void afterPhase(PhaseEvent e) {	
		idPhase = e.getPhaseId().toString();
		System.out.println("AFTER fase: ---------------> " + e.getPhaseId());
		
		if(idPhase.endsWith("6")) { 
			countCycle++;
			System.out.println("\r\n--------------------FIN CICLO DE VIDA nº "+countCycle+"--------------------\r\n\r\n");}
	}

	@Override
	public void beforePhase(PhaseEvent e) {
		System.out.println("BEFORE fase: ---------------> " + e.getPhaseId());

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
