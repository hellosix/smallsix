package cn.hellosix.model;

/**
 * Created by lzz on 2018/10/2.
 */
public class ColumnExtend extends Column {
    private Column column;
    private FieldExtend fieldExtend = new FieldExtend();

    public ColumnExtend(){

    }

    public ColumnExtend(Column column, FieldExtend fieldExtend){
        this.column = column;
        this.fieldExtend = fieldExtend;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public FieldExtend getFieldExtend() {
        return fieldExtend;
    }

    public void setFieldExtend(FieldExtend fieldExtend) {
        this.fieldExtend = fieldExtend;
    }

    @Override
    public String toString() {
        return "ColumnExtend{" +
                "fieldExtend=" + fieldExtend +
                '}';
    }
}
