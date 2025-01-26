package view;

import javax.swing.JFrame;

import controller.Global;

public class Main
{
    public static void main(String[] args)
    {
        JFrame MainWindow = new JFrame();
        MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainWindow.setTitle(Global.gameName + " -ver " + Global.gameVer);

        MainWindow.setLocationRelativeTo(null);
        MainWindow.setResizable(false);
        MainWindow.setVisible(true);
    }
}