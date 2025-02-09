package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import model.Global;
import model.MultiLang;

public class Logger
{
    private static String lastException = null;
    private static String logPath = null;

    public static void setLogIdx(int logIdx)
    {
        if (logIdx != -1)
        {
            logIdx = logIdx % 4;
            Global.logIdx = logIdx;
        }
    }

    public static void init()
    {
        // 上次记录：logIdx; 这次记录：logIdx+1
        // last log: logIdx; this log: logIdx+1
        Logger.setLogIdx(Global.logIdx + 1);

        logPath = Global.logsPath + "log_" + Global.logIdx + ".txt";
        File logFile = new File(logPath);
        if (logFile.exists())
        {
            logFile.delete();
        }

        log("@Logger: Logger is ready.");
        log("@Logger: Today's date: " +
            new SimpleDateFormat("[yyyy-MM-dd]").format(new Date()));
    }

    public static void log(String rawMessage)
    {
        File logDir = new File(Global.logsPath);
        if (!logDir.exists())
        {
            logDir.mkdir();
        }

        StringBuilder msg = new StringBuilder();
        msg.append(new SimpleDateFormat("[HH:mm:ss.SSS] ").format(new Date()))
           .append(rawMessage).append("\n");

        try (FileWriter writer = new FileWriter(logPath, true))
        {
            writer.write(msg.toString());
            System.out.print(msg.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void error(String className, Exception e)
    {
        StringBuilder rawMsg = new StringBuilder(className);
        rawMsg.append(": Exception occurs:\n").append(e.toString());
        for (StackTraceElement element : e.getStackTrace())
        {
            rawMsg.append("\n\tat ").append(element);
        }

        attention(e);
        log(rawMsg.toString());
    }
        
    private static void attention(Exception e)
    {
        if (e.getClass().getSimpleName().equals(lastException)) return;

        lastException = e.getClass().getSimpleName();
        StringBuilder msg = new StringBuilder();
        File logFile = new File(logPath);
        String absolutePath = logFile.getAbsolutePath();

        msg.append(MultiLang.getText("message.popup.exception.part1"))
           .append(e.getClass().getSimpleName()).append("\n  ")
           .append(MultiLang.getText("message.popup.exception.part2"))
           .append(" [" + absolutePath + "]")
           .append(MultiLang.getText("message.popup.exception.part3"));

        JOptionPane.showMessageDialog(null, msg.toString(),
         MultiLang.getText("title.popup.exception"), 0);

        if (Desktop.isDesktopSupported())
        {
            Desktop desktop = Desktop.getDesktop();
            try
            {
                desktop.open(logFile);
            }
            catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
    }
}
