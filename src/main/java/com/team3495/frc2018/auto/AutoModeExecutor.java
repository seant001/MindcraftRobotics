package com.team3495.frc2018.auto;

import com.team1323.lib.util.CrashTrackingRunnable;
import com.team3495.frc2018.auto.modes.AutoModeBase;
import com.team3495.frc2018.auto.modes.StartLeftSwitchLeft;
import com.team3495.frc2018.auto.modes.StartRightSwitchRight;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class selects, runs, and stops (if necessary) (maybe) a specified autonomous mode.
 */
public class AutoModeExecutor {
    private AutoModeBase auto_mode;
    private Thread thread = null;
    private String selected_auto;
    private String selected_pos;
    private final SendableChooser<String> starting_pos = new SendableChooser<>();
    private final SendableChooser<String> auto_mode_string = new SendableChooser<>();
    
    private AutoModeExecutor()
    {
        starting_pos.addDefault("Left", "Left");
        starting_pos.addObject("Center", "Center");
        starting_pos.addObject("Right", "Right");
        auto_mode_string.addDefault("Near Switch", "Near Switch");
        SmartDashboard.putData("Starting Position", starting_pos);
        SmartDashboard.putData("Auto Mode", auto_mode_string);
    }

    private static AutoModeExecutor instance = null;
    public static AutoModeExecutor getInstance()
    {
        if(instance == null)
        {
            instance = new AutoModeExecutor();
        }
        return instance;
    }

    public void selectAutoMode()
    {
        String gameData = DriverStation.getInstance().getGameSpecificMessage();
        char switch_side = gameData.charAt(0);
        char scale_side = gameData.charAt(1);
        selected_auto = auto_mode_string.getSelected();
        selected_pos = starting_pos.getSelected();
        // autoSelected = SmartDashboard.getString("Auto Selector",
        // defaultAuto);
        System.out.println("Position Selected: " + selected_pos);
        System.out.println("Auto Selected: " + selected_auto);
        switch(selected_auto)
        {
            case "Near Switch":
            switch(selected_pos)
            {
                case "Left":
                setAutoMode(new StartLeftSwitchLeft(switch_side == 'L'));
                break;
                case "Right":
                setAutoMode(new StartRightSwitchRight(switch_side == 'R'));
                break;
                case "Center":
                break;
                default:
                break;
            }
            break;
            default:
            break;
        }
    }

    public void setAutoMode(AutoModeBase auto_mode) {
        this.auto_mode = auto_mode;

    }


    public void start() {
        if(thread == null) {
            thread = new Thread(new CrashTrackingRunnable() {
                @Override
                public void runCrashTracked() {
                    if(auto_mode != null) {
                        auto_mode.run();
                    }
                }
            });
            thread.start();
        }
    }

    public void stop() {
        if(auto_mode != null) {
            auto_mode.stop();
        }
    }
}
