package com.kaede.controller;

public class MetaBrokenException extends RuntimeException
{
    MetaBrokenException(String metaPath)
    {
        super("File [" + metaPath + "] is broken");
    }
}
