package ucore.entities.trait;

import ucore.core.Timers;
import ucore.util.Mathf;

public interface TimeTrait extends ScaleTrait, Entity{

    float lifetime();

    void time(float time);

    float time();

    default void updateTime(){
        time(Mathf.clamp(time() + Timers.delta(), 0, lifetime()));

        if(time() >= lifetime()){
            remove();
        }
    }

    //fin() is not implemented due to compiler issues with iOS/RoboVM
}
