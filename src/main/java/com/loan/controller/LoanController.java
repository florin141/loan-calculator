package com.loan.controller;

import com.loan.view.LoanView;
import javafx.event.Event;

public class LoanController {
	private final LoanView view;

	public LoanController(LoanView view) {
		this.view = view;

		/* se inregistreaza metodele de tratare a evenimentelor*/
		view.butGraficRamb().setOnMouseClicked(this::afiseazaGraficRamb);
	}

	private void afiseazaGraficRamb(Event event) {
		if (!view.getCenter().equals(view.loanTableView())) {
			view.setCenter(view.loanTableView());
			view.butGraficRamb().setText("Hide amortization schedule");
		}
		else if (!view.getCenter().equals(view.summaryView())) {
			view.setCenter(view.summaryView());
			view.butGraficRamb().setText("Show amortization schedule");
		}
	}
}
