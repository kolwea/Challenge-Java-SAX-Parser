/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxxmlparser;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;

/**
 *
 * @author Kolbe
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private TextArea textarea;

    @FXML
    private void handleOpenFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(textarea.getScene().getWindow());
        if (file != null) {
            try {
                TreeNode root = XMLParser.load(file);
                this.printTreeNode(root);
                System.out.println(root.getChildren().size());
            } catch (Exception ex) {
                displayExceptionAlert("Exception parsing XML file.", ex);
            }
        }
    }

    private void printTreeNode(TreeNode curr) {
        textarea.appendText("-----------------------------------\n");
        if (curr != null) {
            textarea.appendText("Key: " + curr.getKey() + (curr.getAttributes() == null ? " " : " Attributes: " + printAttributes(curr)) + (curr.getValue() == null ? "  " : " Value: " + curr.getValue()) + "\n");
            if (curr.getChildren() != null) {
                for (TreeNode child : curr.getChildren()) {
                    this.printTreeNode(child);
                }
            }
//            textarea.appendText("-----------------------------------\n");

        }
    }

    private String printAttributes(TreeNode curr) {
        ArrayList<String> attr = curr.getAttributes();
        String help = " ";
        for (int i = 0; i < attr.size(); i++) {
            help += attr.get(i) + " ";
        }
        return help;
    }

    private void displayExceptionAlert(String message, Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Exception!");
        alert.setContentText(message);

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
