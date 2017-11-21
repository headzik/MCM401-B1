package at.fhooe.mcm.context.elements;

public class UltravioletRadiationContext extends ContextElement {

    public enum UVType{
        INSIDE, OUTSIDE
    }

    private UVType mType;
    private int mUltravioletRadition;

    public UltravioletRadiationContext(int _id, String _key, UVType _type, int _ultravioletRadition) {
        super(_id, _key);
        mType = _type;
        mUltravioletRadition = _ultravioletRadition;
    }

    public UVType getType() {
        return mType;
    }

    public int getUltravioletRadition() {
        return mUltravioletRadition;
    }
}
