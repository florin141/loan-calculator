package com.loan.view;

import com.loan.model.LoanModel;
import com.loan.model.TableDataUtil;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class LoanView extends BorderPane {
	private LoanModel model;
	
	/** Constants */
	private static final double INALTIME_PREF_SUS = 80;
	private static final double INALTIME_PREF_JOS = 80;
	private static final Background BACKGROUND = new Background(new BackgroundFill(Color.ROYALBLUE, CornerRadii.EMPTY, Insets.EMPTY));

	/** Top */
	private TextField cdtValoareImp = new TextField();
	private final Label etcValoareImp = new Label("Loan Amount");
	private TextField cdtNrLuni = new TextField();
	private final Label etcNrLuni = new Label("Loan Term (months)");
	private TextField cdtDobanda = new TextField();
	private final Label etcDobanda = new Label("Annual Interest Rate (%)");

	/** BOTTOM */
	private final Button btnAmortizationToggle = new Button("Show amortization schedule");

	/** Center */
	private SummaryView summaryView;
	private LoanTableView loanTableView;


	public LoanView(LoanModel model) {
		this.model = model;
		this.summaryView = new SummaryView(model);
		this.loanTableView = new LoanTableView(TableDataUtil.getTableData(model));

		setTop(contSus());
		setCenter(summaryView);
		setBottom(contJos());
	}

	@SuppressWarnings("unchecked")
	private HBox contSus() {
		HBox contSus = new HBox();

		StringConverter douStrConv = new DoubleStringConverter();
		StringConverter intStrConv = new IntegerStringConverter();

		cdtValoareImp.textProperty().bindBidirectional(model.valoareProperty(), douStrConv);
		cdtNrLuni.textProperty().bindBidirectional(model.nrLuniProperty(), intStrConv);
		cdtDobanda.textProperty().bindBidirectional(model.dobandaAnProperty(), douStrConv);

		cdtValoareImp.textProperty().addListener(this::actualizeazaTabel);
		cdtNrLuni.textProperty().addListener(this::actualizeazaTabel);
		cdtDobanda.textProperty().addListener(this::actualizeazaTabel);

		set(etcValoareImp, cdtValoareImp);
		set(etcNrLuni, cdtNrLuni);
		set(etcDobanda, cdtDobanda);

		contSus.setSpacing(20);
		contSus.setBackground(BACKGROUND);
		contSus.setPrefHeight(INALTIME_PREF_SUS);
		contSus.setMinHeight(USE_PREF_SIZE);
		contSus.setMaxHeight(USE_PREF_SIZE);
		contSus.setAlignment(Pos.CENTER);
		contSus.getChildren().addAll(etcValoareImp, etcNrLuni, etcDobanda);

		return contSus;
	}

	private HBox contJos() {
		HBox contJos = new HBox(20);

		btnAmortizationToggle.setFont(Font.font(null, FontWeight.BOLD, 12));
		btnAmortizationToggle.setPrefSize(200, 25);

		contJos.getChildren().addAll(btnAmortizationToggle);
		contJos.setBackground(BACKGROUND);
		contJos.setPrefHeight(INALTIME_PREF_JOS);
		contJos.setMinHeight(USE_PREF_SIZE);
		contJos.setMaxHeight(USE_PREF_SIZE);
		contJos.setAlignment(Pos.CENTER);

		return contJos;
	}

	private void set(Label lbl, TextField tf) {
		tf.setOnMouseClicked(event -> tf.selectAll());
		tf.setAlignment(Pos.CENTER_RIGHT);
		lbl.setFont(Font.font(null, FontWeight.BOLD, 12));
		lbl.setTextFill(Color.WHITE);
		lbl.setGraphic(tf);
		lbl.setContentDisplay(ContentDisplay.RIGHT);
	}

	public Button butGraficRamb() {
		return btnAmortizationToggle;
	}

	public LoanTableView loanTableView() {
		return loanTableView;
	}

	public SummaryView summaryView() {
		return summaryView;
	}

	public void actualizeazaTabel(ObservableValue o, String ov, String nv) {
		loanTableView.setItems(TableDataUtil.getTableData(model));
	}
}
