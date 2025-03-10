package ucore.io;

import java.io.DataInput;
import java.nio.ByteBuffer;

/** DataInput wrapper of ByteBuffer. */
public class ByteBufferInput implements DataInput{
    private ByteBuffer buffer;

    /** Wraps the specified ByteBuffer. */
    public ByteBufferInput(ByteBuffer buffer){
        this.buffer = buffer;
    }

    /** {@link #setBuffer} must be called before this object can be used. */
    public ByteBufferInput(){
    }

    public void setBuffer(ByteBuffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void readFully(byte[] bytes){
        throw new RuntimeException("Stub!");
    }

    @Override
    public void readFully(byte[] bytes, int i, int i1){
        throw new RuntimeException("Stub!");
    }

    @Override
    public int skipBytes(int i){
        buffer.position(buffer.position() + i);
        return i;
    }

    @Override
    public boolean readBoolean(){
        return buffer.get() == 1;
    }

    @Override
    public byte readByte(){
        return buffer.get();
    }

    @Override
    public int readUnsignedByte(){
        return buffer.get() + 128;
    }

    @Override
    public short readShort(){
        return buffer.getShort();
    }

    @Override
    public int readUnsignedShort(){
        return buffer.getShort() + -((int) Short.MIN_VALUE);
    }

    @Override
    public char readChar(){
        return buffer.getChar();
    }

    @Override
    public int readInt(){
        return buffer.getInt();
    }

    @Override
    public long readLong(){
        return buffer.getLong();
    }

    @Override
    public float readFloat(){
        return buffer.getFloat();
    }

    @Override
    public double readDouble(){
        return buffer.getDouble();
    }

    @Override
    public String readLine(){
        throw new RuntimeException("Stub!");
    }

    @Override
    public String readUTF(){
        short length = buffer.getShort();
        byte[] bytes = new byte[length];
        buffer.get(bytes);
        return new String(bytes);
    }
}
