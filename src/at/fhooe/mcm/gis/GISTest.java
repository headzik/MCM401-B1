package at.fhooe.mcm.gis;

public class GISTest {

	public static void main(String[] args) {
		GISModel m = new GISModel();
		GISController c = new GISController(m);
		GISView v = new GISView(c);
		c.setView(v);
		m.addObserver(v);
	}

}
