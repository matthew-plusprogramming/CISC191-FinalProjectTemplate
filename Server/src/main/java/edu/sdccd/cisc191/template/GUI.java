package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {

   static String[][] array = { { "1", "2", "3" }, { "4", "5", "6" }, { "7", "8", "9" } };
   HBox headerRow;
   VBox allRowsContainer;
   Button selectedButton;

   void redraw() {
      HBox headerCopy = headerRow;
      allRowsContainer.getChildren().clear();
      allRowsContainer.getChildren().add(headerCopy);

      for (int i = 0; i < array.length; i++) {
         HBox rowContainer = new HBox(10);
         for (int j = 0; j < array[0].length; j++) {
            Button button = new Button(array[i][j]);
            button.setPrefHeight(50);
            button.setPrefWidth(50);
            button.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
                  selectedButton = (Button) event.getSource();
               }
            });

            rowContainer.getChildren().add(button);
         }
         allRowsContainer.getChildren().add(rowContainer);
      }
   }

   @Override
   public void start(Stage stage) throws Exception {
      Label statusText = new Label("");
      Button confirmButton = new Button("Confirm");
      TextField inputText = new TextField();
      headerRow = new HBox(10);
      Button exitButton = new Button("Exit");
      exitButton.setOnAction(value -> {
         System.exit(0);
      });
      Button getAtIndexButton = new Button("Get At Index");
      getAtIndexButton.setOnAction(value -> {
         statusText.setText(selectedButton.getText());
      });
      Button setAtIndexButton = new Button("Set At Index");
      setAtIndexButton.setOnAction(value -> {
         statusText.setText("New value:");
         confirmButton.setOpacity(1);
         inputText.setOpacity(1);
      });
      Button findIndexOfButton = new Button("Find Index Of");
      findIndexOfButton.setOnAction(value -> {
         // Find selected button index
         int row = 0;
         int column = 0;
         for (int i = 0; i < allRowsContainer.getChildren().size(); i++) {
            HBox rowContainer = (HBox) allRowsContainer.getChildren().get(i);
            for (int j = 0; j < rowContainer.getChildren().size(); j++) {
               if (rowContainer.getChildren().get(j) == selectedButton) {
                  row = i;
                  column = j + 1;
               }
            }
         }
         statusText.setText("Row: " + row + " Column: " + column);
      });
      Button deleteAtIndexButton = new Button("Delete At Index");
      deleteAtIndexButton.setOnAction(value -> {
         if (selectedButton != null) {
            statusText.setText("Deleted: " + selectedButton.getText());
            // find selected button index
            int row = 0;
            int column = 0;
            for (int i = 0; i < allRowsContainer.getChildren().size(); i++) {
               HBox rowContainer = (HBox) allRowsContainer.getChildren().get(i);
               for (int j = 0; j < rowContainer.getChildren().size(); j++) {
                  if (rowContainer.getChildren().get(j) == selectedButton) {
                     row = i - 1;
                     column = j;
                  }
               }
            }
            array[row][column] = "";
            redraw();
         }
      });
      Button expandButton = new Button("Expand");
      expandButton.setOnAction(value -> {
         // expand the array by one row and one column
         String[][] newArray = new String[array.length + 1][array[0].length + 1];
         for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
               newArray[i][j] = array[i][j];
            }
         }
         array = newArray;
         redraw();
      });
      Button shrinkButton = new Button("Shrink");
      shrinkButton.setOnAction(value -> {
         if (array.length > 1) {
            // shrink the array by one row and one column
            String[][] newArray = new String[array.length - 1][array[0].length - 1];
            for (int i = 0; i < array.length - 1; i++) {
               for (int j = 0; j < array[0].length - 1; j++) {
                  newArray[i][j] = array[i][j];
               }
            }
            array = newArray;
            redraw();
         }
      });
      confirmButton.setOnAction(value -> {
         if (selectedButton != null) {
            statusText.setText("");
            inputText.setOpacity(0);
            confirmButton.setOpacity(0);
            // find selected button index
            int row = 0;
            int column = 0;
            for (int i = 0; i < allRowsContainer.getChildren().size(); i++) {
               HBox rowContainer = (HBox) allRowsContainer.getChildren().get(i);
               for (int j = 0; j < rowContainer.getChildren().size(); j++) {
                  if (rowContainer.getChildren().get(j) == selectedButton) {
                     row = i - 1;
                     column = j;
                  }
               }
            }
            // set new value
            array[row][column] = inputText.getText();
            redraw();
         }
      });
      headerRow.getChildren().add(exitButton);
      headerRow.getChildren().add(getAtIndexButton);
      headerRow.getChildren().add(setAtIndexButton);
      headerRow.getChildren().add(findIndexOfButton);
      headerRow.getChildren().add(deleteAtIndexButton);
      headerRow.getChildren().add(expandButton);
      headerRow.getChildren().add(shrinkButton);
      headerRow.getChildren().add(statusText);
      headerRow.getChildren().add(inputText);
      headerRow.getChildren().add(confirmButton);
      inputText.setOpacity(0);
      confirmButton.setOpacity(0);
      allRowsContainer = new VBox(10);
      redraw();

      Scene scene = new Scene(allRowsContainer);
      stage.setScene(scene);
      stage.setTitle("June Architect");
      stage.show();
   }

   public static void main(String[] args) {
      Application.launch(args);
   }
}
