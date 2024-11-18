package quotify_app.adapters.comparator;

import java.util.ArrayList;

/**
 * The state for the Comparator View Model.
 */
public class ComparatorState {
    private boolean isLoggedIn;
    private ArrayList<Object> list1;
    private ArrayList<Object> list2;
    private ArrayList<Object> list3;

    /**
     * Initializes the ComparatorState with default values.
     */
    public ComparatorState() {
        // Default to not logged in
        this.isLoggedIn = false;
        this.list1 = new ArrayList<>();
        this.list2 = new ArrayList<>();
        this.list3 = new ArrayList<>();
    }

    // Getter and setter for isLoggedIn
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    // Getter for lists
    public String getList1() {
        return String.valueOf(list1);
    }

    public String getList2() {
        return String.valueOf(list2);
    }

    public String getList3() {
        return String.valueOf(list3);
    }

    /**
     * Sets list1 to the provided value.
     *
     * @param list1 the {@code ArrayList} to set as list1. If {@code null}, no changes are made.
     */
    public void setList1(ArrayList<Object> list1) {
        if (list1 != null) {
            this.list1 = new ArrayList<>(list1);
        }
    }

    /**
     * Sets list2 to the provided value.
     *
     * @param list2 the {@code ArrayList} to set as list2. If {@code null}, no changes are made.
     */
    public void setList2(ArrayList<Object> list2) {
        if (list2 != null) {
            this.list2 = new ArrayList<>(list2);
        }
    }

    /**
     * Sets list3 to the provided value.
     *
     * @param list3 the {@code ArrayList} to set as list3. If {@code null}, no changes are made.
     */
    public void setList3(ArrayList<Object> list3) {
        if (list3 != null) {
            this.list3 = new ArrayList<>(list3);
        }
    }
}
