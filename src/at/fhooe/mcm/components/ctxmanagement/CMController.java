package at.fhooe.mcm.components.ctxmanagement;

import java.awt.event.*;

import at.fhooe.mcm.components.CMComponent;
import at.fhooe.mcm.context.elements.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CMController implements ActionListener, ChangeListener {

    private CMModel mModel;
    private CMComponent mComponent;
    private CMView mView;

    public CMController(CMModel _model, CMComponent _comp) {
        mModel = _model;
        mComponent = _comp;
    }

    public void setView(CMView _view) {
        mView = _view;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        switch (_e.getActionCommand()) {
            case "thread":
                mComponent.togglePeriodicUpdate();
                break;
            case "setContext":

                String s;
                s = mView.getPositionTxt();
                if (!s.isEmpty())
                    mModel.setContextElement(new PositionContext(100, "position", PositionContext.PositionType.GAUSSKRUEGER, Integer.parseInt(s.split(",")[0]), Integer.parseInt(s.split(",")[1])));

                s = mView.getSpeedTxt();
                if (!s.isEmpty())
                    mModel.setContextElement(new SpeedContext(200, "speed", SpeedContext.SpeedType.KMH, Integer.parseInt(s)));

                s = mView.getTempTxt();
                if (!s.isEmpty())
                    mModel.setContextElement(new TemperatureContext(300, "temperature", TemperatureContext.TemperatureType.CELSIUS, Integer.parseInt(s)));

                s = mView.getTimeTxt();
                if (!s.isEmpty())
                    mModel.setContextElement(new TimeContext(400, "time", TimeContext.TimeType.H24, Integer.parseInt(s.split(":")[0]), Integer.parseInt(s.split(":")[1])));

                mModel.setContextElement(new WeatherContext(500, "weather", WeatherContext.WeatherType.values()[mView.getWeatherTypeIndex()], WeatherContext.WeatherValue.values()[mView.getWeatherValueIndex()]));
                mModel.setContextElement(new AirQualityContext(600, "air", AirQualityContext.AirQualityType.values()[mView.getAirQualityTypeIndex()], AirQualityContext.AirQualityValue.values()[mView.getAirQualityValueIndex()]));

                s = mView.getDensityTxt();
                if (!s.isEmpty())
                    mModel.setContextElement(new DensityContext(700, "density", DensityContext.DensityType.values()[mView.getDensityIndex()], Integer.parseInt(s)));

                mModel.setContextElement(new VehicleContext(800, "vehicle", VehicleContext.VehicleType.values()[mView.getVehicleTypeIndex()], VehicleContext.VehicleValue.values()[mView.getVehicleValueIndex()]));

                s = mView.getUVTxt();
                if (!s.isEmpty())
                    mModel.setContextElement(new UltravioletRadiationContext(900, "uv", UltravioletRadiationContext.UVType.OUTSIDE, Integer.parseInt(s)));
                break;
            default:
        }
    }

    @Override
    public void stateChanged(ChangeEvent _e) {
        if (_e.getSource() instanceof JSlider){
            mView.setFrequencyTxt(((JSlider) _e.getSource()).getValue());
            mComponent.getCMUpdateThread().setInterval(((JSlider) _e.getSource()).getValue());
        }
    }
}
