package com.loan;

import com.loan.controller.LoanController;
import com.loan.model.LoanModel;
import com.loan.view.LoanView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static final double WIDTH  = 1280 - 2; /* default window width */
    private static final double HEIGHT = 720 - 32; /* default window height */

    public static void main(String[] args) {
        launch(args);
    }

    @Override /* Suprascrie metoda start() din clasa Application */
    public void start(Stage fereastra) {
        LoanModel model = new LoanModel(14000, 60, 16); /* creaza modelul */
        LoanView view = new LoanView(model); /* creaza interfata */
        LoanController controller = new LoanController(view);

        Scene scena = new Scene(view, WIDTH, HEIGHT);
        fereastra.setScene(scena);
        fereastra.setTitle("Loan Calculator");
        fereastra.show();
    }
}
