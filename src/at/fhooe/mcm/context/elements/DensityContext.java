package at.fhooe.mcm.context.elements;

public class DensityContext extends ContextElement {

    public enum DensityType{
        PEOPLE, CARS
    }

    private DensityType mType;
    private int mDensity;

    public DensityContext(int _id, String _key, DensityType _type, int _density) {
        super(_id, _key);
        mType = _type;
        mDensity = _density;
    }

    public DensityType getType() {
        return mType;
    }

    public int getDensity(){
        return mDensity;
    }
}
