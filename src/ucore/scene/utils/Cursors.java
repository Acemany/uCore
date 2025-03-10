package ucore.scene.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ObjectMap;
import ucore.graphics.Pixmaps;

public class Cursors{
    public static Color outlineColor = new Color(0, 0, 0, 0.1f);
    public static int cursorScaling = 4;

    public static Cursor arrow;
    public static Cursor ibeam;
    public static Cursor hand;

    private static ObjectMap<String, Cursor> customCursors = new ObjectMap<>();

    public static Cursor loadCursor(String name){
        Pixmap pixmap = new Pixmap(Gdx.files.internal("cursors/" + name + ".png"));
        Pixmap out = Pixmaps.outline(pixmap, outlineColor);
        out.setColor(Color.WHITE);
        Pixmap out2 = Pixmaps.scale(out, cursorScaling);

        if(!MathUtils.isPowerOfTwo(out2.getWidth())){
            Pixmap old = out2;
            out2 = Pixmaps.resize(out2, MathUtils.nextPowerOfTwo(out2.getWidth()), MathUtils.nextPowerOfTwo(out2.getWidth()));
            old.dispose();
        }

        out.dispose();
        pixmap.dispose();

        return Gdx.graphics.newCursor(out2, out2.getWidth() / 2, out2.getHeight() / 2);
    }

    public static void loadCustom(String name){
        customCursors.put(name, loadCursor(name));
    }

    public static void set(String cursorName){
        if(!customCursors.containsKey(cursorName))
            throw new IllegalArgumentException("No cursor with name '" + cursorName + "' exists!");
        Gdx.graphics.setCursor(customCursors.get(cursorName));
    }

    public static void setIbeam(){
        if(ibeam != null)
            Gdx.graphics.setCursor(ibeam);
        else
            Gdx.graphics.setSystemCursor(SystemCursor.Ibeam);
    }

    public static void setHand(){
        if(hand != null)
            Gdx.graphics.setCursor(hand);
        else
            Gdx.graphics.setSystemCursor(SystemCursor.Hand);
    }

    public static void restoreCursor(){
        if(arrow != null){
            Gdx.graphics.setCursor(arrow);
        }else{
            Gdx.graphics.setSystemCursor(SystemCursor.Arrow);
        }
    }

    public static void dispose(){
        if(arrow != null) arrow.dispose();
        if(ibeam != null) ibeam.dispose();
        if(hand != null) hand.dispose();

        if(customCursors != null){
            for(Cursor cursor : customCursors.values()){
                if(cursor != null){
                    cursor.dispose();
                }
            }
        }

        customCursors = new ObjectMap<>();
        arrow = ibeam = hand = null;
    }
}
