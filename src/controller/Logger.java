package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger
{
    public static void setLogIdx(int logIdx)
    {
        if (logIdx != -1)
        {
            logIdx = logIdx % 4;
            Global.logIdx = logIdx;
        }
    }

    public static void initFile()
    {
        String logFileName = Global.logsPath + "log_" + Global.logIdx + ".txt";
        File logFile = new File(logFileName);
        if (logFile.exists())
        {
            logFile.delete();
        }
    }

    public static void log(String rawMessage)
    {
        File logDir = new File(Global.logsPath);
        if (!logDir.exists())
        {
            logDir.mkdir();
        }

        String logFileName = Global.logsPath + "log_" + Global.logIdx + ".txt";
        StringBuilder curTime = new StringBuilder();
        curTime.append("[")
               .append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()))
               .append("]");
        
        StringBuilder msg = new StringBuilder();
        msg.append(curTime).append(" ").append(rawMessage).append("\n");

        try (FileWriter writer = new FileWriter(logFileName, true))
        {
            writer.write(msg.toString());
            System.out.println(msg.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
