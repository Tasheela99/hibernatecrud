import javax.persistence.*;

@Entity(name = "vehicle")
//@Table(name = "vehicle")
public class Vehicle {

    @Id
    private long vehicleId;
    @Column(name = "brand",length = 45)
    private String vehicleBrand;


    /*----------------------------------------------------*/
    @OneToOne
    @Column(unique = true)
    @JoinColumn(name = "customer")
    private Customer customer;

    /*----------------------------------------------------*/

    public Vehicle() {
    }

    public Vehicle(long vehicleId, String vehicleBrand) {
        this.vehicleId = vehicleId;
        this.vehicleBrand = vehicleBrand;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", vehicleBrand='" + vehicleBrand + '\'' +
                '}';
    }
}
