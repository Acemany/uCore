package ucore.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import ucore.entities.impl.EffectEntity;
import ucore.entities.trait.PosTrait;
import ucore.entities.trait.ScaleTrait;
import ucore.function.Consumer;
import ucore.function.EffectProvider;
import ucore.function.EffectRenderer;
import ucore.util.Mathf;
import ucore.util.Pooling;

public class Effects{
    private static final EffectContainer container = new EffectContainer();
    private static Array<Effect> effects = new Array<>();
    private static ScreenshakeProvider shakeProvider;
    private static float shakeFalloff = 1000f;
    private static EffectProvider provider = (effect, color, x, y, rotation, data) -> {
        EffectEntity entity = Pooling.obtain(EffectEntity.class, EffectEntity::new);
        entity.effect = effect;
        entity.color = color;
        entity.rotation = rotation;
        entity.data = data;
        entity.set(x, y);
        entity.add();
    };

    public static void setEffectProvider(EffectProvider prov){
        provider = prov;
    }

    public static void setScreenShakeProvider(ScreenshakeProvider provider){
        shakeProvider = provider;
    }

    public static void renderEffect(int id, Effect render, Color color, float life, float rotation, float x, float y, Object data){
        container.set(id, color, life, render.lifetime, rotation, x, y, data);
        render.draw.render(container);
    }

    public static Effect getEffect(int id){
        if(id >= effects.size || id < 0)
            throw new IllegalArgumentException("The effect with ID \"" + id + "\" does not exist!");
        return effects.get(id);
    }

    public static Array<Effect> all(){
        return effects;
    }

    public static void effect(Effect effect, float x, float y, float rotation){
        provider.createEffect(effect, Color.WHITE, x, y, rotation, null);
    }

    public static void effect(Effect effect, float x, float y){
        effect(effect, x, y, 0);
    }

    public static void effect(Effect effect, Color color, float x, float y){
        provider.createEffect(effect, color, x, y, 0f, null);
    }

    public static void effect(Effect effect, PosTrait loc){
        provider.createEffect(effect, Color.WHITE, loc.getX(), loc.getY(), 0f, null);
    }

    public static void effect(Effect effect, Color color, float x, float y, float rotation){
        provider.createEffect(effect, color, x, y, rotation, null);
    }

    public static void effect(Effect effect, Color color, float x, float y, float rotation, Object data){
        provider.createEffect(effect, color, x, y, rotation, data);
    }

    public static void effect(Effect effect, float x, float y, float rotation, Object data){
        provider.createEffect(effect, Color.WHITE, x, y, rotation, data);
    }

    public static void sound(String name){
        Sounds.play(name);
    }

    /** Plays a sound, with distance and falloff calulated relative to camera position. */
    public static void sound(String name, PosTrait position){
        sound(name, position.getX(), position.getY());
    }

    /** Plays a sound, with distance and falloff calulated relative to camera position. */
    public static void sound(String name, float x, float y){
        Sounds.playDistance(name, Vector2.dst(Core.camera.position.x, Core.camera.position.y, x, y));
    }

    /** Default value is 1000. Higher numbers mean more powerful shake (less falloff). */
    public static void setShakeFalloff(float falloff){
        shakeFalloff = falloff;
    }

    private static void shake(float intensity, float duration){
        if(shakeProvider == null)
            throw new RuntimeException("Screenshake provider is null! Set it first.");

        shakeProvider.accept(intensity, duration);
    }

    public static void shake(float intensity, float duration, float x, float y){
        float distance = Core.camera.position.dst(x, y, 0);
        if(distance < 1) distance = 1;

        shake(Mathf.clamp(1f / (distance * distance / shakeFalloff)) * intensity, duration);
    }

    public static void shake(float intensity, float duration, PosTrait loc){
        shake(intensity, duration, loc.getX(), loc.getY());
    }

    public interface ScreenshakeProvider{
        void accept(float intensity, float duration);
    }

    public static class Effect{
        private static int lastid = 0;
        public final int id;
        public final EffectRenderer draw;
        public final float lifetime;
        /** Clip size. */
        public float size;

        public Effect(float life, float clipsize, EffectRenderer draw){
            this.id = lastid++;
            this.lifetime = life;
            this.draw = draw;
            this.size = clipsize;
            effects.add(this);
        }

        public Effect(float life, EffectRenderer draw){
            this(life, 28f, draw);
        }
    }

    public static class EffectContainer implements ScaleTrait{
        public float x, y, time, lifetime, rotation;
        public Color color;
        public int id;
        public Object data;
        private EffectContainer innerContainer;

        public void set(int id, Color color, float life, float lifetime, float rotation, float x, float y, Object data){
            this.x = x;
            this.y = y;
            this.color = color;
            this.time = life;
            this.lifetime = lifetime;
            this.id = id;
            this.rotation = rotation;
            this.data = data;
        }

        public void scaled(float lifetime, Consumer<EffectContainer> cons){
            if(innerContainer == null) innerContainer = new EffectContainer();
            if(time <= lifetime){
                innerContainer.set(id, color, time, lifetime, rotation, x, y, data);
                cons.accept(innerContainer);
            }
        }

        @Override
        public float fin(){
            return time / lifetime;
        }
    }

}
