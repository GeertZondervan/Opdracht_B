
package opdracht_b.pojo;

import annotations.Column;
import annotations.Entity;
import annotations.Id;
import annotations.Table;

@Entity
@Table(tableName = "Test")
public class TestKlasse {
    @Id
    private long id;
    
    @Column(columnName = "viaNaam")
    private int mijnInt;
    
    @Column(columnName = "LANGEKOLOMNAAM", notNull = true)
    private String testKolom01;
    
    @Column(length = 120)
    private String testKolom02;
    
    @Column(length = 20, columnName = "TESTKOLOM03", notNull = true)
    private String testKolom03;
}
