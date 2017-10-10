package com.loan.view;

import com.loan.model.LoanModel;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SummaryView extends VBox {
	private LoanModel model;

	private final Label etcRataLunara = new Label("RATA LUNARA");
	private Text textRataLunara = new Text();
	private final Label etcTotalPlata = new Label("TOTAL DE PLATA");
	private Text textTotalPlata = new Text();
	private final Label etcDobandaT = new Label("DOBANDA TOTALA");
	private Text textDobandaT = new Text();
	private PieChart diagram = new PieChart();

	public SummaryView(LoanModel model) {
		this.model = model;

		init();
	}

	private void init() {
		textTotalPlata.textProperty().bind(model.totalPlataProperty().asString("$%.2f"));
		textRataLunara.textProperty().bind(model.rataLunaProperty().asString("$%.2f"));
		textDobandaT.textProperty().bind(model.totalDobandaProperty().asString("$%.2f"));

		Group stanga = obtGrup(textTotalPlata, etcTotalPlata);
		Group centru = obtGrup(textRataLunara, etcRataLunara);
		Group dreapta = obtGrup(textDobandaT, etcDobandaT);

		HBox contInfo = new HBox(80);
		contInfo.getChildren().addAll(stanga, centru, dreapta);

		diagram.getData().setAll(
			new PieChart.Data("Credit", 0.0),
			new PieChart.Data("Dobanda", 0.0));
		diagram.setStartAngle(90);
		diagram.getData().get(0).pieValueProperty().bind(model.valoareProperty());
		diagram.getData().get(1).pieValueProperty().bind(model.totalDobandaProperty());
		diagram.setLabelsVisible(true);
		diagram.setClockwise(false);

		setAlignment(Pos.CENTER);
		VBox.setVgrow(diagram, Priority.ALWAYS);
		getChildren().addAll(new Group(contInfo), diagram);
	}

	private Group obtGrup(Text textTotalPlata, Label etcTotalPlata) {
		VBox cont = new VBox(-3);
		cont.setAlignment(Pos.CENTER);
		textTotalPlata.setFont(Font.font(null, FontWeight.BOLD, 52));
		etcTotalPlata.setFont(Font.font(null, 24));
		cont.getChildren().addAll(textTotalPlata, etcTotalPlata);
		return new Group(cont);
	}
}
