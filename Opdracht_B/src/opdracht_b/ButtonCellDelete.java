package opdracht_b;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;

class ButtonCellDelete extends TableCell<String, Boolean> {

    final Button cellButton = new Button("Delete");

    ButtonCellDelete(final TableView tblView) {

        cellButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {

                int selectedIndex = getTableRow().getIndex();
                ObservableList<String> selectedKlantList = (ObservableList<String>) tblView.getItems().get(selectedIndex);
                System.out.println(selectedKlantList);
                System.out.println(selectedKlantList.get(0));

                AppFunctionality functions = new AppFunctionality();
                functions.deleteKlant(Integer.parseInt(selectedKlantList.get(0)));
                
            }
        });
    }

    //Display button if the row is not empty
    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if (!empty) {
            setGraphic(cellButton);
        }
    }
}
