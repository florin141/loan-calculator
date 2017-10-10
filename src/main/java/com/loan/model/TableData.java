package com.loan.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/* Declaratiile de import ale claselor folosite sunt omise */

public class TableData {

	private IntegerProperty luna = new SimpleIntegerProperty();
	private DoubleProperty soldCreditA = new SimpleDoubleProperty();
	private DoubleProperty dobandaLunara = new SimpleDoubleProperty();
	private DoubleProperty rataCredit = new SimpleDoubleProperty();
	private DoubleProperty rataLunara = new SimpleDoubleProperty();
	private DoubleProperty soldCreditB = new SimpleDoubleProperty();

	public TableData(int luna) {
		setLuna(luna);
	}
	
	public int getLuna() {
		return luna.get();
	}
	
	public IntegerProperty lunaProperty() {
		return luna;
	}
	
	private void setLuna(int luna) {
		this.luna.set(luna);
	}
	
	public double getSoldCreditA() {
		return soldCreditA.get();
	}

	public DoubleProperty soldCreditAProperty() {
		return soldCreditA;
	}

	public void setSoldCreditA(double soldCreditA) {
		this.soldCreditA.set(soldCreditA);
	}

	public double getDobandaLunara() {
		return dobandaLunara.get();
	}

	public DoubleProperty dobandaLunaraProperty() {
		return dobandaLunara;
	}

	public void setDobandaLunara(double dobandaLunara) {
		this.dobandaLunara.set(dobandaLunara);
	}

	public double getRataCredit() {
		return rataCredit.get();
	}

	public DoubleProperty rataCreditProperty() {
		return rataCredit;
	}

	public void setRataCredit(double rataCredit) {
		this.rataCredit.set(rataCredit);
	}

	public double getRataLunara() {
		return rataLunara.get();
	}

	public DoubleProperty rataLunaraProperty() {
		return rataLunara;
	}

	public void setRataLunara(double rataLunara) {
		this.rataLunara.set(rataLunara);
	}

	public double getSoldCreditB() {
		return soldCreditB.get();
	}

	public DoubleProperty soldCreditBProperty() {
		return soldCreditB;
	}

	public void setSoldCreditB(double soldCreditB) {
		this.soldCreditB.set(soldCreditB);
	}
}
