import javax.persistence.*;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    private double orderCost;

    /*-----------------------------------------------------------------------------*/

    @ManyToOne
    private Customer customer;

    /*-----------------------------------------------------------------------------*/

    public Orders() {
    }

    public Orders(long orderId, double orderCost) {
        this.orderId = orderId;
        this.orderCost = orderCost;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public double getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(double orderCost) {
        this.orderCost = orderCost;
    }
}
