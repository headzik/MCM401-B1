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

public class AALComponent implements IComponent, IObserver {

    private Panel mView;
    private AALModel mModel;
    private Mediator mMediator;

    public AALComponent() {

        mModel = new AALModel();
        AALController controller = new AALController(mModel);
        AALView view = new AALView(controller);

        controller.setView(view);

        mView = view.getView();
        mModel.addObserver(this, Observable.ObserverType.AAL);
    }

    @Override
    public Panel getView() {
        return mView;
    }

    @Override
    public String getName() {
        return "AALComponent";
    }

    @Override
    public void init(Mediator _mediator) {
        mMediator = _mediator;
    }

    @Override
    public void setUI(IUIView _view) {

    }

    @Override
    public void update(Object _o) {
        mMediator.update(_o);
    }
}
