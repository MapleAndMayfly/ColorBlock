package view;

import javax.swing.JFrame;

import controller.KeyHandler;
import controller.ScnHandlerMaster;
import model.Global;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dimension;

public class MainWindow extends JFrame
{
    public static enum scene
    {
        TITLE,
        POPUP
    }
    public scene curScene;

    private GamePanel gPanel;
    private KeyHandler keyH;
    private ScnHandlerMaster scnH;
    private Thread renderThread;
    private Thread processThread;

    MainWindow()
    {
        gPanel = new GamePanel();
        keyH = new KeyHandler();
        scnH = new ScnHandlerMaster();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(Global.GAME_NAME + " - " + Global.GAME_VER);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setFocusable(true);
        this.setLayout(new GridBagLayout());
        this.getContentPane().setBackground(Color.BLACK);

        this.addKeyListener(keyH);
        this.add(gPanel);

        _gameStart();
        revalidate();
    }


    public KeyHandler getKeyH() { return keyH; }


    private void _gameStart()
    {
        curScene = scene.TITLE;
        renderThread = new Thread(this::_draw); 
        processThread = new Thread(this::_process);

        renderThread.start();
    }
    
    public void _draw()
    {
        double drawInterval = 1e9 / Global.FPS[Global.fpsLevel];
        double nanoDelta = 0;
        double delta;
        long lastTime = System.nanoTime();
        long curTime;


        while (renderThread != null)
        {
            curTime = System.nanoTime();
            nanoDelta += curTime - lastTime;
            lastTime = curTime;

            if (nanoDelta >= drawInterval)
            {
                delta = nanoDelta / 1e9;
                System.out.println(Math.round(1 / delta));
                nanoDelta = 0;
                
                scnH.handle(this);
                gPanel.repaint();
            }
        }
    }

    public void _process()
    {
        // TODO playing (not selecting) handling strategy
    }

    public void onSizeChange()
    {
        int dispW = this.getWidth();
        int dispH = this.getHeight();

        if (dispH * Global.SCR_PROPORTION.get(Global.scrProp) > dispW)
        {
            dispH = (int)(dispW / Global.SCR_PROPORTION.get(Global.scrProp));
        }
        else
        {
            dispW = (int)(dispH * Global.SCR_PROPORTION.get(Global.scrProp));
        }

        gPanel.setPreferredSize(new Dimension(dispW, dispH));
        gPanel.revalidate();
    }
}
