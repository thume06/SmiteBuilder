package builder;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GodScreenController implements Initializable{
    private Main mainClass;
    private static GodScreenController instance;
    public GodScreenController() {
        instance = this;
    }
    public static GodScreenController getInstance() {
        return instance;
    }

    God god;

    private ArrayList<Item> build = new ArrayList<>();

    private int selectedColumn = 99; //99 is default value if nothing is selected

    private double health;
    private double HP5;
    private double mana;
    private double MP5;
    private double physDef;
    private double magDef;
    private double physMult;
    private double physMit;
    private double magMult;
    private double magMit;
    private double power = 0;
    private int level = 1;

    @FXML AnchorPane screenPane;
    @FXML GridPane itemGrid;
    @FXML Label lblGod;
    @FXML Label lblDamage;
    @FXML Button godListScreen;
    @FXML ImageView godImage;
    @FXML TextField txtHealth;
    @FXML TextField txtPhysDef;
    @FXML TextField txtPhysHealth;
    @FXML TextField txtMagDef;
    @FXML TextField txtMagHealth;
    @FXML TextField txtHP5;
    @FXML TextField txtMana;
    @FXML TextField txtMP5;
    @FXML TextField txtPower;
    @FXML TextField txtFlatPen;
    @FXML TextField txtPercPen;
    @FXML TextField txtMovement;
    @FXML TextField txtLevel;
    @FXML Button btnIncreaseLevel;
    @FXML Button btnDecreaseLevel;

    public void initialize(URL url, ResourceBundle rb) {
        mainClass = Main.getInstance();
        int count = 0;
        while(count < mainClass.getGodList().size()){
            if(mainClass.getGodList().get(count).getName().equals(mainClass.getGod())){
                god = mainClass.getGodList().get(count);
                break;
            }
            count++;
        }
        txtLevel.setText("1");
        txtHealth.setText(round(god.getHealth()));
        health = god.getHealth();
        txtPhysDef.setText(round(god.getBasePhysicalDef()));
        physDef = god.getBasePhysicalDef();
        physMult = (100.0/(physDef + 100));
        physMit = 1-physMult;
        txtPhysHealth.setText(round(health * physMit + health));
        txtMagDef.setText(round(god.getBaseMagicalDef()));
        magDef = (god.getBaseMagicalDef());
        magMult = (100.0/(magDef + 100));
        magMit = 1 - magMult;
        txtMagHealth.setText(round(health * magMit + health));
        txtHP5.setText(round(god.getHP5()));
        txtMana.setText(round(god.getMana()));
        txtMP5.setText(round(god.getMP5()));
        txtPower.setText(round(god.getPower()));
        txtFlatPen.setText("0");
        txtPercPen.setText("0");
        txtMovement.setText(String.valueOf(god.getMS()));

        godImage.setImage(new Image(god.getURL()));

        lblGod.setText(god.getName());
        if(god.getDamageType().equals("P")){
            lblDamage.setText("Phys Power:");
        }
        else{
            lblDamage.setText("Mag Power:");
        }
        count = 0;
        while(count < 6){
            build.add(null);
            count++;
        }
        InitializeItems();
    }

    private void InitializeItems(){
        int count = 0;
        while(count < 6){
            ImageView emptySlot = new ImageView(new Image("hover.png"));
            emptySlot.setOpacity(0);
            emptySlot.setFitWidth(100);
            emptySlot.setFitHeight(100);
            emptySlot.setPickOnBounds(true);
            emptySlot.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    emptySlot.setOpacity(1);
                }
            });
            emptySlot.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    emptySlot.setOpacity(0);
                }
            });
            emptySlot.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        itemSlotPressed(emptySlot.getLayoutX() / 100);
                    }
                    catch(IOException e){
                        System.out.println("Failed to load item select screen");
                        e.printStackTrace();
                    }
                }
            });
            itemGrid.add(emptySlot, count, 0);
            count++;
        }
    }

    public void godListScreen() throws  IOException{
        changeScreen();
    }

    private void changeScreen() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        screenPane.getChildren().setAll(pane);
    }

    private void itemSlotPressed(double c) throws IOException{
        String column = String.valueOf(c);
        column = column.substring(0, 1);
        selectedColumn = Integer.valueOf(column);
        Parent root = FXMLLoader.load(getClass().getResource("ItemSelect.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.setTitle("Item Select");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private String round(double v){
        DecimalFormat df = new DecimalFormat("0.00");
        String rounded = String.valueOf(df.format(v));
        int index = rounded.indexOf(".");
        if(String.valueOf(rounded.charAt(index + 1)).equals("0") && String.valueOf(rounded.charAt(index + 2)).equals("0")){
            rounded = rounded.substring(0, index);
        }

        return rounded;
    }

    public void itemSelected(String n) {
        int columnSave = selectedColumn;
        build.set(columnSave, mainClass.getItem(n));
        ImageView item = new ImageView(new Image(n + ".png"));
        ImageView hover = new ImageView(new Image("hover.png"));
        item.setFitWidth(100);
        item.setFitHeight(100);
        hover.setFitWidth(100);
        hover.setFitHeight(100);
        hover.setDisable(true);
        hover.setOpacity(0);
        item.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    itemSlotPressed(columnSave);
                } catch (IOException e) {
                    System.out.println("Failed to load item select screen");
                }
            }
        });
        item.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                hover.setOpacity(1);
            }
        });
        item.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                hover.setOpacity(0);
            }
        });
        itemGrid.add(item, selectedColumn, 0);
        itemGrid.add(hover, selectedColumn, 0);
        selectedColumn = 99;
        updateStats();
    }

    //Updates god stats. Calls other methods for updating the text fields and applying effects
    private void updateStats(){
        //sets stats to base stats plus level scaling
        power = 0;
        health = god.getHealth() + ((level - 1) * god.getHealthScale());
        HP5 = god.getHP5() + ((level - 1) * god.getHP5Scale());
        physDef = god.getBasePhysicalDef() + ((level - 1) * god.getPhysicalDefScale());
        magDef = god.getBaseMagicalDef() + ((level - 1) * god.getMagicalDefScale());
        mana = god.getMana() + ((level - 1) * god.getManaScale());
        MP5 = god.getMP5() + ((level - 1) * god.getMP5Scale());

        //loop through the build
        int count = 0;
        while(count < 6){
            //skips empty slots
            if(build.get(count) == null){
                count++;
                continue;
            }

            //if the item adds health
            if(build.get(count).usesStat("health")){
                health = health + build.get(count).getHealth();
            }

            //if the item adds mana
            if(build.get(count).usesStat("mana")){
                mana = mana + build.get(count).getMana();
            }

            //if the item adds physical defense
            if(build.get(count).usesStat("physicalDef")){
                physDef = physDef + build.get(count).getPhysicalDef();
            }

            //if the item adds magical defense
            if(build.get(count).usesStat("magicalDef")){
                magDef = magDef + build.get(count).getMagicalDef();
            }

            //if the item adds magical power
            if(build.get(count).usesStat("magicalPower")){
                power = power + build.get(count).getMagicalPower();
            }

            //if the item adds physical power
            if (build.get(count).usesStat("physicalPower")){
                power = power + build.get(count).getPhysicalPower();
            }

            //if the item adds HP5
            if(build.get(count).usesStat("hp5")){
                HP5 = HP5 + build.get(count).getHP5();
            }

            //if the item adds MP5
            if(build.get(count).usesStat("mp5")){
                MP5 = MP5 + build.get(count).getMP5();
            }
            count++;
        }
        updateTextFields();
    }

    private void updateTextFields(){
        txtHealth.setText(round(health));
        txtPhysDef.setText(round(physDef));
        physMult = (100.0/(physDef + 100));
        physMit = 1-physMult;
        txtPhysHealth.setText(round(health * physMit + health));
        txtMagDef.setText((round(magDef)));
        magMult = (100.0/(magDef + 100));
        magMit = 1 - magMult;
        txtMagHealth.setText(round(health * magMit + health));
        txtHP5.setText(round(HP5));
        txtMana.setText(round(mana));
        txtMP5.setText(round(MP5));
        txtPower.setText(round(power));
    }

    public String getDamageType(){
        return god.getDamageType();
    }

    public ArrayList<Item> getBuild(){
        return build;
    }

    @FXML public void increasePressed(){
        if(level < 20) {
            level++;
            txtLevel.setText(String.valueOf(level));
            updateStats();
        }
    }

    @FXML public void decreasePressed(){
        if(level > 1){
            level--;
            txtLevel.setText(String.valueOf(level));
            updateStats();
        }
    }
}