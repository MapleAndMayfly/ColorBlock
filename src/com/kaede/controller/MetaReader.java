package com.kaede.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;

public class MetaReader
{
    private String curOuterKey = "null";

    public Map<String, Map<String, String>> readMeta(String path) throws IOException, MetaBrokenException
    {
        Logger.log("@MetaReader: Reading meta file [" + path + "].");

        Map<String, Map<String, String>> ret = new HashMap<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)))
        {
            String line;

            while ((line = reader.readLine()) != null)
            {
                // 忽略注释与空行 - ignore annotate and empty lines
                if (line.startsWith("!") || line.startsWith("//") || line.isEmpty()) continue;
                parseLine(path, ret, line);
            }
        } 

        Logger.log("@MetaReader: File [" + path + "] reading accomplished.");
        return ret;
    }

    private void parseLine(String metaPath, Map<String, Map<String, String>> target, String line)
    {
        // System.out.println("\ncurrent key:\t" + curOuterKey + "\ncurrent line:\t" + line);

        if (line.startsWith("#"))
        {
            // 删除空格、开头"#"号与末尾":"号 - remove spaces, "#" at the biginning and ":" at the end
            line = line.trim().substring(1, line.length() - 1);
            curOuterKey = line;
            target.put(line, new HashMap<>());
        }
        else if (line.matches("\s*\"([^\"]|\\\")*\"\s*:\s*\"([^\"]|\\\")*\"\s*")) // "...":"..."
        {
            {
                String[] parts = line.split("\"\\s*:\\s*\"");
                if (parts.length == 2)
                {
                    String key = parts[0].trim().substring(1);
                    String value = parts[1].trim().substring(0, parts[1].length() - 1);
                    // System.out.println("parse result:\t" + key + " --> " + value);
                    target.get(curOuterKey).put(key, value);
                }
                else
                {
                    throw new MetaBrokenException(metaPath);
                }
            }
        }
        else
        {
            throw new MetaBrokenException(metaPath);
        }
    }
}
