package Main;

import Skaiciavimai.AnnuityCalculate;
import Skaiciavimai.LinearCalculate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GraphPanel extends Main{

    private LineChart chart = new LineChart(new NumberAxis(), new NumberAxis());
    private AnnuityCalculate annuityCalculate;
    private LinearCalculate linearCalculate;
    private double sum, percent;
    private int term;

    TextField sumField, yearsField, monthField, percentField, postpone;

    public GraphPanel(TextField sumField, TextField yearsField, TextField monthField, TextField percentField, TextField postpone) {
        this.sumField = sumField;
        this.yearsField = yearsField;
        this.monthField = monthField;
        this.percentField = percentField;
        this.postpone = postpone;
    }

    public void graph() {

        sum = Double.parseDouble(sumField.getText());
        term = Integer.parseInt(yearsField.getText()) * 12 + Integer.parseInt(monthField.getText());
        percent = Double.parseDouble(percentField.getText()) / 100;

        annuityCalculate = new AnnuityCalculate(sum, percent, term, Integer.parseInt(postpone.getText()));
        linearCalculate = new LinearCalculate(sum, percent, term, Integer.parseInt(postpone.getText()));

        createGraph();
        createPanel();
    }

    private void createGraph() {
        Axis month = new NumberAxis("Mėnuo", 0, term, 1);
        Axis perMonth = new NumberAxis("Suma", 0, annuityCalculate.getPerMonth() * 2, annuityCalculate.getPerMonth() / 5);

        ObservableList<XYChart.Series> seriesList = FXCollections.observableArrayList();
        ObservableList<XYChart.Data> annuityList = FXCollections.observableArrayList();
        ObservableList<XYChart.Data> linearList = FXCollections.observableArrayList();

        chart = new LineChart(month, perMonth, seriesList);
        chart.setPrefSize(1000, 500);

        seriesList.addAll(new XYChart.Series("Anuitetas", annuityList), new XYChart.Series("Linijinis", linearList));

        for(int i = 0; i < annuityCalculate.numbers.size(); i++) {
            annuityList.add(new XYChart.Data(i + 1, annuityCalculate.numbers.get(i).perMonth));
            linearList.add(new XYChart.Data(i + 1, linearCalculate.numbers.get(i).perMonth));
        }
    }

    private void createPanel() {
        Group root = new Group();
        Stage stage = new Stage();
        root.getChildren().add(chart);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Grafikas");
        stage.show();
    }
}
