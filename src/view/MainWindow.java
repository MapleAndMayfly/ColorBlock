package view;

import javax.swing.JFrame;

import model.Global;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Dimension;

public class MainWindow extends JFrame
{
    private GamePanel gPanel;
    private Thread renderThread;
    private Thread processThread;

    MainWindow()
    {
        gPanel = new GamePanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(Global.GAME_NAME + " - " + Global.GAME_VER);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setLayout(new GridBagLayout());
        this.getContentPane().setBackground(Color.BLACK);

        this.add(gPanel);

        _gameStart();
    }

    private void _gameStart()
    {
        renderThread = new Thread(this::_draw); 
        processThread = new Thread(this::_process);

        renderThread.start();

        // 游戏时（而非选歌或是其他什么时）启用
        // run for game play only (instead of selecting songs or sth else)
        // processThread.start();
    }
    
    public void _draw()
    {
        double drawInterval = 1e9 / Global.FPS[Global.fpsLevel];
        double nanoDelta = 0;

        // delta用来以秒为单位保存两帧间时间的差值，可以使运动和帧率无关
        // delta tells delta time in seconds between two frames and may helps make move has nothing to do with FPS
        double delta;
        long lastTime = System.nanoTime();
        long curTime;
        long timer = 0;
        int drawCount = 0;

        while (renderThread != null)
        {
            curTime = System.nanoTime();
            nanoDelta += curTime - lastTime;
            lastTime = curTime;

            if (nanoDelta >= drawInterval)
            {
                delta = nanoDelta / 1e9;
                timer += nanoDelta;
                drawCount++;

                gPanel._draw(delta);

                nanoDelta = 0;
            }

            if (timer >= 1e9)   // ~1s
            {
                // System.out.printf("Render: %dFPS\n", drawCount);
                timer = 0;
                drawCount = 0;
            }
        }
    }

    public void _process()
    {
        double processInterval = 1e9 / Global.PPS[Global.fpsLevel];
        double nanoDelta = 0;
        double delta;
        long lastTime = System.nanoTime();
        long curTime;
        long timer = 0;
        int processCount = 0;

        while (processThread != null)
        {
            curTime = System.nanoTime();
            nanoDelta += curTime - lastTime;
            lastTime = curTime;

            if (nanoDelta >= processInterval)
            {
                delta = nanoDelta / 1e9;
                timer += nanoDelta;
                processCount++;

                // TODO playing (not selecting) handling strategy

                nanoDelta = 0;
            }

            if (timer >= 1e9)
            {
                // System.out.printf("Process: %dFPS\n", processCount);
                timer = 0;
                processCount = 0;
            }
        }
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
        gPanel.changeBgi(Global.bgiName);
    }
}
