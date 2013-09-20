package com.stefanski.lineserver.file;

/**
 * This is an exception that is thrown whenever there are some problems with getting line from
 * TextFile.
 * 
 * @author Dariusz Stefanski
 * @date Sep 7, 2013
 */
public class TextFileException extends Exception {

    private static final long serialVersionUID = 2687756341553547329L;

    public TextFileException(String message) {
        super(message);
    }

    public TextFileException(String message, Throwable cause) {
        super(message, cause);
    }

}