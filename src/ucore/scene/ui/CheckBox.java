/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
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

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import ucore.scene.style.Drawable;
import ucore.scene.style.SkinReader.ReadContext;
import ucore.scene.ui.layout.Cell;

import static ucore.core.Core.skin;

/**
 * A checkbox is a button that contains an image indicating the checked or unchecked state and a label.
 *
 * @author Nathan Sweet
 */
public class CheckBox extends TextButton{
    private Image image;
    private Cell imageCell;
    private CheckBoxStyle style;

    public CheckBox(String text){
        this(text, skin.get(CheckBoxStyle.class));
    }

    public CheckBox(String text, String styleName){
        this(text, skin.get(styleName, CheckBoxStyle.class));
    }

    public CheckBox(String text, CheckBoxStyle style){
        super(text, style);
        clearChildren();
        Label label = getLabel();
        imageCell = add(image = new Image(style.checkboxOff, Scaling.stretch));
        add(label).padLeft(4).get().setWrap(false);
        label.setAlignment(Align.left);
        setSize(getPrefWidth(), getPrefHeight());
    }

    /**
     * Returns the checkbox's style. Modifying the returned style may not have an effect until {@link #setStyle(ButtonStyle)} is
     * called.
     */
    public CheckBoxStyle getStyle(){
        return style;
    }

    public void setStyle(ButtonStyle style){
        if(!(style instanceof CheckBoxStyle)) throw new IllegalArgumentException("style must be a CheckBoxStyle.");
        super.setStyle(style);
        this.style = (CheckBoxStyle) style;
    }

    public void draw(Batch batch, float parentAlpha){
        Drawable checkbox = null;
        if(isDisabled()){
            if(isChecked && style.checkboxOnDisabled != null)
                checkbox = style.checkboxOnDisabled;
            else
                checkbox = style.checkboxOffDisabled;
        }
        if(checkbox == null){
            if(isChecked && isOver() && style.checkboxOnOver != null)
                checkbox = style.checkboxOnOver;
            else if(isChecked && style.checkboxOn != null)
                checkbox = style.checkboxOn;
            else if(isOver() && style.checkboxOver != null && !isDisabled())
                checkbox = style.checkboxOver;
            else
                checkbox = style.checkboxOff;
        }
        image.setDrawable(checkbox);
        super.draw(batch, parentAlpha);
    }

    public Image getImage(){
        return image;
    }

    public Cell getImageCell(){
        return imageCell;
    }

    /**
     * The style for a select box, see {@link CheckBox}.
     *
     * @author Nathan Sweet
     */
    static public class CheckBoxStyle extends TextButtonStyle{
        public Drawable checkboxOn, checkboxOff;
        /** Optional. */
        public Drawable checkboxOver, checkboxOnDisabled, checkboxOffDisabled, checkboxOnOver;

        @Override
        public void read(ReadContext read){
            super.read(read);
            checkboxOn = read.rdraw("checkboxOn");
            checkboxOff = read.rdraw("checkboxOff");

            checkboxOver = read.draw("checkboxOver");
            checkboxOnDisabled = read.draw("checkboxOnDisabled");
            checkboxOffDisabled = read.draw("checkboxOffDisabled");
            checkboxOnOver = read.draw("checkboxOnOver");
        }
    }
}
