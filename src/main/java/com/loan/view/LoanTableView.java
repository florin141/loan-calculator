package com.loan.view;

import com.loan.model.TableData;
import com.loan.model.TableDataUtil;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class LoanTableView extends TableView<TableData> {

	@SuppressWarnings("unchecked")
	public LoanTableView(ObservableList<TableData> colData) {
		this.setItems(colData);
		this.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);

		TableColumn col1 = TableDataUtil.colLuna();
		TableColumn col2 = TableDataUtil.colSoldCreditA();
		TableColumn col3 = TableDataUtil.colDobandaLunara();
		TableColumn col4 = TableDataUtil.colRataCredit();
		TableColumn col5 = TableDataUtil.colRataLunara();
		TableColumn col6 = TableDataUtil.colSoldCreditB();

		getColumns().addAll(col1, col2, col3, col4, col5, col6);
	}
}
