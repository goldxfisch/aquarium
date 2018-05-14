package pojo;

public class VehicleUnderTest {
    public VehicleUnderTest(String vehicleRegistrationNumber, String vehicleMake, String vehicleColour) {
        VehicleRegistrationNumber = vehicleRegistrationNumber;
        VehicleMake = vehicleMake;
        VehicleColour = vehicleColour;
    }

    public String getVehicleRegistrationNumber() {

        return VehicleRegistrationNumber;
    }

    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        VehicleRegistrationNumber = vehicleRegistrationNumber;
    }

    public String getVehicleMake() {
        return VehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        VehicleMake = vehicleMake;
    }

    public String getVehicleColour() {
        return VehicleColour;
    }

    public void setVehicleColour(String vehicleColour) {
        VehicleColour = vehicleColour;
    }

    String VehicleRegistrationNumber;
    String VehicleMake;
    String VehicleColour;

    @Override
    public String toString() {
        return "VehicleUnderTest{" +
                "VehicleRegistrationNumber='" + VehicleRegistrationNumber + '\'' +
                ", VehicleMake='" + VehicleMake + '\'' +
                ", VehicleColour='" + VehicleColour + '\'' +
                '}';
    }
}
