package at.fhooe.mcm.interfaces;

import at.fhooe.mcm.components.gps.NMEAInfo;

public interface ISatInfo {
    void update(NMEAInfo _info);
}
