package view;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import model.ResLoader;

public class Main
{
    public static void main(String[] args)
    {
        MainWindow mainWindow = new MainWindow();

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(mainWindow);

        mainWindow.onSizeChange();
        mainWindow.setVisible(true);

        ResLoader resLoader = new ResLoader();
        resLoader.reload();
    }
}