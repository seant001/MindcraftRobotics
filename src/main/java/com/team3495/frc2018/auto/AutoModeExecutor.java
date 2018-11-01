package com.team3495.frc2018.auto;

import com.team1323.lib.util.CrashTrackingRunnable;
import com.team3495.frc2018.auto.modes.AutoModeBase;

/**
 * This class selects, runs, and stops (if necessary) (maybe) a specified autonomous mode.
 */
public class AutoModeExecutor {
    private AutoModeBase auto_mode;
    private Thread thread = null;

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
