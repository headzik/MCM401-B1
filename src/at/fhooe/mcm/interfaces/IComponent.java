package at.fhooe.mcm.interfaces;

import at.fhooe.mcm.Mediator;

import java.awt.Panel;

public interface IComponent {
	
	Panel getView();
	String getName();

    void init(Mediator mediator);
	void setUI(IUIView _view);
}
