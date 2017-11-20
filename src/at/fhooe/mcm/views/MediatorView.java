package at.fhooe.mcm.views;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JTabbedPane;

import at.fhooe.mcm.interfaces.IComponent;

/**
 * MainView of the application holding all tabs.
 */
public class MediatorView extends Frame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane mJTabbedPane;

	/**
	 * Constructor of the MediatorView.
	 */
	public MediatorView() {
		
		// Add listener
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				System.exit(0);
			}
		});
		
		// Set layout and size
		this.setLayout(new BorderLayout());
		this.setSize(1200	, 800);

		// Add tab panel
		mJTabbedPane = new JTabbedPane();
		this.add(mJTabbedPane, BorderLayout.CENTER);

		this.setLocationRelativeTo(null);
		
		// Set visible
		this.setVisible(true);			
	}
	
	/**
	 * Adds all tabs to the view for the passed list of components.
	 * @param _components
	 */
	public void addTabs(List<IComponent> _components) {
		for (IComponent comp : _components) {
			mJTabbedPane.add(comp.getName(), comp.getView());
		}
	}

}
