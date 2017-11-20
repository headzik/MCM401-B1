package at.fhooe.mcm.components;

import at.fhooe.mcm.Mediator;
import at.fhooe.mcm.contextmanagement.CMController;
import at.fhooe.mcm.contextmanagement.CMModel;
import at.fhooe.mcm.contextmanagement.CMView;
import at.fhooe.mcm.interfaces.IComponent;
import at.fhooe.mcm.interfaces.IObserver;

import java.awt.*;

public class CMComponent implements IComponent, IObserver {

    private Panel mView;
    private Mediator mMediator;

    public CMComponent(Mediator _mediator){
        CMModel model = new CMModel();
        CMController controller = new CMController(model);
        CMView view = new CMView(controller);

        mView = view.getView();
        mMediator = _mediator;
        model.addObserver(this);
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
        mMediator.update(_o);
    }
}
