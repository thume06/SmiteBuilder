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
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class ItemSelectController implements Initializable {
    private Main mainClass;
    private GodScreenController godScreen;

    private String damageType = "null";

    private ArrayList<Label> hoverLabels = new ArrayList<>(); //contains the labels when hovering an item
    private ArrayList<Item> tempArray = new ArrayList<>(); //will contain items after filtering
    private String[][] itemTable = new String[9][11]; //columns first, then rows 0,0 is top left. rows increase down. columns increase to the right.

    @FXML ScrollPane itemScroll;
    @FXML GridPane itemGrid;
    @FXML Label noneFound;
    @FXML ImageView itemHover;
    @FXML Label lblItemName;
    @FXML Label lblStat1;
    @FXML Label lblStat2;
    @FXML Label lblStat3;
    @FXML Label lblStat4;
    @FXML Label lblStat5;

    public void initialize(URL url, ResourceBundle rb) {
        mainClass = Main.getInstance();
        godScreen = GodScreenController.getInstance();
        damageType = godScreen.getDamageType();
        itemScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        itemScroll.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-background-color:transparent;");
        itemHover.setImage(new Image("itemHover.png"));
        itemHover.setDisable(true);
        itemHover.setOpacity(0);
        lblItemName.setOpacity(0);
        lblStat1.setOpacity(0);
        lblStat2.setOpacity(0);
        lblStat3.setOpacity(0);
        lblStat4.setOpacity(0);
        lblStat5.setOpacity(0);
        hoverLabels.add(lblStat1);
        hoverLabels.add(lblStat2);
        hoverLabels.add(lblStat3);
        hoverLabels.add(lblStat4);
        hoverLabels.add(lblStat5);
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

        //Loop filters through items that are already selected
        count = 0;
        while(count < tempArray.size()){
            int count2 = 0;
            while(count2 < godScreen.getBuild().size()){
                if(godScreen.getBuild().get(count2) == null){
                    count2++;
                    continue;
                }
                if(tempArray.get(count).getName().equals(godScreen.getBuild().get(count2).getName())){
                    tempArray.remove(count);
                    count--;
                }
                count2++;
            }
            count++;
        }

        //Loop filters through item restrictions
        count = 0;
        while(count < tempArray.size()){
            //Skips T3 items to make it faster
            if(tempArray.get(count).getTier() == 3){
                count++;
                continue;
            }
            ArrayList<String> restrictingItems = tempArray.get(count).getRestrictingItems();

            int count2 = 0;
            int restrictedCount = 0;
            while(count2 < godScreen.getBuild().size()){
                //Skips if the build slot is empty
                if(godScreen.getBuild().get(count2) == null){
                    count2++;
                    continue;
                }

                int count3 = 0;
                while(count3 < restrictingItems.size()){
                    if(godScreen.getBuild().get(count2).getName().equals(restrictingItems.get(count3))){
                        restrictedCount++;
                    }
                    count3++;
                }
                count2++;
            }
            if(restrictedCount == restrictingItems.size()){
                tempArray.remove(count);
                count--;
            }
            count++;
        }

        //Finally the items are sorted alphabetically
        Collections.sort(tempArray, new Comparator<Item>() {
            @Override
            public int compare(Item i1, Item i2) {
                String s1 = i1.getName();
                String s2 = i2.getName();
                return s1.compareToIgnoreCase(s2);
            }
        });
    }

    //Adds all items from the tempArray to the screen
    private void RefreshItems() {
        //Clears all grid spaces
        itemGrid.getChildren().clear();

        //makes the notification label for when no gods are found invisible
        noneFound.setOpacity(0);

        //Gets the number of rows required and adds the scroll bar if needed
        double rows = Math.ceil(tempArray.size() / 7.0);

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

        //Shows the None Found label and exits the method if no items are on the array
        if(tempArray.size() == 0){
            noneFound.setOpacity(1);
            return;
        }

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
                while (count < 7) {
                    itemTable[count][rowCount] = tempArray.get(count).getName();
                    count++;
                }
                count = 0;
                while (count < 7) {
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
            while (columnCount < 7) {
                if (itemTable[columnCount][rowCount] == null) {
                    toBreak = true;
                    break;
                }
                Label itemName = new Label();
                itemName.setText(itemTable[columnCount][rowCount]);
                itemName.setDisable(true);
                itemName.setOpacity(1);
                itemName.setStyle("-fx-font-size: 11;");
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
                int row = rowCount;

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
                        if(row == 0){
                            itemHover.setLayoutY(itemScroll.getLayoutY() + (row * 100) + 75);
                        }
                        else{
                            itemHover.setLayoutY(itemScroll.getLayoutY() + (row * 100) - 125);
                        }
                        if(column == 6){
                            itemHover.setLayoutX(column * 100 -75);
                        }
                        else{
                            itemHover.setLayoutX(column * 100 + 75);
                        }
                        itemHover.setOpacity(1);

                        //This section sets all hover labels to the right text
                        Item itemObj = mainClass.getItem(itemTable[column][row]);
                        ArrayList<String> statsUsed = itemObj.getStatsUsed();
                        int numStats = statsUsed.size();
                        lblItemName.setText(item);
                        int count = 0;
                        while(count < numStats){
                            hoverLabels.get(count).setText(itemObj.getStat(count));
                            count++;
                        }

                        lblItemName.setLayoutX(itemHover.getLayoutX() + 5);
                        lblItemName.setLayoutY(itemHover.getLayoutY() + 5);
                        lblItemName.setOpacity(1);
                        count = 0;
                        while(count < numStats){
                            hoverLabels.get(count).setLayoutX(itemHover.getLayoutX() + 5);
                            hoverLabels.get(count).setLayoutY(itemHover.getLayoutY() + 30 + (count * 20));
                            hoverLabels.get(count).setOpacity(1);
                            count++;
                        }
                    }
                });
                itemImage.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        itemImage.setFitWidth(67);
                        itemImage.setFitHeight(67);
                        hover.setVisible(false);
                        itemName.setOpacity(1);
                        itemHover.setOpacity(0);
                        lblItemName.setOpacity(0);
                        lblStat1.setOpacity(0);
                        lblStat2.setOpacity(0);
                        lblStat3.setOpacity(0);
                        lblStat4.setOpacity(0);
                        lblStat5.setOpacity(0);
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
        godScreen.itemSelected(n);
        Stage stage = (Stage) itemScroll.getScene().getWindow();
        stage.close();
    }
}
