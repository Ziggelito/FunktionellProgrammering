public class Orders {
    protected int OrderID;
    protected int OrderDate;
    protected String Location;

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public int getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(int orderDate) {
        OrderDate = orderDate;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
