package com.kaede.view;

import com.kaede.controller.KeyHandler;
import com.kaede.controller.Logger;
import com.kaede.controller.ResLoader;
import com.kaede.controller.sceneHandler.ScnHandlerMaster;
import com.kaede.model.Global;
import com.kaede.model.SceneStack;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GamePanel extends JPanel
{
    private KeyHandler keyH;
    private ScnHandlerMaster scnH;
    private BufferedImage bgi;

    private boolean isBgiChanged;

    GamePanel()
    {
        keyH = new KeyHandler();
        scnH = new ScnHandlerMaster(this);
        SceneStack.init();
        isBgiChanged = false;

        this.setDoubleBuffered(true);
        this.setFocusable(true);
        
        this.addKeyListener(keyH);

        Logger.log("@GamePanel: GamePanel is ready.");
    }

    public KeyHandler getKeyH() { return keyH; }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        paintBgi(g);
    }

    public void changeBgi(String bgiName)
    {
        Global.bgiName = bgiName;
        isBgiChanged = true;
        Logger.log("@GamePanel: Change backgound image to [" + bgiName + "].");
    }

    private void paintBgi(Graphics g)
    {
        if (isBgiChanged)
        {
            try
            {
                bgi = ResLoader.getImg(Global.bgiName);
                isBgiChanged = false;
            }
            catch (IllegalArgumentException e)
            {
                Logger.error("@GamePanel", e);
                changeBgi("null");
                bgi = ResLoader.getImg("null");
            }
        }

        if (bgi != null)
        {
            int bgiX, bgiY;
            int bgiW = bgi.getWidth();
            int bgiH = bgi.getHeight();
            int panelW = this.getWidth();
            int panelH = this.getHeight();
            double scale = Math.max((double)panelW / (double)bgiW,
                                    (double)panelH / (double)bgiH);
                                    
            bgiW *= scale;
            bgiH *= scale;
            bgiX = (panelW - bgiW) / 2;
            bgiY = (panelH - bgiH) / 2;

            g.drawImage(bgi, bgiX, bgiY, bgiW, bgiH, this);
        }
    }

    public void _draw(double delta)
    {
        scnH.handle();
        this.repaint();
    }
}
