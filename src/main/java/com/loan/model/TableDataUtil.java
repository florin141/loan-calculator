package com.loan.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

import java.text.NumberFormat;
import java.util.Locale;

public class TableDataUtil {

	private static final String FORMAT = "%.2f";

	/* Returns an observable list of TableData */
	public static ObservableList<TableData> getTableData(LoanModel model) {
		ObservableList<TableData> tableData =
										FXCollections.observableArrayList();

		double dobandaLuna = model.getDobandaLuna();
		double valoare = model.getValoare();
		double rataLuna = model.getRataLuna();

		{
			TableData td = new TableData(1);
			td.setSoldCreditA(valoare);
			td.setDobandaLunara(td.getSoldCreditA() * dobandaLuna);
			td.setRataCredit(rataLuna - td.getDobandaLunara());
			td.setRataLunara(rataLuna);
			td.setSoldCreditB(td.getSoldCreditA() - td.getRataCredit());
			tableData.add(td);
		}
		for (int row = 1; row < model.getNrLuni(); row++) {
			TableData td = new TableData(row + 1);
			td.setSoldCreditA(tableData.get(row - 1).getSoldCreditB());
			td.setDobandaLunara(td.getSoldCreditA() * dobandaLuna);
			td.setRataCredit(rataLuna - td.getDobandaLunara());
			td.setRataLunara(rataLuna);
			td.setSoldCreditB(td.getSoldCreditA() - td.getRataCredit());

			tableData.add(td);
		}

		return tableData;
	}

	/* Returns Amortization Payment TableColumn */
	public static TableColumn<TableData, String> colLuna() {
		TableColumn<TableData, String> colLuna = new TableColumn<>("Payment");
		colLuna.setCellValueFactory(colData -> colData.getValue().lunaProperty().asString());
		colLuna.setCellFactory(cell -> new MyFormatCell());
		return colLuna;
	}

	/* Returns Amortization Beginning Balance TableColumn */
	public static TableColumn<TableData, String> colSoldCreditA() {
		TableColumn<TableData, String> soldCreditA =
									new TableColumn<>("Beginning Balance");
		soldCreditA.setCellValueFactory(colData -> colData.getValue().soldCreditAProperty().asString(FORMAT));
		soldCreditA.setCellFactory(cell -> new MyFormatCell());
		return soldCreditA;
	}

	/* Returns Amortization Interest TableColumn */
	public static TableColumn<TableData, String> colDobandaLunara() {
		TableColumn<TableData, String> dobandaLunara =
										new TableColumn<>("Interest");
		dobandaLunara.setCellValueFactory(colData -> colData.getValue().dobandaLunaraProperty()
													.asString(FORMAT));
		dobandaLunara.setCellFactory(cell -> new MyFormatCell());
		return dobandaLunara;
	}

	/* Returns Amortization Principal TableColumn */
	public static TableColumn<TableData, String> colRataCredit() {
		TableColumn<TableData, String> rataCredit =
											new TableColumn<>("Principal");
		rataCredit.setCellValueFactory(colData -> colData.getValue().rataCreditProperty().asString(FORMAT));
		rataCredit.setCellFactory(cell -> new MyFormatCell());
		return rataCredit;
	}

	/* Returns Amortization Monthly Payment TableColumn */
	public static TableColumn<TableData, String> colRataLunara() {
		TableColumn<TableData, String> rataLunara =
											new TableColumn<>("Payment");
		rataLunara.setCellValueFactory(colData -> colData.getValue().rataLunaraProperty().asString(FORMAT));
		rataLunara.setCellFactory(cell -> new MyFormatCell());
		return rataLunara;
	}

	/* Returns Amortization Principal TableColumn */
	public static TableColumn<TableData, String> colSoldCreditB() {
		TableColumn<TableData, String> soldCreditSfarsit =
								new TableColumn<>("Ending Balance");
		soldCreditSfarsit.setCellValueFactory(colData -> colData.getValue().soldCreditBProperty().asString(FORMAT));
		soldCreditSfarsit.setCellFactory(cell -> new MyFormatCell());
		return soldCreditSfarsit;
	}

	public static class MyFormatCell extends TableCell<TableData, String> {

		MyFormatCell() { }

		@Override
		protected void updateItem(String item, boolean empty) {
			// calling super here is very important - don't skip this!
			super.updateItem(item, empty);

			// set text to null
			setText(null);
			// set alignment
			setAlignment(Pos.CENTER);

			// set text to item
			if (!empty) {
				this.setText(item);
			}
		}
	}
}
