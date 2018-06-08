package builder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.lang.String;
import java.util.ArrayList;


public class Main extends Application {
    private ArrayList<God> godArrayList = new ArrayList<>(); //contains all gods
    private ArrayList<Item> itemArrayList = new ArrayList<>(); //contains all items

    String selectedGod = "";

    //God Initializations
    God Ra = new God("Ra", "Mage", "Egyptian", "ra.png");
    God Agni = new God("Agni","Mage", "Egyptian", "agni.png");
    God Achilles = new God("Achilles", "Warrior", "Greek", "achilles.png");
    God Aphrodite = new God("Aphrodite", "Mage", "Greek", "aphrodite.png");
    God AMC = new God("Ah Muzen Cab", "Hunter", "Mayan", "amc.png");
    God AhPuch = new God("Ah Puch", "Mage", "Mayan", "ahpuch.png");
    God Amaterasu = new God("Amaterasu", "Warrior", "Japanese", "amaterasu.png");
    God Anhur = new God("Anhur", "Hunter", "Egyptian", "anhur.png");
    God Anubis = new God("Anubis", "Mage", "Egyptian", "anubis.png");
    God AoKuang = new God("Ao Kuang", "Mage", "Chinese", "aokuang.png");
    God Apollo = new God("Apollo", "Hunter", "Roman", "apollo.png");
    God Arachne = new God("Arachne", "Assassin", "Greek", "arachne.png");
    God Ares = new God("Ares", "Guardian", "Greek", "ares.png");
    God Artemis = new God("Artemis", "Hunter", "Greek", "artemis.png");
    God Artio = new God("Artio", "Guardian", "Celtic", "artio.png");
    God Athena = new God("Athena", "Guardian", "Greek", "athena.png");
    God Awilix = new God("Awilix", "Assassin", "Mayan", "awilix.png");
    God Bacchus = new God("Bacchus", "Guardian", "Roman", "bacchus.png");
    God Bakasura = new God("Bakasura", "Assassin", "Hindu", "bakasura.png");
    God Bastet = new God("Bastet", "Assassin", "Egyptian", "bastet.png");
    God Bellona = new God("Bellona", "Warrior", "Roman", "bellona.png");
    God Cabrakan = new God("Cabrakan", "Guardian", "Mayan", "cabrakan.png");
    God Camazotz = new God("Camazotz", "Assassin", "Mayan", "camazotz.png");
    God Cerberus = new God("Cerberus", "Guardian", "Greek", "cerberus.png");
    God Cernunnos = new God("Cernunnos", "Hunter", "Celtic", "cernunnos.png");
    God Chaac = new God("Chaac", "Warrior", "Mayan", "chaac.png");
    God Change = new God("Chang'e", "Mage", "Chinese", "change.png");
    God Chiron = new God("Chiron", "Hunter", "Greek", "chiron.png");
    God Chronos = new God("Chronos", "Mage", "Greek", "chronos.png");
    God CuChulainn = new God("Cu Chulainn", "Warrior", "Celtic", "cuchulainn.png");
    God Cupid = new God("Cupid", "Hunter", "Roman", "cupid.png");
    God DaJi = new God("Da Ji", "Assassin", "Chinese", "daji.png");
    God ErlangShen = new God("Erlang Shen", "Warrior", "Chinese", "erlangshen.png");
    God Discordia = new God("Discordia", "Mage", "Roman", "discordia.png");
    God Chernobog = new God("Chernobog", "Hunter", "Slavic", "chernobog.png");
    God Fafnir = new God("Fafnir", "Guardian", "Norse", "fafnir.png");
    God Fenrir = new God("Fenrir", "Assassin", "Norse", "fenrir.png");
    God Freya = new God("Freya", "Mage", "Norse", "freya.png");
    God Ganesha = new God("Ganesha", "Guardian", "Hindu", "ganesha.png");
    God Geb = new God("Geb", "Guardian", "Egyptian", "geb.png");
    God GuanYu = new God("Guan Yu", "Warrior", "Chinese", "guanyu.png");
    God Hachiman = new God("Hachiman", "Hunter", "Japanese", "hachiman.png");
    God Hades = new God("Hades", "Mage", "Greek", "hades.png");
    God HeBo = new God("He Bo", "Mage", "Chinese", "hebo.png");
    God Hel = new God("Hel", "Mage", "Norse", "hel.png");
    God Hercules = new God("Hercules", "Warrior", "Roman", "hercules.png");
    God HouYi = new God("Hou Yi", "Hunter", "Chinese", "houyi.png");
    God HunBatz = new God("Hun Batz", "Assassin", "Mayan", "hunbatz.png");
    God Isis = new God("Isis", "Mage", "Egyptian", "isis.png");
    God Izanami = new God("Izanami", "Hunter", "Japanese", "izanami.png");
    God Janus = new God("Janus", "Mage", "Roman", "janus.png");
    God JingWei = new God("Jing Wei", "Hunter", "Chinese", "jingwei.png");
    God Kali = new God("Kali", "Assassin", "Hindu", "kali.png");
    God Khepri = new God("Khepri", "Guardian", "Egyptian", "khepri.png");
    God Kukulkan = new God("Kukulkan", "Mage", "Mayan", "kukulkan.png");
    God Kumbhakarna = new God("Kumbhakarna", "Guardian", "Hindu", "kumbhakarna.png");
    God Kuzenbo = new God("Kuzenbo", "Guardian", "Japanese", "kuzenbo.png");
    God Loki = new God("Loki", "Assassin", "Norse", "loki.png");
    God Medusa = new God("Medusa", "Hunter", "Greek", "medusa.png");
    God Mercury = new God("Mercury", "Assassin", "Roman", "mercury.png");
    God Morrigan = new God("The Morrigan", "Mage", "Celtic", "morrigan.png");
    God Neith = new God("Neith", "Hunter", "Egyptian", "neith.png");
    God Nemesis = new God("Nemesis", "Assassin", "Greek", "nemesis.png");
    God NeZha = new God("Ne Zha", "Assassin", "Chinese", "nezha.png");
    God Nike = new God("Nike", "Warrior", "Greek", "nike.png");
    God Nox = new God("Nox", "Mage", "Roman", "nox.png");
    God NuWa = new God("Nu Wa", "Mage", "Chinese", "nuwa.png");
    God Odin = new God("Odin", "Warrior", "Norse", "odin.png");
    God Osiris = new God("Osiris", "Warrior", "Egyptian", "osiris.png");
    God Poseidon = new God("Poseidon", "Mage", "Greek", "poseidon.png");
    God Raijin = new God("Raijin", "Mage", "Japanese", "raijin.png");
    God Rama = new God("Rama", "Hunter", "Hindu", "rama.png");
    God Ratatoskr = new God("Ratatoskr", "Assassin", "Norse", "ratatoskr.png");
    God Ravana = new God("Ravana", "Warrior", "Hindu", "ravana.png");
    God Scylla = new God("Scylla", "Mage", "Greek", "scylla.png");
    God Serqet = new God("Serqet", "Assassin", "Egyptian", "serqet.png");
    God Skadi = new God("Skadi", "Hunter", "Norse", "skadi.png");
    God Sobek = new God("Sobek", "Guardian", "Egyptian", "sobek.png");
    God Sol = new God("Sol", "Mage", "Norse", "sol.png");
    God SunWukong = new God("Sun Wukong", "Warrior", "Chinese", "sunwukong.png");
    God Susano = new God("Susano", "Assassin", "Japanese", "susano.png");
    God Sylvanus = new God("Sylvanus", "Guardian", "Roman", "sylvanus.png");
    God Terra = new God("Terra", "Guardian", "Roman", "terra.png");
    God Thanatos = new God("Thanatos", "Assassin", "Greek", "thanatos.png");
    God Thor = new God("Thor", "Assassin", "Norse", "thor.png");
    God Thoth = new God("Thoth", "Mage", "Egyptian", "thoth.png");
    God Tyr = new God("Tyr", "Warrior", "Norse", "tyr.png");
    God Ullr = new God("Ullr", "Hunter", "Norse", "ullr.png");
    God Vamana = new God("Vamana", "Warrior", "Hindu", "vamana.png");
    God Vulcan = new God("Vulcan", "Mage", "Roman", "vulcan.png");
    God Xbalanque = new God("Xbalanque", "Hunter", "Mayan", "xbalanque.png");
    God XingTian = new God("Xing Tian", "Guardian", "Chinese", "xingtian.png");
    God Ymir = new God("Ymir", "Guardian", "Norse", "ymir.png");
    God Zeus = new God("Zeus", "Mage", "Greek", "zeus.png");
    God ZhongKui = new God("Zhong Kui", "Mage", "Chinese", "zhongkui.png");

    //Item Initializations
    //Tier 3---------------------------------------
    Item BookOfThoth = new Item("Book of Thoth");
    Item Transcendance = new Item("Transcendence");
    Item TyphonsFang = new Item("Typhon's Fang");
    Item BancroftsTalon = new Item("Bancroft's Talon");
    Item SoulGem = new Item("Soul Gem");
    Item PythagoremsPiece = new Item("Pythagorem's Piece");

    //Tier 2---------------------------------------
    Item TalonTrinket = new Item("Talon Trinket");
    Item EnchantedTrinket = new Item("Enchanted Trinket");

    //Tier 1---------------------------------------
    Item TinyTrinket = new Item("Tiny Trinket");

    private static Main instance;

    private Stage stage;

    public Main() {
        instance = this;
    }

    public static Main getInstance() {
        return instance;
    }

    public ArrayList<God> getGodList(){
        return godArrayList;
    }

    public ArrayList<Item> getItemList(){
        return itemArrayList;
    }

    public void setGod(String g){
        selectedGod = g;
    }

    public String getGod(){
        return selectedGod;
    }

    public Item getItem(String i){
        int count = 0;
        while(count < itemArrayList.size()){
            if(itemArrayList.get(count).getName().equals(i)){
                return itemArrayList.get(count);
            }
            count++;
        }
        System.out.println("Item not found");
        return null;
    }

    private void InitializeGods(){
        Ra.setBaseHealth(453);
        Ra.setHealthScale(68);
        Ra.setBaseMana(303);
        Ra.setManaScale(48);
        Ra.setBaseMS(360);
        Ra.setBasicAttackDamage(36);
        Ra.setBasicAttackScale(1.5);
        Ra.setAttackSpeed(0.89);
        Ra.setAttackSpeedScale(.01);
        Ra.setBasePhysicalDef(12.5);
        Ra.setPhysicalDefScale(2.5);
        Ra.setBaseMagicalDef(30.9);
        Ra.setMagicalDefScale(0.9);
        Ra.setHP5(7.48);
        Ra.setHP5Scale(0.48);
        Ra.setMP5(5.24);
        Ra.setMP5Scale(0.44);
        godArrayList.add(Ra);

        Agni.setBaseHealth(431);
        Agni.setHealthScale(71);
        Agni.setBaseMana(300);
        Agni.setManaScale(45);
        Agni.setBaseMS(355);
        Agni.setBasicAttackDamage(36);
        Agni.setBasicAttackScale(1.5);
        Agni.setAttackSpeed(1.01);
        Agni.setAttackSpeedScale(.012);
        Agni.setBasePhysicalDef(13.6);
        Agni.setPhysicalDefScale(2.6);
        Agni.setBaseMagicalDef(30.9);
        Agni.setMagicalDefScale(0.9);
        Agni.setHP5(7.47);
        Agni.setHP5Scale(0.47);
        Agni.setMP5(5.07);
        Agni.setMP5Scale(0.37);
        godArrayList.add(Agni);

        godArrayList.add(Achilles);

        godArrayList.add(Aphrodite);

        godArrayList.add(AMC);

        godArrayList.add(AhPuch);

        godArrayList.add(Amaterasu);

        godArrayList.add(Anhur);

        godArrayList.add(Anubis);

        godArrayList.add(AoKuang);

        godArrayList.add(Apollo);

        godArrayList.add(Arachne);

        godArrayList.add(Ares);

        godArrayList.add(Artemis);

        godArrayList.add(Artio);

        godArrayList.add(Athena);

        godArrayList.add(Awilix);

        godArrayList.add(Bacchus);

        godArrayList.add(Bakasura);

        godArrayList.add(Bastet);

        godArrayList.add(Bellona);

        godArrayList.add(Cabrakan);

        godArrayList.add(Camazotz);

        godArrayList.add(Cernunnos);

        godArrayList.add(Chaac);

        godArrayList.add(Change);

        godArrayList.add(Chiron);

        godArrayList.add(Chronos);

        godArrayList.add(CuChulainn);

        godArrayList.add(Cupid);

        godArrayList.add(DaJi);

        godArrayList.add(ErlangShen);

        godArrayList.add(Discordia);

        godArrayList.add(Chernobog);

        godArrayList.add(Fafnir);

        godArrayList.add(Fenrir);

        godArrayList.add(Freya);

        godArrayList.add(Ganesha);

        godArrayList.add(Geb);

        godArrayList.add(GuanYu);

        godArrayList.add(Hachiman);

        godArrayList.add(Hades);

        godArrayList.add(HeBo);

        godArrayList.add(Hel);

        godArrayList.add(Hercules);

        godArrayList.add(HouYi);

        godArrayList.add(HunBatz);

        godArrayList.add(Isis);

        godArrayList.add(Izanami);

        godArrayList.add(Janus);

        godArrayList.add(JingWei);

        godArrayList.add(Kali);

        godArrayList.add(Khepri);

        godArrayList.add(Kukulkan);

        godArrayList.add(Kumbhakarna);

        godArrayList.add(Kuzenbo);

        godArrayList.add(Loki);

        godArrayList.add(Medusa);

        godArrayList.add(Mercury);

        godArrayList.add(Morrigan);

        godArrayList.add(Neith);

        godArrayList.add(Nemesis);

        godArrayList.add(NeZha);

        godArrayList.add(Nike);

        godArrayList.add(Nox);

        godArrayList.add(NuWa);

        godArrayList.add(Odin);

        godArrayList.add(Osiris);

        godArrayList.add(Poseidon);

        godArrayList.add(Raijin);

        godArrayList.add(Rama);

        godArrayList.add(Ratatoskr);

        godArrayList.add(Ravana);

        godArrayList.add(Scylla);

        godArrayList.add(Serqet);

        godArrayList.add(Skadi);

        godArrayList.add(Sobek);

        godArrayList.add(Sol);

        godArrayList.add(SunWukong);

        godArrayList.add(Susano);

        godArrayList.add(Sylvanus);

        godArrayList.add(Terra);

        godArrayList.add(Thanatos);

        godArrayList.add(Thor);

        godArrayList.add(Thoth);

        godArrayList.add(Tyr);

        godArrayList.add(Ullr);

        godArrayList.add(Vamana);

        godArrayList.add(Vulcan);

        godArrayList.add(Xbalanque);

        godArrayList.add(XingTian);

        godArrayList.add(Ymir);

        godArrayList.add(Zeus);

        godArrayList.add(ZhongKui);

        godArrayList.add(Cerberus);
    }

    private void InitializeItems(){
        BookOfThoth.setDamageType("M");
        BookOfThoth.setMagicalPower(100);
        BookOfThoth.setMana(125);
        BookOfThoth.setMp5(15);
        BookOfThoth.setMaxStacks(75);
        BookOfThoth.setStacks(75);
        BookOfThoth.setStackMana(10);
        BookOfThoth.setTier(3);
        itemArrayList.add(BookOfThoth);

        Transcendance.setDamageType("P");
        Transcendance.setPhysicalPower(35);
        Transcendance.setMana(300);
        Transcendance.setMp5(6);
        Transcendance.setCDR(10);
        Transcendance.setMaxStacks(50);
        Transcendance.setStacks(50);
        Transcendance.setTier(3);
        itemArrayList.add(Transcendance);

        TinyTrinket.setDamageType("M");
        TinyTrinket.setMagicalPower(20);
        TinyTrinket.setMagicLS(.06);
        TinyTrinket.setTier(1);
        TinyTrinket.addRestriction("Typhon's Fang");
        TinyTrinket.addRestriction("Bancroft's Talon");
        TinyTrinket.addRestriction("Soul Gem");
        TinyTrinket.addRestriction("Pythagorem's Piece");
        itemArrayList.add(TinyTrinket);

        TalonTrinket.setDamageType("M");
        TalonTrinket.setMagicalPower(60);
        TalonTrinket.setMana(100);
        TalonTrinket.setMagicLS(.08);
        TalonTrinket.setTier(2);
        TalonTrinket.addRestriction("Typhon's Fang");
        TalonTrinket.addRestriction("Bancroft's Talon");
        itemArrayList.add(TalonTrinket);

        TyphonsFang.setDamageType("M");
        TyphonsFang.setMagicalPower(100);
        TyphonsFang.setMana(200);
        TyphonsFang.setMagicLS(.10);
        TyphonsFang.setTier(3);
        itemArrayList.add(TyphonsFang);

        BancroftsTalon.setDamageType("M");
        BancroftsTalon.setMagicalPower(100);
        BancroftsTalon.setMagicLS(.15);
        BancroftsTalon.setMana(150);
        BancroftsTalon.setTier(3);
        itemArrayList.add(BancroftsTalon);

        EnchantedTrinket.setDamageType("M");
        EnchantedTrinket.setMagicalPower(30);
        EnchantedTrinket.setHealth(100);
        EnchantedTrinket.setMagicLS(.12);
        EnchantedTrinket.setTier(2);
        EnchantedTrinket.addRestriction("Soul Gem");
        EnchantedTrinket.addRestriction("Pythagorem's Piece");
        itemArrayList.add(EnchantedTrinket);

        SoulGem.setDamageType("M");
        SoulGem.setMagicalPower(65);
        SoulGem.setHealth(150);
        SoulGem.setMagicLS(.12);
        SoulGem.setCDR(10);
        SoulGem.setTier(3);
        itemArrayList.add(SoulGem);

        PythagoremsPiece.setDamageType("M");
        PythagoremsPiece.setMagicalPower(40);
        PythagoremsPiece.setHealth(200);
        PythagoremsPiece.setMagicLS(.12);
        PythagoremsPiece.setCDR(10);
        PythagoremsPiece.setTier(3);
        itemArrayList.add(PythagoremsPiece);
    }

    @Override
    public void start(Stage stage) throws Exception {
        InitializeGods();
        InitializeItems();
        this.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.setTitle("Smite Builder");
        //stage.getIcons().add(new Image("images/recipeBook.png"));
        stage.show();
    }

    public void resize(double w, double h){
        stage.setWidth(w);
        stage.setHeight(h);
    }

    public static void main (String[]args){
        launch(args);
    }
}