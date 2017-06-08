package org.usfirst.frc.team2706.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Selects top speed for robot
 */
public class SpeedSelector extends Subsystem {

    // Can't be static?
    private final Range[] voltages = {  new Range(0, 2.5), new Range(2.5, 2.75), new Range(2.75, 3.1),
                                        new Range(3.1, 3.5), new Range(3.5, 3.75), new Range(3.75, 3.95),
                                        new Range(3.95, 4.1), new Range(4.1, 4.2), new Range(4.2, 4.3),
                                        new Range(4.3, 4.4), new Range(4.4, 4.5), new Range(4.5, 4.6),
                                        new Range(4.6, 5)};
    private final AnalogInput selector;

    /**
     * Limits speed relative to how far selector goes
     */
    public SpeedSelector() {
        this.selector = new AnalogInput(0);
    }

    @Override
    protected void initDefaultCommand() {}

    /**
     * Gets the currently selected speed
     * 
     * @return The selected speed
     */
    public double getSpeedSelected() {
        int idx = getVoltageAsIndex();
    	//int idx = 12;

        if(idx == 0)
        	return 1.0;
        
        int flipped = 12 - idx;
        return 1.0 / 3.0 + (flipped * 2.0 / 33.0);
    }

    private int getVoltageAsIndex() {
        for (int i = 0; i < voltages.length; i++) {
            double voltage = selector.getAverageVoltage();
            if (voltage >= voltages[i].min && voltage < voltages[i].max) {
                return i;
            }
        }

        return 0;
    }

    /**
     * Logs selected number to SmartDashboard
     */
    public void log() {
        SmartDashboard.putNumber("Autonomous Selector", getVoltageAsIndex());
    }

    private class Range {

        public final double min, max;

        Range(double min, double max) {
            this.min = min;
            this.max = max;
        }
    }

}
