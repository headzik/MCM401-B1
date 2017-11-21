package at.fhooe.mcm.components.ctxmanagement;

import javax.swing.*;

import at.fhooe.mcm.context.elements.ContextSituation;
import at.fhooe.mcm.context.elements.PositionContext;
import at.fhooe.mcm.context.elements.SpeedContext;
import at.fhooe.mcm.context.elements.TemperatureContext;
import at.fhooe.mcm.context.elements.TimeContext;

import java.awt.*;

public class CMView {
    private Panel mPanel;
    
    private TextField mPositionTxt, mSpeedTxt, mTempTxt, mTimeTxt;
    
    private Button mSetContextBtn, mThreadBtn;
   
    public CMView(CMController _controller) {
    	// Initialise Layout
        mPanel = new Panel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Initialise components
        mPositionTxt = new TextField("---");
        mSpeedTxt = new TextField("---");
        mTempTxt = new TextField("---");
        mTimeTxt = new TextField("---");
        
        mSetContextBtn = new Button("Set CTX");
        mSetContextBtn.setActionCommand("setContext");
        mSetContextBtn.addActionListener(_controller);
        mThreadBtn = new Button("Toggle CTX Broadcast");
        mThreadBtn.setActionCommand("thread");
        mThreadBtn.addActionListener(_controller);
              
        // Add title labels
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        mPanel.add(new Label("Current Context Situation"), gbc);    
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        mPanel.add(new Label("Position: "), gbc);    
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        mPanel.add(new Label("Speed: "), gbc);  
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        mPanel.add(new Label("Temperature: "), gbc);    
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        mPanel.add(new Label("Time: "), gbc);    
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        mPanel.add(mPositionTxt, gbc);    
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        mPanel.add(mSpeedTxt, gbc);  
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        mPanel.add(mTempTxt, gbc);    
        
        gbc.gridx = 1;
        gbc.gridy = 5;
        mPanel.add(mTimeTxt, gbc);     
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        mPanel.add(mThreadBtn, gbc); 
        
        gbc.gridx = 1;
        gbc.gridy = 6;
        mPanel.add(mSetContextBtn, gbc); 
       

        /*
        
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mPanel.add(new Label("Change Context Situation"), gbc);
        

        
        */
        
        
        
        /*
        


        // setup time panel
        Panel timePanel = new Panel(new FlowLayout());
        String[] hoursStrings = new String[24];
        for (int i = 0; i < hoursStrings.length; i++) {
            hoursStrings[i] = String.valueOf(i);
        }
        String[] minutesString = new String[60];
        for (int i = 0; i < minutesString.length; i++) {
            minutesString[i] = String.valueOf(i);
        }
        JComboBox hours = new JComboBox(hoursStrings);
        JComboBox minutes = new JComboBox(minutesString);
        Label hoursLabel = new Label("H");
        Label minutesLabel = new Label("M");
        Button setTime = new Button("Set Time");
        setTime.setActionCommand("time");

        timePanel.add(hours);
        timePanel.add(hoursLabel);
        timePanel.add(minutes);
        timePanel.add(minutesLabel);
        timePanel.add(setTime);

        // setup time panel

        mPanel.add(timePanel);
        */

    }
    
    public void updateLabels(ContextSituation _cs) {
    	if (_cs != null) {
    		PositionContext posCtx = _cs.getPositionContext();
    		TemperatureContext tempCtx = _cs.getTemperatureContext();
    		TimeContext timeCtx = _cs.getTimeContext();
    		SpeedContext speedCtx = _cs.getSpeedContext();
    		
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

    public Panel getView() {
        return mPanel;
    }
}
