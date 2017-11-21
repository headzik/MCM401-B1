package at.fhooe.mcm.interfaces;

import at.fhooe.mcm.components.gps.NMEAInfo;

public interface IPositionUpdateListener {
	void updateSats(NMEAInfo _mInfo);
}
