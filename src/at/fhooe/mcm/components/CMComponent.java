package at.fhooe.mcm.components;

import at.fhooe.mcm.Mediator;
import at.fhooe.mcm.contextelements.ContextElement;
import at.fhooe.mcm.contextelements.ContextSituation;
import at.fhooe.mcm.contextmanagement.CMController;
import at.fhooe.mcm.contextmanagement.CMModel;
import at.fhooe.mcm.contextmanagement.CMView;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IObserver;
import at.fhooe.mcm.objects.Observable;

import java.awt.*;

public class CMComponent implements IComponent, IObserver {

    private Panel mView;
    private Mediator mMediator;
    private CMModel mModel;

    public CMComponent(Mediator _mediator){
        mModel = new CMModel();
        CMController controller = new CMController(mModel);
        CMView view = new CMView(controller);

        mView = view.getView();
        mMediator = _mediator;
        mModel.addObserver(this, Observable.ObserverType.CM);
    }


    @Override
    public Panel getView() {
        return mView;
    }

    @Override
    public String getName() {
        return "Context Management";
    }

    @Override
    public void update(Object _o) {
        if (_o instanceof ContextElement){
            mModel.setContextElement((ContextElement)_o);
        }
    }
}
