package ucore.entities.impl;

import com.badlogic.gdx.utils.Pool.Poolable;
import ucore.entities.trait.ScaleTrait;
import ucore.entities.trait.TimeTrait;

public abstract class TimedEntity extends BaseEntity implements ScaleTrait, TimeTrait, Poolable{
    public float time;

    @Override
    public void time(float time){
        this.time = time;
    }

    @Override
    public float time(){
        return time;
    }

    @Override
    public void update(){
        updateTime();
    }

    @Override
    public void reset(){
        time = 0f;
    }

    @Override
    public float fin(){
        return time() / lifetime();
    }
}
