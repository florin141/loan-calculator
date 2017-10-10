package com.loan.model;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.*;

public class LoanModel {

	private DoubleProperty valoare = new SimpleDoubleProperty();
	private IntegerProperty nrLuni = new SimpleIntegerProperty();
	private DoubleProperty dobandaAn = new SimpleDoubleProperty();

	private ReadOnlyDoubleWrapper dobandaLuna = new ReadOnlyDoubleWrapper();
	private ReadOnlyDoubleWrapper rataLuna = new ReadOnlyDoubleWrapper();
	private ReadOnlyDoubleWrapper totalPlata = new ReadOnlyDoubleWrapper();
	private ReadOnlyDoubleWrapper totalDobanda = new ReadOnlyDoubleWrapper();

	private final DoubleBinding calcNumitor = new DoubleBinding() {
		{ this.bind(nrLuni, dobandaLuna); }

		@Override
		protected double computeValue() {
			double numarLuni = nrLuni.get();
			double dobandaLunara = dobandaLuna.get();

			return 1.0 - (1.0 / Math.pow(1 + dobandaLunara, numarLuni));
		}
	};

	public LoanModel() {

		this(14000, 60, 16);

	}

	public LoanModel(double valoare, int numarLuni, double dobandaAn) {

		setValoare(valoare);
		setNrLuni(numarLuni);
		setDobandaAn(dobandaAn);

		this.dobandaLuna.bind(this.dobandaAn.divide(12.0 * 100.0));
		this.rataLuna.bind(this.valoare.multiply(dobandaLuna).divide(calcNumitor));
		this.totalPlata.bind(this.rataLuna.multiply(this.nrLuni));
		this.totalDobanda.bind(this.totalPlata.subtract(this.valoare));
	}


	/* Getter-ul proprietatii valoare */
	public double getValoare() {
		return valoare.get();
	}

	/* Setter-ul proprietatii valoare */
	public void setValoare(double valoare) {
		this.valoare.set(valoare);
	}

	/* Returneaza referinta proprietatii */
	public DoubleProperty valoareProperty() {
		return valoare;
	}


	/* Getter-ul proprietatii nrLuni */
	public int getNrLuni() {
		return nrLuni.get();
	}
	/* Setter-ul proprietatii nrLuni */
	public void setNrLuni(int nrLuni) {
		this.nrLuni.set(nrLuni);
	}
	/* Returneaza referinta proprietatii */
	public IntegerProperty nrLuniProperty() {
		return nrLuni;
	}


	/* Getter-ul proprietatii dobandaAn */
	public double getDobandaAn() {
		return dobandaAn.get();
	}
	/* Setter-ul proprietatii dobandaAn */
	public void setDobandaAn(double dobandaAn) {
		this.dobandaAn.set(dobandaAn);
	}
	/* Returneaza referinta proprietatii */
	public DoubleProperty dobandaAnProperty() {
		return dobandaAn;
	}


	/* Getter-ul proprietatii totalPlata */
	public double getTotalPlata() {
		return totalPlata.get();
	}
	/* Returneaza referinta proprietatii */
	public ReadOnlyDoubleProperty totalPlataProperty() {
		return totalPlata.getReadOnlyProperty();
	}

	/* Getter-ul proprietatii rataLuna */
	public double getRataLuna() {
		return rataLuna.get();
	}
	/* Returneaza referinta proprietatii */
	public ReadOnlyDoubleProperty rataLunaProperty() {
		return rataLuna.getReadOnlyProperty();
	}


	/* Getter-ul proprietatii totalDobanda */
	public double getTotalDobanda() {
		return totalDobanda.get();
	}
	/* Returneaza referinta proprietatii */
	public ReadOnlyDoubleProperty totalDobandaProperty() {
		return totalDobanda.getReadOnlyProperty();
	}


	/* Getter-ul proprietatii dobandaLuna */
	public double getDobandaLuna() {
		return dobandaLuna.get();
	}
	/* Returneaza referinta proprietatii */
	public ReadOnlyDoubleProperty dobandaLunaProperty() {
		return dobandaLuna.getReadOnlyProperty();
	}
}
