package at.fhooe.mcm.components.ctxmanagement;

import javax.swing.*;

import at.fhooe.mcm.context.elements.*;

import java.awt.*;

public class CMView {
    private Panel mPanel;

    private TextField mPositionTxt, mSpeedTxt, mTempTxt, mTimeTxt, mDensityTxt, mUVTxt;
    private JComboBox mPostionCombo, mSpeedCombo, mTempCombo, mTimeCombo, mWeatherTypeCombo, mWeatherValueCombo, mAirQualityTypeCombo, mAirQualityValueCombo, mVehicleTypeCombo, mVehicleValueCombo, mDensityCombo, mUVCombo;
    private Label mFrequency;

    private Button mSetContextBtn, mThreadBtn;
    
    private Button mRecordSession, mStartSession, mStopSession;
    
    private JSlider mSlider;

    public CMView(CMController _controller) {
        // Initialise Layout
        mPanel = new Panel();
        mPanel.setBackground(Color.LIGHT_GRAY);
        mPanel.setLayout(new GridBagLayout());

        // Initialise components
        mPositionTxt = new TextField("", 20);
        mSpeedTxt = new TextField("", 20);
        mTempTxt = new TextField("", 20);
        mTimeTxt = new TextField("", 20);
        mPostionCombo = new JComboBox(PositionContext.PositionType.values());
        mSpeedCombo = new JComboBox(SpeedContext.SpeedType.values());
        mTempCombo = new JComboBox(TemperatureContext.TemperatureType.values());
        mTimeCombo = new JComboBox(TimeContext.TimeType.values());
        mWeatherTypeCombo = new JComboBox(WeatherContext.WeatherType.values());
        mWeatherValueCombo = new JComboBox(WeatherContext.WeatherValue.values());
        mAirQualityTypeCombo = new JComboBox(AirQualityContext.AirQualityType.values());
        mAirQualityValueCombo = new JComboBox(AirQualityContext.AirQualityValue.values());
        mVehicleTypeCombo = new JComboBox(VehicleContext.VehicleType.values());
        mVehicleValueCombo = new JComboBox(VehicleContext.VehicleValue.values());
        mDensityTxt = new TextField("", 20);
        mDensityCombo = new JComboBox(DensityContext.DensityType.values());
        mUVCombo = new JComboBox(UltravioletRadiationContext.UVType.values());
        mUVTxt = new TextField("", 20);


        mSetContextBtn = new Button("Set CTX");
        mSetContextBtn.setActionCommand("setContext");
        mSetContextBtn.addActionListener(_controller);
        
        mThreadBtn = new Button("Toggle CTX Broadcast");
        mThreadBtn.setActionCommand("thread");
        mThreadBtn.addActionListener(_controller);
        
        mRecordSession = new Button("Record Session");
        mStartSession = new Button ("Start Simulation");
        mStopSession = new Button ("Stop Simulation");
        
        mRecordSession.setActionCommand("record");
        mStartSession.setActionCommand("start");
        mStopSession.setActionCommand("stop");
        
        mRecordSession.addActionListener(_controller);
        mStartSession.addActionListener(_controller);
        mStopSession.addActionListener(_controller);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        mPanel.add(new Label("Current Context Situation:"), gbc);
        gbc.gridy++;


        gbc.gridx = 0;
        Panel positionPanel = new Panel(new FlowLayout());
        Label positionLabel = new Label("Position [Lat,Lng]:");
        positionPanel.add(positionLabel);
        positionPanel.add(mPositionTxt);
        positionPanel.add(mPostionCombo);
        mPanel.add(positionPanel, gbc);
        gbc.gridy++;

        Panel speedPanel = new Panel(new FlowLayout());
        Label speedLabel = new Label("Speed [number]:");
        speedPanel.add(speedLabel);
        speedPanel.add(mSpeedTxt);
        speedPanel.add(mSpeedCombo);
        mPanel.add(speedPanel, gbc);
        gbc.gridy++;

        Panel temperaturePanel = new Panel(new FlowLayout());
        Label temperatureLabel = new Label("Temperature [-/+ number]:");
        temperaturePanel.add(temperatureLabel);
        temperaturePanel.add(mTempTxt);
        temperaturePanel.add(mTempCombo);
        mPanel.add(temperaturePanel, gbc);
        gbc.gridy++;

        Panel timePanel = new Panel(new FlowLayout());
        Label timeLabel = new Label("Time [HH:MM]:");
        timePanel.add(timeLabel);
        timePanel.add(mTimeTxt);
        timePanel.add(mTimeCombo);
        mPanel.add(timePanel, gbc);
        gbc.gridy++;

        Panel weatherPanel = new Panel(new FlowLayout());
        Label weatherLabel = new Label("Weather:");
        weatherPanel.add(weatherLabel);
        weatherPanel.add(mWeatherTypeCombo);
        weatherPanel.add(mWeatherValueCombo);
        mPanel.add(weatherPanel, gbc);
        gbc.gridy++;

        Panel airPanel = new Panel(new FlowLayout());
        Label airLabel = new Label("Air quality:");
        airPanel.add(airLabel);
        airPanel.add(mAirQualityTypeCombo);
        airPanel.add(mAirQualityValueCombo);
        mPanel.add(airPanel, gbc);
        gbc.gridy++;

        Panel densityPanel = new Panel(new FlowLayout());
        Label densityLabel = new Label("Density [0-10]:");
        densityPanel.add(densityLabel);
        densityPanel.add(mDensityTxt);
        densityPanel.add(mDensityCombo);
        mPanel.add(densityPanel, gbc);
        gbc.gridy++;

        Panel vehiclePanel = new Panel(new FlowLayout());
        Label vehicleLabel = new Label("Vehicle:");
        vehiclePanel.add(vehicleLabel);
        vehiclePanel.add(mVehicleTypeCombo);
        vehiclePanel.add(mVehicleValueCombo);
        mPanel.add(vehiclePanel, gbc);
        gbc.gridy++;

        Panel uvPanel = new Panel(new FlowLayout());
        Label uvLabel = new Label("Ultraviolet Radiation [0-15]:");
        uvPanel.add(uvLabel);
        uvPanel.add(mUVTxt);
        uvPanel.add(mUVCombo);
        mPanel.add(uvPanel, gbc);
        gbc.gridy++;

        Panel frequencyPanel = new Panel(new FlowLayout());
        Label frequencyLabel = new Label("Update frequency [ms]:");
        mSlider = new JSlider(JSlider.HORIZONTAL, 100, 10000, 1000);
        mSlider.addChangeListener(_controller);
        mFrequency = new Label();
        mFrequency.setText("1000");
        frequencyPanel.add(frequencyLabel);
        frequencyPanel.add(mSlider);
        frequencyPanel.add(mFrequency);
        mPanel.add(frequencyPanel, gbc);
        gbc.gridy++;

        mThreadBtn = new Button("Toggle broadcast");
        mThreadBtn.addActionListener(_controller);
        mThreadBtn.setActionCommand("thread");
        mPanel.add(mThreadBtn, gbc);
        gbc.gridy++;

        mSetContextBtn = new Button("Set context");
        mSetContextBtn.setActionCommand("setContext");
        mSetContextBtn.addActionListener(_controller);
        mPanel.add(mSetContextBtn, gbc);
        gbc.gridy++;
        
        mPanel.add(mRecordSession, gbc);
        gbc.gridy++;
        mPanel.add(mStartSession, gbc);
        gbc.gridy++;
        mPanel.add(mStopSession, gbc);
        gbc.gridy++;
             
    }

    public void updateGUIElements(ContextSituation _cs) {
        if (_cs != null) {
            PositionContext posCtx = _cs.getPositionContext();
            TemperatureContext tempCtx = _cs.getTemperatureContext();
            TimeContext timeCtx = _cs.getTimeContext();
            SpeedContext speedCtx = _cs.getSpeedContext();
            
            // TODO Update UI
            DensityContext densityCtx = _cs.getDensityContext();
            UltravioletRadiationContext uvCTx = _cs.getUltravioletRadiationContext();
            VehicleContext vehicleCtx = _cs.getVehicleContext();
            WeatherContext weatherCtx = _cs.getWeatherContext();
            AirQualityContext airQualityCtx = _cs.getAirQualityContext();
            
            if (posCtx != null)
                mPositionTxt.setText(posCtx.toString());
            if (tempCtx != null)
                mTempTxt.setText(tempCtx.toString());
            if (timeCtx != null)
                mTimeTxt.setText(timeCtx.toString());
            if (speedCtx != null)
                mSpeedTxt.setText(speedCtx.toString());
        }
    }

    public void setFrequencyTxt(int _frequency) {
        mFrequency.setText(String.valueOf(_frequency));
    }

    public int getAirQualityValueIndex() {
        return mAirQualityValueCombo.getSelectedIndex();
    }

    public int getAirQualityTypeIndex() {
        return mAirQualityTypeCombo.getSelectedIndex();
    }

    public int getVehicleTypeIndex() {
        return mVehicleTypeCombo.getSelectedIndex();
    }

    public int getVehicleValueIndex() {
        return mVehicleValueCombo.getSelectedIndex();
    }

    public int getWeatherTypeIndex() {
        return mWeatherTypeCombo.getSelectedIndex();
    }

    public int getWeatherValueIndex() {
        return mWeatherValueCombo.getSelectedIndex();
    }

    public Label getFrequency() {
        return mFrequency;
    }

    public String getDensityTxt() {
        return mDensityTxt.getText();
    }

    public String getSpeedTxt() {
        return mSpeedTxt.getText();
    }

    public String getTempTxt() {
        return mTempTxt.getText();
    }

    public String getTimeTxt() {
        return mTimeTxt.getText();
    }

    public String getUVTxt() {
        return mUVTxt.getText();
    }

    public String getPositionTxt() {
        return mPositionTxt.getText();
    }

    public Panel getView() {
        return mPanel;
    }

    public int getDensityIndex() {
        return mDensityCombo.getSelectedIndex();
    }

	public JSlider getSlider() {
		return mSlider;
	}
}
