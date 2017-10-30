package at.fhooe.mcm.interfaces;

import at.fhooe.mcm.gps.NMEAInfo;

public interface ISatInfo {
    void update(NMEAInfo _info);
}
