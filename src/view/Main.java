package view;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import controller.Global;
import controller.Logger;
import controller.ResLoader;

public class Main
{
    public static void main(String[] args)
    {
        // TODO: initialize attributes of Global

        // 上次记录：logIdx; 这次记录：logIdx+1
        // last log: logIdx; this log: logIdx+1
        Logger.setLogIdx(Global.logIdx + 1);
        Logger.initFile();

        MainWindow mainWindow = new MainWindow();

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(mainWindow);

        ResLoader resLoader = new ResLoader();
        resLoader.reload();

        mainWindow.onSizeChange();
        mainWindow._gameStart();
        mainWindow.setVisible(true);
    }
}