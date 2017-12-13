package at.fhooe.mcm.context.elements;

/**
 * Context element for the vehicle used (car, pedestrian, ...).
 * @author ifumi
 *
 */
public class VehicleContext extends ContextElement {

    public enum VehicleType{
        PUBLIC, PRIVATE
    }

    public enum VehicleValue{
        CAR, BIKE, BUS, TRAIN, PLANE, NOVEHICLE
    }

    private VehicleType mType;
    private VehicleValue mVehicle;

    public VehicleContext(int _id, String _key, VehicleType _type, VehicleValue _vehicle) {
        super(_id, _key);
        mType = _type;
        mVehicle = _vehicle;
    }

    public VehicleType getType() {
        return mType;
    }

    public VehicleValue getVehicle() {
        return mVehicle;
    }
}
