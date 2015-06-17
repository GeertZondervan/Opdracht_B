
package opdracht_b;

import javafx.scene.control.TableCell;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;
import opdracht_b.pojo.Klant;

public class CheckBoxCellFactory implements Callback {
 
    @Override
    public TableCell call(Object param) {
        CheckBoxTableCell<Klant, Boolean> checkBoxCell = new CheckBoxTableCell();
        return checkBoxCell;
        
        
        
    }
    
}
