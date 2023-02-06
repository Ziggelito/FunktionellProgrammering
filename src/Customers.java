public class Customers {
    protected int CustomerID;
    protected String CustomerName;
    protected int CustomerPass;

    public Customers(){}

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public int getCustomerPass() {
        return CustomerPass;
    }

    public void setCustomerPass(int customerPass) {
        CustomerPass = customerPass;
    }
}
