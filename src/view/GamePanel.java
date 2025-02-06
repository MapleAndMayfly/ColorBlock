package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import controller.KeyHandler;
import controller.ScnHandlerMaster;
import model.Global;
import model.ResLoader;

public class GamePanel extends JPanel
{
    private KeyHandler keyH;
    private ScnHandlerMaster scnH;
    private BufferedImage bgi;

    private boolean isBgiChanged;
    
    public static enum scene
    {
        TITLE,
        POPUP
    }
    public ArrayList<scene> curScene = new ArrayList<>();

    GamePanel()
    {
        keyH = new KeyHandler();
        scnH = new ScnHandlerMaster();
        curScene.add(scene.TITLE);
        isBgiChanged = false;

        this.setDoubleBuffered(true);
        this.setFocusable(true);
        
        this.addKeyListener(keyH);
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
    }

    private void paintBgi(Graphics g)
    {
        if (isBgiChanged)
        {
            bgi = ResLoader.getImg(Global.bgiName);
            if (bgi != null) isBgiChanged = false;
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
        scnH.handle(this);
        this.repaint();
    }
}
