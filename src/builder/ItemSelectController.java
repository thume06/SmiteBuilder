package builder;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemSelectController implements Initializable {
    private Main mainClass;
    private GodScreenController godScreen;

    private String damageType = "null";

    private ArrayList<Item> tempArray = new ArrayList<>(); //will contain items after filtering
    private String[][] itemTable = new String[9][11]; //columns first, then rows 0,0 is top left. rows increase down. columns increase to the right.

    @FXML ScrollPane itemScroll;
    @FXML GridPane itemGrid;
    @FXML Label noneFound;

    public void initialize(URL url, ResourceBundle rb) {
        mainClass = Main.getInstance();
        godScreen = GodScreenController.getInstance();
        damageType = godScreen.getDamageType();
        itemScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        itemScroll.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-background-color:transparent;");
        InitializeItems();
        RefreshItems();
    }

    //Adds all items to the temp ArrayList to start
    private void InitializeItems(){
        tempArray = (ArrayList<Item>)mainClass.getItemList().clone();
        //Looop filters through damage types
        int count = 0;
        while(count < tempArray.size()){
            if(damageType.equals("M")){
                if(tempArray.get(count).getDamageType().equals("P")){
                    tempArray.remove(count);
                    count--;
                }
            }
            else{
                if(tempArray.get(count).getDamageType().equals("M")){
                    tempArray.remove(count);
                    count--;
                }
            }
            count++;
        }
    }

    private void RefreshItems() {
        //Clears all grid spaces
        itemGrid.getChildren().clear();

        //makes the notification label for when no gods are found invisible
        noneFound.setOpacity(0);

        //Gets the number of rows required and adds the scroll bar if needed
        double rows = Math.ceil(tempArray.size() / 9.0);

        //Adds/removes scroll bar when necessary
        if (rows > 5) {
            itemScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        } else {
            itemScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        }

        //Reduces the size of the grid so that scrolling is not longer than the list of gods
        double height = (rows * 100) + 8;
        itemGrid.setMaxHeight(height);
        itemGrid.setMinHeight(height);
        itemGrid.setPrefHeight(height);

        int rowCount = 0;
        while (rowCount < rows) {
            //if on the last row, loop through the rest of the temp array.
            if (rowCount + 1 == rows) {
                int count = 0;
                while (count < tempArray.size()) {
                    itemTable[count][rowCount] = tempArray.get(count).getName();
                    count++;
                }
            }
            //if not on the last row, loop through one row then remove it from the temp array.
            else {
                int count = 0;
                while (count < 9) {
                    itemTable[count][rowCount] = tempArray.get(count).getName();
                    count++;
                }
                count = 0;
                while (count < 9) {
                    tempArray.remove(0);
                    count++;
                }
            }
            rowCount++;
        }

        //now loops through the table of image names and adds all images with listeners to the grid pane
        rowCount = 0;
        while (rowCount < rows) {
            boolean toBreak = false;
            int columnCount = 0;
            while (columnCount < 9) {
                if (itemTable[columnCount][rowCount] == null) {
                    toBreak = true;
                    break;
                }
                Label itemName = new Label();
                itemName.setText(itemTable[columnCount][rowCount]);
                itemName.setDisable(true);
                itemName.setOpacity(1);
                ImageView hover = new ImageView(new Image("hover.png"));
                hover.setFitWidth(87);
                hover.setFitHeight(87);
                hover.setDisable(true);
                hover.setVisible(false);
                ImageView itemImage = new ImageView(new Image(itemTable[columnCount][rowCount] + ".png"));
                String item = itemTable[columnCount][rowCount];
                GridPane.setHalignment(itemImage, HPos.CENTER);
                GridPane.setValignment(itemImage, VPos.CENTER);
                GridPane.setHalignment(hover, HPos.CENTER);
                GridPane.setValignment(hover, VPos.CENTER);
                GridPane.setHalignment(itemName, HPos.CENTER);
                GridPane.setValignment(itemName, VPos.BOTTOM);
                itemImage.setFitWidth(67);
                itemImage.setFitHeight(67);
                int column = columnCount;
                itemImage.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        ItemSelected(item);
                    }
                });
                itemImage.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        itemImage.setFitWidth(87);
                        itemImage.setFitHeight(87);
                        hover.setVisible(true);
                        itemName.setOpacity(0);
                    }
                });
                itemImage.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        itemImage.setFitWidth(67);
                        itemImage.setFitHeight(67);
                        hover.setVisible(false);
                        itemName.setOpacity(1);
                    }
                });
                itemGrid.add(itemImage, columnCount, rowCount);
                itemGrid.add(hover, columnCount, rowCount);
                itemGrid.add(itemName, columnCount, rowCount);
                columnCount++;
            }
            if (toBreak) {
                break;
            }
            rowCount++;
        }
    }

    private void ItemSelected(String n){
        String itemName = n;
        godScreen.itemSelected(n);
        Stage stage = (Stage) itemScroll.getScene().getWindow();
        stage.close();
    }

}
