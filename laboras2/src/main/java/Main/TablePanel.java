package Main;

import Skaiciavimai.AnnuityCalculate;
import Skaiciavimai.LinearCalculate;
import Skaiciavimai.Numbers;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TablePanel extends Main {
    TextArea area;
    Button button;
    TextField sumField, yearsField, monthsField, percentField, postpone, filterFrom, FilterTo;
    ChoiceBox choiceBox;
    StringBuilder info = new StringBuilder();

    double sum, yearlyPercent;
    int term, filter, filter1;

    AnnuityCalculate annuityCalculate;
    LinearCalculate linearCalculate;

    public TablePanel(TextField sumField, TextField yearsField, TextField monthsField, TextField percentField,
                      ChoiceBox choiceBox, TextField filterFrom, TextField filterTo, TextField postpone) {


        //super();
        this.sumField = sumField;
        this.yearsField = yearsField;
        this.monthsField = monthsField;
        this.percentField = percentField;
        this.choiceBox = choiceBox;
        this.postpone = postpone;
        this.filterFrom = filterFrom;
        this.filterTo = filterTo;

    }

    public void table() {
        button = createButton(540, 20, "Atsiųsti .txt dokumentą");
        button.setWrapText(true);

        createArea();
        createPanel();
    }

    private void createPanel() {
        Pane root = new Pane();
        Stage stage = new Stage();
        root.setPrefSize(700, 500);
        root.getChildren().addAll(area, button);
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Lentelė");
        stage.show();
    }

    private void createArea() {
        info.replace(0, info.length(), "");

        sum = Double.parseDouble(sumField.getText());
        term = Integer.parseInt(yearsField.getText()) * 12 + Integer.parseInt(monthsField.getText());
        yearlyPercent = Double.parseDouble(percentField.getText()) / 100;

        annuityCalculate = new AnnuityCalculate(sum, yearlyPercent, term, Integer.parseInt(postpone.getText()));
        linearCalculate = new LinearCalculate(sum, yearlyPercent, term, Integer.parseInt(postpone.getText()));

        if(filterFrom.getText() == "")
            filter = 1;
        else
            filter = Integer.parseInt(filterFrom.getText());

        if (choiceBox.getSelectionModel().getSelectedItem() == "Linijinis") {
            if (filterTo.getText() == "")
                filter1 = linearCalculate.numbers.size();
            else
                filter1 = Integer.parseInt(filterTo.getText());

            putInfo(linearCalculate.numbers, filter, filter1);

        }
        else if (choiceBox.getSelectionModel().getSelectedItem() == "Anuitetas") {
            if (filterTo.getText() == "")
                filter1 = annuityCalculate.numbers.size();
            else
                filter1 = Integer.parseInt(filterTo.getText());

            putInfo(annuityCalculate.numbers, filter, filter1);
        }

        area = new TextArea();
        area.setLayoutX(20);
        area.setLayoutY(20);
        area.setPrefSize(510, 400);
        area.setText(info.toString());

    }

    private void putInfo(ArrayList<Numbers> numbers, int from, int to) {
        for (int i = from; i <= to; i++) {
            info.append(i + ". Mėnesinė įmoka: " + String.format("%.2f", numbers.get(i - 1).perMonth) + ", Palūkanos: "
                                                 + String.format("%.2f", numbers.get(i - 1).interest) + ", Kreditas: "
                                                 + String.format("%.2f", numbers.get(i - 1).credit) + ", Paskolos likutis: "
                                                 + String.format("%.2f", numbers.get(i - 1).leftovers) + "\n");
        }
    }

    public Button createButton(double x, double y, String s) {
        Button button = super.createButton(x, y, s);
        button.setOnAction(event -> toFile());

        return button;
    }

    protected void toFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("ataskaita.txt"));
            writer.write(info.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
