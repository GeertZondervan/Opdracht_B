package opdracht_b;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.sql.rowset.CachedRowSet;
import opdracht_b.dbconnect.DBConnect;
import opdracht_b.pojo.Klant;
import opdracht_b.pojo.KlantWrapper;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Opdracht_B2 extends Application {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private ObservableList<ObservableList> data;
    private BorderPane mainPane = new BorderPane();
    private MenuBar menuBar = new MenuBar();
    private GridPane innerTopPane = new GridPane();
    private GridPane datasourceGrid = new GridPane();
    private GridPane randomPane = new GridPane();
    private BorderPane sqlPane = new BorderPane();
    private TableView tableView = new TableView<KlantWrapper>();
    private AppFunctionality functions = new AppFunctionality();
    private CachedRowSet crs;

    private final Stage klantStage = new Stage();
    private GridPane newKlantPane;

    private CheckBoxCellFactory cbcf = new CheckBoxCellFactory();

    @Override
    public void start(Stage primaryStage) {

        //Menu
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

        //File menu 
        Menu fileMenu = new Menu("_File");
        fileMenu.setMnemonicParsing(true);
        MenuItem newMenuItem = new MenuItem("New");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(e -> Platform.exit());

        fileMenu.getItems().addAll(newMenuItem, saveMenuItem, new SeparatorMenuItem(), exitMenuItem);

        //edit Menu
        Menu editMenu = new Menu("_Edit");
        editMenu.setMnemonicParsing(true);

        //Help Menu
        Menu helpMenu = new Menu("_Help");
        helpMenu.setMnemonicParsing(true);

        //Datasource info
        TextField tfUrlDatasource = new TextField();
        TextField tfUser = new TextField();
        PasswordField tfPassword = new PasswordField();
        Button btSetConnection = new Button("Set connectie gegevens");

        datasourceGrid.setPadding(new Insets(6));
        datasourceGrid.setHgap(5.5);
        datasourceGrid.setVgap(5.5);
        datasourceGrid.add(new Label("Datasource"), 0, 0);
        datasourceGrid.add(new ComboBox(), 1, 0);
        datasourceGrid.add(new Label("Url datasource"), 0, 1);
        datasourceGrid.add(tfUrlDatasource, 1, 1);
        datasourceGrid.add(new Label("Gebruikersnaam"), 0, 2);
        datasourceGrid.add(tfUser, 1, 2);
        datasourceGrid.add(new Label("Wachtwoord"), 0, 3);
        datasourceGrid.add(tfPassword, 1, 3);
        datasourceGrid.add(btSetConnection, 0, 4);
        datasourceGrid.setAlignment(Pos.BOTTOM_LEFT);

        //Random klanten
        TextField tfAantalKlanten = new TextField();
        tfAantalKlanten.setPrefColumnCount(4);
        Button btRandom = new Button("Maak klanten aan");
        randomPane.setPadding(new Insets(6));
        randomPane.setHgap(5.5);
        randomPane.setVgap(5.5);
        randomPane.add(new Label("Maak random klanten"), 0, 0);
        randomPane.add(new Label("Voer aantal in"), 0, 1);
        randomPane.add(tfAantalKlanten, 1, 1);
        randomPane.add(btRandom, 0, 2);
        randomPane.setAlignment(Pos.BOTTOM_LEFT);

        //SQL Invoer gedeelte
        TextArea taSQL = new TextArea();
        taSQL.setWrapText(true);
        Button btVoerSqlUit = new Button("Voor SQL uit");
        Button btDelete = new Button("Delete");
        Button btUpdate = new Button("Update");
        HBox buttonBox = new HBox();
        buttonBox.setPadding(new Insets(6));
        buttonBox.setSpacing(6);
        buttonBox.getChildren().addAll(btVoerSqlUit, btDelete, btUpdate);
        sqlPane.setPadding(new Insets(6));
        sqlPane.setTop(taSQL);
        sqlPane.setCenter(buttonBox);
        sqlPane.setAlignment(buttonBox, Pos.BOTTOM_LEFT);

        //Buttons HBox
        HBox buttonBox2 = new HBox();
        buttonBox2.setPadding(new Insets(6));
        buttonBox2.setSpacing(6);
        Button btVervers = new Button("Ververs Tabel");
        Button btNewKlant = new Button("Maak een nieuwe klant");
        VBox connectionPoolBox = new VBox();
        connectionPoolBox.setPadding(new Insets(6));
        connectionPoolBox.setSpacing(6);
        Text textConPool = new Text("Connection Pool");
        RadioButton rbHikari = new RadioButton("Hikari");
        RadioButton rbC3p0 = new RadioButton("C3P0");
        ToggleGroup group = new ToggleGroup();
        rbHikari.setToggleGroup(group);
        rbC3p0.setToggleGroup(group);
        rbHikari.setSelected(true);
        connectionPoolBox.getChildren().addAll(textConPool, rbHikari, rbC3p0);
        buttonBox2.getChildren().addAll(btVervers, btNewKlant, connectionPoolBox);

        //TABLEVIEW
        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);
        innerTopPane.setPadding(new Insets(10));
        innerTopPane.setHgap(10);
        innerTopPane.add(datasourceGrid, 0, 0);
        innerTopPane.add(randomPane, 1, 0);
        innerTopPane.add(sqlPane, 2, 0);
        innerTopPane.add(buttonBox2, 0, 3);
        mainPane.setTop(menuBar);
        mainPane.setCenter(innerTopPane);
        mainPane.setBottom(tableView);

        Scene scene = new Scene(mainPane, 1200, 800);

        primaryStage.setTitle("B!");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Button functions
        btSetConnection.setOnAction(e -> {
            functions.setConnection(tfUrlDatasource.getText().trim(), tfUser.getText().trim(), tfPassword.getText().trim());
            buildData(rbHikari.isSelected(), rbC3p0.isSelected());
        });

        btVervers.setOnAction(e -> buildData(rbHikari.isSelected(), rbC3p0.isSelected()));

        btNewKlant.setOnAction(e -> {
            newKlantScherm(rbHikari.isSelected(), rbC3p0.isSelected());
        });

        btVoerSqlUit.setOnAction(e -> {
            functions.setHikariSelected(rbHikari.isSelected());
            functions.voerSQLUit(taSQL.getText());
        });

        btDelete.setOnAction(e -> {

            //for(Object klant: tableView.getItems()){
            for (int i = 0; i < tableView.getItems().size(); i++) {
                System.out.println(tableView.getItems().size() + "FFFFFFFFFFFFFFFFFFFFFFFFFFFFF");

                // KlantWrapper kw = (KlantWrapper) tableView.getItems().get(i);
                //KlantWrapper kw2 = (KlantWrapper) tableView.getSelectionModel().getSelectedItems().get(i);
                ObservableList<KlantWrapper> listkw = tableView.getSelectionModel().getSelectedItems();
                ObservableList<KlantWrapper> lijstje = tableView.getItems();

                List<KlantWrapper> ls = new ArrayList<>();
                KlantWrapper[] arr = null;
                //lijstje.toArray(arr);
                ls.addAll(lijstje);
                System.out.println(listkw + "SSSSSSSSSSSSSSSSSSSSSSSSSS");
                System.out.println(lijstje + "ffffffffffffffffffffffffffffffffSSSS");
                System.out.println(data.get(8).get(9) + "");
                //  System.out.println(kw2 + "FFFFFFFFFFFFFFF");
                if (lijstje.get(8).getTo_delete()) {
                    System.out.println("WAAR");
                } else {
                    System.out.println("niet waar");
                }
                System.out.println(lijstje.get(8));
                for (int j = 0; j < lijstje.size(); i++) {

//                    if (lijstje.get(j).getTo_delete().equals(true)) {
//                        functions.deleteKlant(lijstje.get(j).getKlant_id());
                }

            }

//               if(((KlantWrapper)klant).getTo_delete().equals(true)){
//                   functions.deleteKlant(((KlantWrapper)klant).getKlant_id());
        }
        );
        btRandom.setOnAction(e -> {
            Klant klantje = klantje = functions.readKlant("Susanne", "Wezels");
            klantje.setVoornaam("Suzy");
            klantje.setAchternaam("Wessels");
            functions.updateKlant(klantje);

            System.out.println(klantje.getKlant_id() + " " + klantje.getVoornaam() + "DDDDDDD");
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void buildData(boolean hikariSelected, boolean c3p0Selected) {
        Connection con;
        data = FXCollections.observableArrayList();
        try {
            log.info("Database Connection set, url: {}, user: {}, password: {} \n", DBConnect.getUrl(), DBConnect.getUser(), DBConnect.getPassword());

            //Database connection + CachedRowSetImpl
//            crs = new CachedRowSetImpl();
//            con = DBConnect.getConnection();
//            crs.setCommand("SELECT * from Klant");
//            int[] keys = {1};
//            crs.setKeyColumns(keys);
//            crs.execute(con);
            //Database coneection with Hikari + CachedRowSetImpl
            if (hikariSelected) {
                log.info("Using HIKARI CONNECTION POOL");
                crs = new CachedRowSetImpl();
                con = DBConnect.connectWithHikari();
                crs.setCommand("SELECT * from Klant");
                int[] keys = {1};
                crs.setKeyColumns(keys);
                crs.execute(con);
            }

            if (c3p0Selected) {
                log.info("Using C3P0 CONNECTION POOL");
                crs = new CachedRowSetImpl();
                con = DBConnect.connectWithC3p0();
                crs.setCommand("SELECT * from Klant");
                int[] keys = {1};
                crs.setKeyColumns(keys);
                crs.execute(con);
            }

            //table column added dynamicly
            tableView.setEditable(true);
            for (int i = 0; i < crs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(crs.getMetaData().getColumnName(i + 1));
                col.prefWidthProperty().bind(tableView.widthProperty().divide(crs.getMetaData().getColumnCount() + 2));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {

                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {

                        return new SimpleStringProperty(param.getValue().get(j).toString());

                    }

                });
                col.setOnEditCommit(
                        new EventHandler<TableColumn.CellEditEvent<KlantWrapper, String>>() {
                            @Override
                            public void handle(TableColumn.CellEditEvent<KlantWrapper, String> t) {
                                ((KlantWrapper) t.getTableView().getItems().get(
                                        t.getTablePosition().getRow())).setVoornaam(t.getNewValue());
                            }
                        }
                );

                tableView.getColumns().addAll(col);
            }

            TableColumn colUpdate = new TableColumn("Update");

            colUpdate.setMinWidth(100);
            colUpdate.setEditable(true);
            colUpdate.setCellValueFactory(new PropertyValueFactory<Klant, Boolean>("checkedUpdate"));
            colUpdate.setCellFactory(new CheckBoxCellFactory());

            TableColumn<KlantWrapper, KlantWrapper> colDelete = new TableColumn("Delete");
            colDelete.setMinWidth(100);
            colDelete.setEditable(true);
            colDelete.setCellValueFactory(new PropertyValueFactory("to_delete"));
            //colDelete.setCellFactory(CheckBoxTableCell.forTableColumn(colDelete));
            colDelete.setCellFactory(new CheckBoxCellFactory());

            TableColumn<KlantWrapper, Boolean> d_Col = new TableColumn<KlantWrapper, Boolean>("Delete");
            d_Col.setCellValueFactory(new PropertyValueFactory<KlantWrapper, Boolean>("to_delete"));
            d_Col.setCellFactory(CheckBoxTableCell.forTableColumn(d_Col));
            d_Col.setEditable(true);

            TableColumn delButtonsCol = new TableColumn("Delete");
            delButtonsCol.setSortable(false);
            delButtonsCol.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<String, Boolean>, ObservableValue<Boolean>>() {

                        @Override
                        public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<String, Boolean> p) {
                            return new SimpleBooleanProperty(p.getValue() != null);
                        }
                    });
            delButtonsCol.setCellFactory(
                    new Callback<TableColumn<String, Boolean>, TableCell<String, Boolean>>() {

                        @Override
                        public TableCell<String, Boolean> call(TableColumn<String, Boolean> p) {
                            return new ButtonCellDelete(tableView);
                        }

                    });
            /*
             TableColumn registeredCol = new TableColumn("Registered");
             registeredCol.setCellValueFactory(
             new Callback<CellDataFeatures<KlantWrapper, Boolean>, ObservableValue<Boolean>>() {
             //This callback tell the cell how to bind the data model 'Registered' property to
             //the cell, itself.
             @Override
             public ObservableValue<Boolean> call(CellDataFeatures<KlantWrapper, Boolean> param) {
             return param.getValue().to_deleteProperty();
             }
             });

             //This tell how to insert and render a checkbox in the cell
             // 
             //The CheckBoxTableCell has the updateItem() method which by default links up the
             //cell value (i.e. the 'Registered' property to the checkbox.  And this method is
             //automatically call at the appropriate time, such as when creating and rendering
             //the cell (I believe).
             //
             //In this case, as the registed_col.setCellValueFactory() method has specified
             //'Registered' in the actual data model (i.e. personList), therefore the checkbox will
             //be bound to this property.
             registeredCol.setCellFactory(CheckBoxTableCell.forTableColumn(registeredCol));

            
            
             TableColumn<KlantWrapper, Boolean> select_col = new TableColumn<KlantWrapper, Boolean>("Select");

             List<BooleanProperty> selectedRowList = new ArrayList<BooleanProperty>();

             //This callback allows the checkbox in the column to access selectedRowList (or more
             //exactly, the boolean property it contains
             Callback<Integer, ObservableValue<Boolean>> selectedStateSelectColumn
             = new Callback<Integer, ObservableValue<Boolean>>() {

             //index in this context reference the table cell index (I believe)
             @Override
             public ObservableValue<Boolean> call(Integer index) {
             return selectedRowList.get(index);
             }
             };
             //Initialise the selectedRowList

             //            for (Person p : data) {
             //                //initially, it starts off as false, i.e. unticked state
             //                selectedRowList.add(new SimpleBooleanProperty());
             //            }
             //            
             for(int i = 0; i < data.size(); i++){
             //initially, it starts off as false, i.e. unticked state
             selectedRowList.add(new SimpleBooleanProperty());
             }

             select_col.setCellValueFactory(
             new Callback<CellDataFeatures<KlantWrapper, Boolean>, ObservableValue<Boolean>>() {
             //retrieve the cell index and use it get boolean property in the selectedRowList
             @Override
             public ObservableValue<Boolean> call(CellDataFeatures<KlantWrapper, Boolean> cdf) {

             TableView<KlantWrapper> tblView = cdf.getTableView();

             KlantWrapper rowData = cdf.getValue();

             int rowIndex = tblView.getItems().indexOf(rowData);

             return selectedRowList.get(rowIndex);
             }
             });

             select_col.setCellFactory(
             CheckBoxTableCell.forTableColumn(selectedStateSelectColumn));
             */
            tableView.getColumns().addAll(colUpdate, d_Col, delButtonsCol);

            tableView.setEditable(true);

            //data added to ObservableList data
            while (crs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= crs.getMetaData().getColumnCount(); i++) {
                    //iterate Column
                    row.add(crs.getString(i));
                }
                log.info("Row added: {}", row);
                data.add(row);

                tableView.setItems(data);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error on building Data");
        }

    }

    public void newKlantScherm(boolean hikariSelected, boolean c3p0Selected) {
        newKlantPane = new GridPane();
        TextField tfVoornaam = new TextField();
        TextField tfAchternaam = new TextField();
        TextField tfTussenvoegsel = new TextField();
        TextField tfEmail = new TextField();
        TextField tfStraatnaam = new TextField();
        TextField tfPostcode = new TextField();
        TextField tfToevoeging = new TextField();
        TextField tfHuisnummer = new TextField();
        TextField tfWoonplaats = new TextField();
        Button btRegisterKlant = new Button("Register");

        newKlantPane.setPadding(new Insets(6));
        newKlantPane.setHgap(5.5);
        newKlantPane.setVgap(5.5);
        newKlantPane.add(new Label("Voornaam"), 0, 1);
        newKlantPane.add(tfVoornaam, 1, 1);
        newKlantPane.add(new Label("Tussenvoegsel"), 0, 2);
        newKlantPane.add(tfTussenvoegsel, 1, 2);
        newKlantPane.add(new Label("Achternaam"), 0, 3);
        newKlantPane.add(tfAchternaam, 1, 3);
        newKlantPane.add(new Label("Email"), 0, 4);
        newKlantPane.add(tfEmail, 1, 4);
        newKlantPane.add(new Label("Straatnaam"), 0, 5);
        newKlantPane.add(tfStraatnaam, 1, 5);
        newKlantPane.add(new Label("Postcode"), 0, 6);
        newKlantPane.add(tfPostcode, 1, 6);
        newKlantPane.add(new Label("Toevoeging"), 0, 7);
        newKlantPane.add(tfToevoeging, 1, 7);
        newKlantPane.add(new Label("Huisnummer"), 0, 8);
        newKlantPane.add(tfHuisnummer, 1, 8);
        newKlantPane.add(new Label("Woonplaats"), 0, 9);
        newKlantPane.add(tfWoonplaats, 1, 9);
        newKlantPane.add(btRegisterKlant, 1, 10);

        btRegisterKlant.setOnAction(e -> {

            Klant klant = new Klant();

            klant.setVoornaam(tfVoornaam.getText().trim());
            klant.setTussenvoegsel(tfTussenvoegsel.getText().trim());
            klant.setAchternaam(tfAchternaam.getText().trim());
            klant.setEmail(tfEmail.getText().trim());
            klant.setStraatnaam(tfStraatnaam.getText().trim());
            klant.setPostcode(tfPostcode.getText().trim());
            klant.setToevoeging(tfToevoeging.getText().trim());

            try {
                klant.setHuisnummer(Integer.parseInt(tfHuisnummer.getText().trim()));
            } catch (NumberFormatException ex) {
                tfHuisnummer.setText("Voer een integer in");
            }

            klant.setWoonplaats(tfWoonplaats.getText().trim());

            EmailValidator emailValid = EmailValidator.getInstance();
            boolean valid = emailValid.isValid(klant.getEmail());

            if (valid && !(klant.getHuisnummer() <= 0)) {
                functions.setHikariSelected(hikariSelected);
                functions.createKlant(klant);
                klantStage.close();
            } else if (!valid) {
                tfEmail.setText("Invalid email adress");
            }

            buildData(hikariSelected, c3p0Selected);
        }
        );
        Scene klantScene = new Scene(newKlantPane, 300, 400);
        //klantStage.initModality(Modality.APPLICATION_MODAL);
        klantStage.setScene(klantScene);
        klantStage.setTitle("Nieuwe Klant toevoegen");
        klantStage.show();

    }
}
