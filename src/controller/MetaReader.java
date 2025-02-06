package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class MetaReader
{
    public Map<String, Map<String, String>> readMeta(String path) throws IOException, MetaBrokenException
    {
        Logger.log("@MetaReader: Reading meta file [" + path + "].");

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path)))
        {
            String line;

            while ((line = reader.readLine()) != null)
            {
                content.append(line);
            }
        }
        return parseContent(path, content.toString());
    }

    private Map<String, Map<String, String>> parseContent(String metaPath, String content)
    {
        Map<String, Map<String, String>> ret = new HashMap<>();
        // 删除空格与开头"#"号 - remove spaces and "#" at the biginning
        content = content.trim().substring(1);

        String[] blocks = content.split("#");
        for (String block : blocks)
        {
            int headLength = block.indexOf(':');
            if (headLength == -1) throw new MetaBrokenException(metaPath);

            String outerKey = block.substring(0, headLength);
            // 从block中删除outerKey字符串 - remove outerKey string from block
            block = block.substring(headLength + 1);

            Map<String, String> valueMap = new HashMap<>();
            String[] pairs = block.split(",");
            for (String pair : pairs)
            {
                String[] keyValue = pair.split(":");
                if (keyValue.length != 2) throw new MetaBrokenException(metaPath);

                String innerKey = keyValue[0].trim().replace("\"", "");
                String valueStr = keyValue[1].trim().replace("\"", "");
                valueMap.put(innerKey, valueStr);
            }
            ret.put(outerKey, valueMap);
        }

        Logger.log("@MetaReader: File [" + metaPath + "] reading accomplished.");
        return ret;
    }
}

class MetaBrokenException extends RuntimeException
{
    MetaBrokenException(String metaPath)
    {
        super("File [" + metaPath + "] is broken");
    }
}
