// Vytautas Krupavičius 4gr. 1pogr.

package Main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.*;
import javafx.stage.Stage;

public class Main extends Application {

    Label loanSum, loanTime, years, months, percent, postponeLabel, filterFromLabel, filterToLabel;
    Button table, graph;
    TextField sumField, yearsField, monthsField, percentField, filterFrom, filterTo, postpone;
    ChoiceBox choiceBox;

    TablePanel tablePanel;
    GraphPanel graphPanel;

    public static void  main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
            setupMenu();
    }

    private void setupMenu() {


        loanSum = createLabel(50, 58, "Paskolos suma: ");
        loanTime = createLabel(50, 124, "Paskolos laikotarpis:");
        years = createLabel(50, 150, "Metai:");
        months = createLabel(131, 150, "Mėnesiai:");
        percent = createLabel(50, 210, "Metinė palūkanų norma:");
        postponeLabel = createLabel(338, 138, "Atidėjimas (mėn.):");
        filterFromLabel = createLabel(338, 206, "Filtras nuo kada:");
        filterToLabel = createLabel(338, 270, "Filtras iki kada:");



        sumField = createTextField(50, 76, 160, 30, "0");
        yearsField = createTextField(50, 170, 78, 30, "0");
        monthsField = createTextField(131, 170, 78, 30, "0");
        percentField = createTextField(50, 230, 160, 30, "0");
        filterFrom = createTextField(338, 223, 160, 30, "");
        filterTo = createTextField(338, 287, 160, 30, "");
        postpone = createTextField(338, 155, 160, 30, "0");

        choiceBox = createChoiceBox(343, 80);

        tablePanel = new TablePanel(sumField, yearsField, monthsField, percentField, choiceBox, filterFrom, filterTo, postpone);
        graphPanel = new GraphPanel(sumField, yearsField, monthsField, percentField, postpone);

        table = createButton(50, 289, "Lentelė");
        EventHandler<ActionEvent> tableEvent = event -> tablePanel.table();
        table.setOnAction(tableEvent);

        graph = createButton(164, 289, "Grafikas");
        EventHandler<ActionEvent> graphEvent = event -> graphPanel.graph();
        graph.setOnAction(graphEvent);

        Group root = new Group();

        Stage mainStage = new Stage();
        mainStage.setHeight(500);
        mainStage.setWidth(650);
        mainStage.setTitle("Būsto paskolos skaičiuoklė/antras laboras");

        root.getChildren().addAll(loanSum, loanTime, years, months, percent,
                                postponeLabel, filterFromLabel, filterToLabel);
        root.getChildren().addAll(table, graph);
        root.getChildren().addAll(sumField, yearsField, monthsField, postpone,
                                 percentField, filterFrom, filterTo, choiceBox);


        Scene mainScene = new Scene(root);
        Color c = Color.web("#FBF2EA");
        mainScene.setFill(c);
        mainStage.setScene(mainScene);
        mainStage.show();

    }

    public Button createButton(double x, double y, String s) {
        Button button = new Button(s);

        button.setLayoutX(x);
        button.setLayoutY(y);

        return button;
    }

    public ChoiceBox createChoiceBox(int x, int y) {
        ChoiceBox box = new ChoiceBox();

        box.setLayoutX(x);
        box.setLayoutY(y);
        box.getItems().addAll("Anuitetas", "Linijinis");
        box.getSelectionModel().select(0);

        return box;
    }

    public TextField createTextField(double x, double y, double width, double height, String s) {
        TextField field = new TextField();

        field.setLayoutX(x);
        field.setLayoutY(y);
        field.setPrefSize(width, height);
        field.setText(s);

        return field;
    }

    private Label createLabel(double x, double y, String s) {
        Label label = new Label(s);
        label.setLayoutX(x);
        label.setLayoutY(y);

        return label;
    }
}
