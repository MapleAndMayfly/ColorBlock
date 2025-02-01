package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import model.Global;
import model.ResLoader;

public class GamePanel extends JPanel
{
    GamePanel()
    {
        this.setDoubleBuffered(true);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        applyBgi(g);
    }

    private void applyBgi(Graphics g)
    {
        BufferedImage bgi = ResLoader.getImg(Global.bgi);

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
}
