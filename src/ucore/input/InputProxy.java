package ucore.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputProxy implements Input{
    protected final Input input;

    public InputProxy(Input input){
        this.input = input;
    }

    @Override
    public float getAccelerometerX(){
        return input.getAccelerometerX();
    }

    @Override
    public float getAccelerometerY(){
        return input.getAccelerometerY();
    }

    @Override
    public float getAccelerometerZ(){
        return input.getAccelerometerZ();
    }

    @Override
    public float getGyroscopeX(){
        return input.getGyroscopeX();
    }

    @Override
    public float getGyroscopeY(){
        return input.getGyroscopeY();
    }

    @Override
    public float getGyroscopeZ(){
        return input.getGyroscopeZ();
    }

    @Override
    public int getMaxPointers(){
        return input.getMaxPointers();
    }

    @Override
    public int getX(){
        return input.getX();
    }

    @Override
    public int getX(int pointer){
        return input.getX(pointer);
    }

    @Override
    public int getDeltaX(){
        return input.getDeltaX();
    }

    @Override
    public int getDeltaX(int pointer){
        return input.getDeltaX(pointer);
    }

    @Override
    public int getY(){
        return input.getY();
    }

    @Override
    public int getY(int pointer){
        return input.getY(pointer);
    }

    @Override
    public int getDeltaY(){
        return input.getDeltaY();
    }

    @Override
    public int getDeltaY(int pointer){
        return input.getDeltaY(pointer);
    }

    @Override
    public boolean isTouched(){
        return input.isTouched();
    }

    @Override
    public boolean justTouched(){
        return input.justTouched();
    }

    @Override
    public boolean isTouched(int pointer){
        return input.isTouched(pointer);
    }

    @Override
    public boolean isButtonPressed(int button){
        return input.isButtonPressed(button);
    }

    @Override
    public boolean isButtonJustPressed(int button){
        return input.isButtonJustPressed(button);
    }

    @Override
    public boolean isKeyPressed(int key){
        return input.isKeyPressed(key);
    }

    @Override
    public boolean isKeyJustPressed(int key){
        return input.isKeyJustPressed(key);
    }

    @Override
    public void getTextInput(TextInputListener listener, String title, String text, String hint){
        input.getTextInput(listener, title, text, hint);
    }

    @Override
    public void getTextInput(TextInputListener listener, String title, String text, String hint, OnscreenKeyboardType type){
        input.getTextInput(listener, title, text, hint, type);
    }

    @Override
    public void setOnscreenKeyboardVisible(boolean visible){
        input.setOnscreenKeyboardVisible(visible);
    }

    @Override
    public void setOnscreenKeyboardVisible(boolean visible, OnscreenKeyboardType type){
        input.setOnscreenKeyboardVisible(visible, type);
    }

    @Override
    public void vibrate(int milliseconds){
        input.vibrate(milliseconds);
    }

    @Override
    public void vibrate(long[] pattern, int repeat){
        input.vibrate(pattern, repeat);
    }

    @Override
    public void cancelVibrate(){
        input.cancelVibrate();
    }

    @Override
    public float getAzimuth(){
        return input.getAzimuth();
    }

    @Override
    public float getPitch(){
        return input.getPitch();
    }

    @Override
    public float getRoll(){
        return input.getRoll();
    }

    @Override
    public void getRotationMatrix(float[] matrix){
        input.getRotationMatrix(matrix);
    }

    @Override
    public long getCurrentEventTime(){
        return input.getCurrentEventTime();
    }

    @Override
    public boolean isCatchBackKey(){
        return input.isCatchKey(Keys.BACK);
    }

    @Override
    public void setCatchBackKey(boolean catchBack){
        input.setCatchKey(Keys.BACK, catchBack);
    }

    @Override
    public boolean isCatchMenuKey(){
        return input.isCatchKey(Keys.MENU);
    }

    @Override
    public void setCatchKey(int key, boolean catchKey){
        input.setCatchKey(key, catchKey);
    }

    @Override
    public boolean isCatchKey(int key){
        return input.isCatchKey(key);
    }

    @Override
    public void setCatchMenuKey(boolean catchMenu){
        input.setCatchKey(Keys.MENU, catchMenu);
    }

    @Override
    public InputProcessor getInputProcessor(){
        return input.getInputProcessor();
    }

    @Override
    public void setInputProcessor(InputProcessor processor){
        input.setInputProcessor(processor);
    }

    @Override
    public boolean isPeripheralAvailable(Peripheral peripheral){
        return input.isPeripheralAvailable(peripheral);
    }

    @Override
    public int getRotation(){
        return input.getRotation();
    }

    @Override
    public Orientation getNativeOrientation(){
        return input.getNativeOrientation();
    }

    @Override
    public boolean isCursorCatched(){
        return input.isCursorCatched();
    }

    @Override
    public void setCursorCatched(boolean catched){
        input.setCursorCatched(catched);
    }

    @Override
    public void setCursorPosition(int x, int y){
        input.setCursorPosition(x, y);
    }

    @Override
    public float getPressure(){
        return 0;
    }

    @Override
    public float getPressure(int pointer){
        return 0;
    }
}
