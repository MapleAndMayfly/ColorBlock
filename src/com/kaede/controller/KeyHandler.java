package com.kaede.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.kaede.model.Global;

public class KeyHandler implements KeyListener
{
    public boolean EscDown = false;

    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * 按键被按下 - key down
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        int kc = e.getKeyCode();

        switch (kc)
        {
            //*
            case KeyEvent.VK_F8:        // 仅调试用 - for debugging only
                Logger.log("@KeyHandler: F8 pressed, system exit.");
                Global.save();
                System.exit(0);  //*/

            case KeyEvent.VK_ESCAPE:
                EscDown = true;
                break;

            default:
                break;
        }
    }

    /**
     * 按键被放开 - key up
     */
    @Override
    public void keyReleased(KeyEvent e)
    {
        int kc = e.getKeyCode();

        switch (kc)
        {
            case KeyEvent.VK_ESCAPE:
                EscDown = false;
                break;

            default:
                break;
        }
    }
}
