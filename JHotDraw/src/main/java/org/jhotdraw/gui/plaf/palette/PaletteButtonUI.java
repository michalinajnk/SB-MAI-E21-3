/**
 * @(#)PaletteButtonUI.java  1.0  Apr 6, 2008
 *
 * Copyright (c) 2008 by the original authors of JHotDraw
 * and all its contributors.
 * All rights reserved.
 *
 * The copyright of this software is owned by the authors and  
 * contributors of the JHotDraw project ("the copyright holders").  
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * the copyright holders. For details see accompanying license terms. 
 */
package org.jhotdraw.gui.plaf.palette;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;

/**
 * ButtonUI for palette components.
 *
 * @author Werner Randelshofer
 * @version 1.0 Apr 6, 2008 Created.
 */
public class PaletteButtonUI extends BasicButtonUI {
    // Shared UI object
    private static final PaletteButtonUI buttonUI = new PaletteButtonUI();


    private PaletteButtonUI() {}
    
    public static ComponentUI createUI() {
        return buttonUI;
    }
    // ********************************
    //          Create PLAF
    // ********************************
    
    @Override
    protected void installDefaults(AbstractButton b) {
        super.installDefaults(b);

        // load shared instance defaults
        //String pp = getPropertyPrefix();

        LookAndFeel.installProperty(b, "opaque", Boolean.FALSE);


        if (b.getMargin() == null || (b.getMargin() instanceof UIResource)) {
            b.setMargin(new InsetsUIResource(0, 0, 0, 0));
        }

        PaletteLookAndFeel.installColorsAndFont(b, getPropertyPrefix() + "background",
                getPropertyPrefix() + "foreground", getPropertyPrefix() + "font");
        PaletteLookAndFeel.installBorder(b, getPropertyPrefix() + "border");

        setRollover(b);
        
        b.setFocusable(false);
    }

    private void setRollover(AbstractButton b) {
        Object rollover = UIManager.get(getPropertyPrefix() + "rollover");
        if (rollover != null) {
            LookAndFeel.installProperty(b, "rolloverEnabled", rollover);
        }
    }

    //Paint Border responsible for painting a grid for component, a background of each chosen tool
    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton button = (AbstractButton) c;
        if (button.isBorderPainted() && (c.getBorder() instanceof BackdropBorder)) {
           BackdropBorder bb = (BackdropBorder) c.getBorder();
           bb.getBackdropBorder().paintBorder(c, g, 0, 0, c.getWidth(), c.getHeight());
        }
       super.paint(g, c);    //paint a proper image to a button
    }
}
