
package opdracht_b;


import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import opdracht_b.pojo.Klant;
import opdracht_b.pojo.KlantWrapper;

public class CustomCheckBoxCell extends TableCell<KlantWrapper, Boolean> {
    private final CheckBox box = new CheckBox();

    public CustomCheckBoxCell(){
        box.setOnAction(e -> {

                getTableView().getItems().get(getIndex()).setTo_delete(box.isSelected());
            });
        }

    @Override
    protected void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);

        if(!empty){
            box.setSelected(item);
            setText(getTableView().getItems().get(getIndex()).toString());
            setGraphic(box);
        }
        else {
            setText(null);
            setGraphic(null);
        }
    }
    
  
}

