package builder;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GodScreenController implements Initializable{
    private Main mainClass;

    God god;

    @FXML AnchorPane screenPane;
    @FXML Label lblGod;
    @FXML Button godListScreen;
    @FXML ImageView godImage;

    public void initialize(URL url, ResourceBundle rb) {
        mainClass = Main.getInstance();
        int count = 0;
        while(count < mainClass.getGodList().size()){
            if(mainClass.getGodList().get(count).getName().equals(mainClass.getGod())){
                String name = mainClass.getGodList().get(count).getName();
                String type = mainClass.getGodList().get(count).getType();
                String pantheon = mainClass.getGodList().get(count).getPantheon();
                String img = mainClass.getGodList().get(count).getURL();
                god = new God(name, type, pantheon, img);
                break;
            }
            count++;
        }

        godImage.setImage(new Image(god.getURL()));

        lblGod.setText(god.getName());
    }

    public void godListScreen() throws IOException{
        changeScreen();
    }

    private void changeScreen() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        screenPane.getChildren().setAll(pane);
    }
}
