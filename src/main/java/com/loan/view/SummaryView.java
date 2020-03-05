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
import java.util.Currency;
import java.util.Locale;

class SummaryView extends VBox {
	private LoanModel model;

	private final Label etcRataLunara = new Label("MONTHLY PAYMENT");
	private Text textRataLunara = new Text();
	private final Label etcTotalPlata = new Label("TOTAL PAYMENT");
	private Text textTotalPlata = new Text();
	private final Label etcDobandaT = new Label("TOTAL INTEREST");
	private Text textDobandaT = new Text();
	private PieChart chart = new PieChart();

	SummaryView(LoanModel model) {
		this.model = model;

		init();
	}

	private void init() {
		Locale locale = Locale.getDefault();
		Currency currency = Currency.getInstance(locale);
		String symbol = currency.getSymbol();

		textTotalPlata.textProperty().bind(model.totalPlataProperty().asString(symbol + "%.2f"));
		textRataLunara.textProperty().bind(model.rataLunaProperty().asString(symbol + "%.2f"));
		textDobandaT.textProperty().bind(model.totalDobandaProperty().asString(symbol + "%.2f"));

		Group totalPaymentGroup = getGroup(textTotalPlata, etcTotalPlata);
		Group monthlyPaymentGroup = getGroup(textRataLunara, etcRataLunara);
		Group totalInterestGroup = getGroup(textDobandaT, etcDobandaT);

		HBox contInfo = new HBox(80);
		contInfo.getChildren().addAll(totalPaymentGroup, monthlyPaymentGroup, totalInterestGroup);

		chart.getData().setAll(
			new PieChart.Data("Principal", 0.0),
			new PieChart.Data("Interest", 0.0));
		chart.setStartAngle(90);
		chart.getData().get(0).pieValueProperty().bind(model.valoareProperty());
		chart.getData().get(1).pieValueProperty().bind(model.totalDobandaProperty());
		chart.setLabelsVisible(true);
		chart.setClockwise(false);

		setAlignment(Pos.CENTER);
		VBox.setVgrow(chart, Priority.ALWAYS);
		getChildren().addAll(new Group(contInfo), chart);
	}

	private Group getGroup(Text amount, Label description) {
		VBox cont = new VBox(-3);
		cont.setAlignment(Pos.CENTER);
		amount.setFont(Font.font(null, FontWeight.BOLD, 52));
		description.setFont(Font.font(null, 24));
		cont.getChildren().addAll(amount, description);
		return new Group(cont);
	}
}
