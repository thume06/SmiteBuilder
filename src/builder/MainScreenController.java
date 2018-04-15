package builder;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable, ControlledScreen {
    //TODO ALL items and gods must be declared on this scope zzzz. Figure out a way to organize and minimize all of these declarations.
    Item BookOfThoth = new Item("Book of Thoth");
    Item Transcendance = new Item("Transcendance");
    God Ra = new God("Ra", "M");

    private ScreensController myController;
    private Main mainClass;

    @FXML ImageView hoverImg;
    @FXML ImageView raImg;
    @FXML ImageView godImgLarge;
    @FXML Label selectDialog;
    @FXML Label lblPower;
    @FXML Label lblGodName;

    public void initialize(URL url, ResourceBundle rb) {
        InitializeItems();
        InitializeGods();
        InitializeImages();
    }

    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    public void InitializeItems(){
        //TODO Book of Thoth and Trans are fine for now, lets get other functionality working before adding more items.
        //-----------------------------------------T3 Items------------------------------------------//
        BookOfThoth.setDamageType("M");
        BookOfThoth.setMagicalPower(100);
        BookOfThoth.setMana(125);
        BookOfThoth.setMp5(15);
        BookOfThoth.setMaxStacks(75);
        BookOfThoth.setStacks(75);
        BookOfThoth.setStackMana(10);

        Transcendance.setDamageType("P");
        Transcendance.setPhysicalPower(35);
        Transcendance.setMana(300);
        Transcendance.setMp5(6);
        Transcendance.setCDR(10);
        Transcendance.setMaxStacks(50);
        Transcendance.setStacks(50);
    }

    public void InitializeGods(){
        Ra.setBaseHealth(385);
        Ra.setHealthScale(68);
        Ra.setBaseMana(255);
        Ra.setManaScale(48);
        Ra.setBaseMS(360);
        Ra.setBasicAttackDamage(34);
        Ra.setBasicAttackScale(1.5);
        Ra.setAttackSpeed(0.88);
        Ra.setAttackSpeedScale(.01);
        Ra.setBasePhysicalDef(10);
        Ra.setPhysicalDefScale(2.5);
        Ra.setBaseMagicalDef(30);
        Ra.setMagicalDefScale(0.9);
    }

    public void InitializeImages(){
        hoverImg.setImage(new Image("hover.png"));
        hoverImg.setVisible(false);
        hoverImg.setDisable(true);

        raImg.setImage(new Image("ra.jpg"));

        //TODO REMOVE BELOW LINES ONCE DONE DESIGNING SCREEN AFTER SELECTING GOD. DOING THIS FOR EACH NEW GOD ADDED WOULD BE A LOT OF WORK
        raImg.setDisable(false);
        raImg.setVisible(true);
        selectDialog.setDisable(false);
        selectDialog.setVisible(true);
        lblPower.setVisible(false);
    }

    public void HideImages(){
        raImg.setVisible(false);
        raImg.setDisable(true);
        selectDialog.setVisible(false);
        selectDialog.setDisable(true);
    }

    public void GodSelected(God g, String img){
        godImgLarge.setImage(new Image(img));
        lblGodName.setText(g.getName());
        HideImages();
        lblPower.setVisible(true);

        if(g.getDamageType().equals("P")){
            lblPower.setText("Physical power: " + g.getPower());
        }
        else{
            lblPower.setText("Magical power: " + g.getPower());
        }
    }

    public void HoverExited(){
        hoverImg.setVisible(false);
    }

    //GOD SELECTIONS
    public void RaSelect(){
        GodSelected(Ra, "raLarge.jpg");
    }

    //GOD HOVERS
    public void RaHovered(){
        hoverImg.setLayoutX(raImg.getLayoutX());
        hoverImg.setLayoutY(raImg.getLayoutY());
        hoverImg.setVisible(true);
    }
}
