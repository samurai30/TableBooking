package TableCollectionData;

public class Tables {

    private int tablesize;
    private String tableCat;

    public int getTablesize() {
        return tablesize;
    }

    public void setTablesize(int tablesize) {
        this.tablesize = tablesize;
    }

    public String getTableCat() {
        return tableCat;
    }

    public void setTableCat(String tableCat) {
        this.tableCat = tableCat;
    }

    public Tables(int size, String cat){
        this.tableCat = cat;
        this.tablesize = size;
    }
}
