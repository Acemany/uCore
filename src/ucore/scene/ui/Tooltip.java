/*******************************************************************************
 * Copyright 2015 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package ucore.scene.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import ucore.scene.Element;
import ucore.scene.Scene;
import ucore.scene.event.InputEvent;
import ucore.scene.event.InputListener;
import ucore.scene.event.Touchable;
import ucore.scene.ui.layout.Container;

/**
 * A listener that shows a tooltip actor when another actor is hovered over with the mouse.
 *
 * @author Nathan Sweet
 */
public class Tooltip<T extends Element> extends InputListener{
    static Vector2 tmp = new Vector2();

    protected final TooltipManager manager;
    final Container<T> container;
    boolean instant = true, always;
    Element targetActor;
    Runnable show;

    /** @param contents May be null. */
    public Tooltip(T contents){
        this(contents, TooltipManager.getInstance());
    }

    public Tooltip(T contents, Runnable show){
        this(contents, TooltipManager.getInstance());
        this.show = show;
    }

    /** @param contents May be null. */
    public Tooltip(T contents, TooltipManager manager){
        this.manager = manager;

        container = new Container<T>(contents){
            public void act(float delta){
                super.act(delta);
                if(targetActor != null && targetActor.getScene() == null) remove();
            }
        };
        container.setTouchable(Touchable.disabled);
    }

    public TooltipManager getManager(){
        return manager;
    }

    public Container<T> getContainer(){
        return container;
    }

    public T getActor(){
        return container.getActor();
    }

    public void setActor(T contents){
        container.setActor(contents);
    }

    /** If true, this tooltip is shown without delay when hovered. */
    public void setInstant(boolean instant){
        this.instant = instant;
    }

    /** If true, this tooltip is shown even when tooltips are not {@link TooltipManager#enabled}. */
    public void setAlways(boolean always){
        this.always = always;
    }

    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
        if(instant){
            container.toFront();
            return false;
        }
        manager.touchDown(this);
        return false;
    }

    public boolean mouseMoved(InputEvent event, float x, float y){
        if(container.hasParent()) return false;
        setContainerPosition(event.getListenerActor(), x, y);
        return true;
    }

    protected void setContainerPosition(Element actor, float x, float y){
        this.targetActor = actor;
        Scene stage = actor.getScene();
        if(stage == null) return;

        container.pack();
        float offsetX = manager.offsetX, offsetY = manager.offsetY, dist = manager.edgeDistance;
        Vector2 point = actor.localToStageCoordinates(tmp.set(x + offsetX, y - offsetY - container.getHeight()));
        if(point.y < dist) point = actor.localToStageCoordinates(tmp.set(x + offsetX, y + offsetY));
        if(point.x < dist) point.x = dist;
        if(point.x + container.getWidth() > stage.getWidth() - dist)
            point.x = stage.getWidth() - dist - container.getWidth();
        if(point.y + container.getHeight() > stage.getHeight() - dist)
            point.y = stage.getHeight() - dist - container.getHeight();
        container.setPosition(point.x, point.y);

        point = actor.localToStageCoordinates(tmp.set(actor.getWidth() / 2, actor.getHeight() / 2));
        point.sub(container.getX(), container.getY());
        container.setOrigin(point.x, point.y);
    }

    public void enter(InputEvent event, float x, float y, int pointer, Element fromActor){
        if(pointer != -1) return;
        if(Gdx.input.isTouched()) return;
        Element actor = event.getListenerActor();
        if(fromActor != null && fromActor.isDescendantOf(actor)) return;
        setContainerPosition(actor, x, y);
        manager.enter(this);

        if(show != null)
            show.run();
    }

    public void exit(InputEvent event, float x, float y, int pointer, Element toActor){
        if(toActor != null && toActor.isDescendantOf(event.getListenerActor())) return;
        hide();
    }

    public void hide(){
        manager.hide(this);
    }
}
