package opdracht_b;

import annotations.Entity;
import annotations.Id;
import annotations.Table;
import annotations.Column;
import com.mysql.jdbc.authentication.MysqlClearPasswordPlugin;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Database_Builder {

    public void createTable(Object object) {
        if (!(object.getClass().isAnnotationPresent(Entity.class))) {
            throw new IllegalArgumentException("Object is not annotated with @Entity");
        }

        if (!(checkForPrimaryKey(object))) {
            throw new IllegalArgumentException("Could not find a primary key, missing @Id on an object field");
        }
        String sqlStatement = buildCreateTableStatememt(object);
        System.out.println(sqlStatement);

        //create the actual table (through AppFunctionality) 
        AppFunctionality functions = new AppFunctionality();
        functions.voerSQLUit(sqlStatement);
    }

    public String buildCreateTableStatememt(Object object) {
        int variableToInsert = 0;
        String className = getClassName(object);
        String sqlTableName = null;

        if (object.getClass().isAnnotationPresent(Table.class)) {
            Table table = object.getClass().getAnnotation(Table.class);
            if (!(table.tableName().equals(""))) {
                sqlTableName = table.tableName();
            } else {
                sqlTableName = className.substring(className.lastIndexOf(".") + 1);
            }
        } else {
            sqlTableName = className.substring(className.lastIndexOf(".") + 1);
        }

        String buildSqlStatement = "CREATE TABLE " + sqlTableName + "( ";
        String idString = "";

        try {
            Class myClass = Class.forName(className);
            Field[] declaredFields = myClass.getDeclaredFields();

            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                variableToInsert++;
                if (variableToInsert > 1) {
                    buildSqlStatement += ", ";
                }
                buildSqlStatement += getFieldName(declaredField);
                buildSqlStatement += " " + getFieldType(declaredField);
                buildSqlStatement += " " + checkNotNullValue(declaredField);

                if (declaredField.isAnnotationPresent(Id.class)) {
                    idString = getFieldName(declaredField);
                    Annotation[] annotations = declaredField.getAnnotations();
                    for (Annotation annotation : annotations) {
                        if (annotation instanceof Id) {
                            Id id = (Id) annotation;
                            if (id.autoIncrement()) {
                                buildSqlStatement += " AUTO_INCREMENT";
                            }
                        }
                    }
                }
            }

            buildSqlStatement += ", PRIMARY KEY (" + idString + "))";

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();

        }
        return buildSqlStatement;

    }

    public String checkNotNullValue(Field field) {
        String notNull = "";
        Annotation[] annotations = field.getDeclaredAnnotations();
        if (annotations.length < 1) {
            notNull = "";
        }
        for (Annotation annotation : annotations) {
            if (annotation instanceof Column) {
                Column column = (Column) annotation;
                if (column.notNull()) {
                    notNull = "NOT NULL";
                }
            } else {
                notNull = "";
            }
        }
        return notNull;
    }

    public String getFieldType(Field field) {
        String dataType = "";
        if (field.getType().isPrimitive()) {
            if (field.getType().isAssignableFrom(Long.TYPE)) {
                dataType = "INT(11) UNSIGNED";
            } else if (field.getType().isAssignableFrom(Integer.TYPE)) {
                dataType = "INT(11) UNSIGNED";

            }
        } else {
            if (field.getType().isAssignableFrom(String.class
            )) {
                dataType = "VARCHAR(" + getColumSizeForVarcharFromField(field) + ")";
            } else {
                throw new IllegalArgumentException("Cannot convert this data tye to a SQL data type");
            }
        }

        return dataType;
    }

    public String getColumSizeForVarcharFromField(Field field) {
        String length = "";
        Annotation[] annotations = field.getDeclaredAnnotations();
        if (annotations.length < 1) {
            length = "45";
        }
        for (Annotation annotation : annotations) {
            if (annotation instanceof Column) {
                Column column = (Column) annotation;
                length = column.length() + "";
            } else {
                length = "45";
            }
        }
        return length;
    }

    public String getFieldName(Field field) {
        String fieldName = "";
        Annotation[] annotations = field.getDeclaredAnnotations();
        if (annotations.length < 1) {
            fieldName = field.getName();
        } else {
            for (Annotation annotation : annotations) {
                if (annotation instanceof Column) {
                    Column column = (Column) annotation;
                    if (!(column.columnName().equals(""))) {
                        fieldName = column.columnName();
                    } else {
                        fieldName = field.getName();
                    }

                } else {
                    fieldName = field.getName();
                }
            }
        }
        return fieldName;
    }

    public boolean checkForPrimaryKey(Object object) {
        boolean hasId = false;
        String className = getClassName(object);
        try {
            Class myClass = Class.forName(className);
            Field[] declaredFields = myClass.getDeclaredFields();

            for (Field declaredField : declaredFields) {
                try {
                    declaredField.setAccessible(true);

                    if ((declaredField.isAnnotationPresent(Id.class
                    ))) {
                        hasId = true;
                    }
                } catch (IllegalArgumentException | SecurityException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return hasId;
    }

    private String getClassName(Object object) {

        String className = object.getClass().toString();
        // klassenaam uit class XXXX string halen, class eraf
        className = className.substring("class ".length());

        return className;

    }

}
