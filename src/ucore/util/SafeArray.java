package ucore.util;

import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class SafeArray<T> extends Array<T>{
    @Override
    public ArrayIterator<T> iterator(){
        return new ArrayIterator<>(this);
    }
}
