package at.fhooe.mcm.components;

import at.fhooe.mcm.Mediator;
import at.fhooe.mcm.components.aal.AALController;
import at.fhooe.mcm.components.aal.AALModel;
import at.fhooe.mcm.components.aal.AALView;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IObserver;
import at.fhooe.mcm.interfaces.IUIView;
import at.fhooe.mcm.objects.Observable;

import java.awt.*;

/**
 * Ambient Assisted Living Component.
 * Reads ContextElements from XML files.
 * @author ifumi
 *
 */
public class AALComponent implements IComponent, IObserver {

    private Panel mView;
    private AALModel mModel;
    private Mediator mMediator;

    /**
     * Constructor.
     */
    public AALComponent() {

        mModel = new AALModel();
        AALController controller = new AALController(mModel);
        AALView view = new AALView(controller);

        controller.setView(view);

        mView = view.getView();
        mModel.addObserver(this, Observable.ObserverType.AAL);
    }

    /**
     * Getter for the AAL View.
     */
    @Override
    public Panel getView() {
        return mView;
    }

    /**
     * Getter for the component name.
     */
    @Override
    public String getName() {
        return "AALComponent";
    }

    /**
     * Init method setting the mediator.
     */
    @Override
    public void init(Mediator _mediator) {
        mMediator = _mediator;
    }

    /**
     * Setter for the AAL View.
     */
    @Override
    public void setUI(IUIView _view) {

    }

    /**
     * Update method. Passes objects straight through to the mediator which handles the rest.
     */
    @Override
    public void update(Object _o) {
        mMediator.update(_o);
    }
}
