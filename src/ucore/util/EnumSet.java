package ucore.util;

import java.util.Iterator;

public class EnumSet<T extends Enum<T>> implements Iterable<T>{
    private int i;
    private T[] set;

    private EnumSet(){
    }

    public static <T extends Enum<T>> EnumSet<T> of(T... arr){
        EnumSet<T> set = new EnumSet<>();
        set.set = arr;
        for(T t : arr){
            set.i |= (1 << t.ordinal());
        }
        return set;
    }

    public boolean contains(T t){
        return (i & (1 << t.ordinal())) != 0;
    }

    public int size(){
        return set.length;
    }

    @Override
    public Iterator<T> iterator(){
        return new EnumSetIterator();
    }

    class EnumSetIterator implements Iterator<T>{
        int index = 0;

        @Override
        public boolean hasNext(){
            return index < set.length;
        }

        @Override
        public T next(){
            return set[index++];
        }
    }
}
