package quotify_app.usecases.comparator;

import java.util.ArrayList;

/**
 * Output Data for the Comparator Use Case, holding the result of comparing.
 */
public class ComparatorOutputData {
    private ArrayList<Object> list1;
    private ArrayList<Object> list2;
    private ArrayList<Object> list3;

    // Constructor with all parameters as ArrayList<Object>
    public ComparatorOutputData(ArrayList<Object> list1, ArrayList<Object> list2, ArrayList<Object> list3) {
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
    }

    public ArrayList<Object> getList1() {
        return list1;
    }

    public ArrayList<Object> getList2() {
        return list2;
    }

    public ArrayList<Object> getList3() {
        return list3;
    }
}
