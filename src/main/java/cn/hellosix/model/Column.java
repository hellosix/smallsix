package cn.hellosix.model;

/**
 * Created by lzz on 2018/10/1.
 */
public class Column {
    protected String database;
    protected String table;
    protected String afterField = "latest";
    protected String comment;
    protected String cname;
    protected String reName;
    protected String ctype;
    protected int size;

    public Column(){

    }

    public Column(String cname, String ctype, int size) {
        this.cname = cname;
        this.ctype = ctype;
        this.size = size;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getAfterField() {
        return afterField;
    }

    public void setAfterField(String afterField) {
        this.afterField = afterField;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getReName() {
        return reName;
    }

    public void setReName(String reName) {
        this.reName = reName;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Column{" +
                "database='" + database + '\'' +
                ", table='" + table + '\'' +
                ", afterField='" + afterField + '\'' +
                ", comment='" + comment + '\'' +
                ", cname='" + cname + '\'' +
                ", reName='" + reName + '\'' +
                ", ctype='" + ctype + '\'' +
                ", size=" + size +
                '}';
    }
}
