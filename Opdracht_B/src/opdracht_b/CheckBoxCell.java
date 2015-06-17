
package opdracht_b;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import opdracht_b.pojo.KlantWrapper;


public class CheckBoxCell<S, T> extends TableCell<S, T> {
 
private final CheckBox checkBox;
private ObservableValue<T> ov;
 
public CheckBoxCell() {
    this.checkBox = new CheckBox();
    this.checkBox.setAlignment(Pos.CENTER);
    setAlignment(Pos.CENTER);
    setGraphic(checkBox);
}
 
@Override
public void updateItem(T item, boolean empty) {
    super.updateItem(item, empty);
    if (empty) {
        setText(null);
        setGraphic(null);
    } else {
        setGraphic(checkBox);
        if (ov instanceof BooleanProperty) {
            checkBox.selectedProperty().unbindBidirectional((BooleanProperty) ov);
        }
        ov = getTableColumn().getCellObservableValue(getIndex());
        if (ov instanceof BooleanProperty) {
            checkBox.selectedProperty().bindBidirectional((BooleanProperty) ov);
        }
    }
}
 
@Override
public void startEdit() {
    super.startEdit();
    if (isEmpty()) {
        return;
    }
    checkBox.setDisable(false);
    checkBox.requestFocus();
}
 
@Override
public void cancelEdit() {
    super.cancelEdit();
    checkBox.setDisable(true);
}
}