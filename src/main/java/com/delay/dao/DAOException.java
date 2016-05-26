package com.delay.dao;

import java.util.logging.Logger;

/**
 * Created by LucidMinds on 25.05.16.
 * package com.delay.dao;
 */
class DAOException extends Exception {

    private Logger logger = Logger.getLogger(DAOException.class.getName());

    private String message;
    private Throwable throwable;
    private Object object;

    public DAOException(String message, Throwable throwable) {
        this.message = message;
        this.throwable = throwable;
    }

    DAOException(String message, Throwable throwable, Object object) {
        this.message = message;
        this.throwable = throwable;
        this.object = object;
    }

    void print() {
        logger.warning(message);
        throwable.printStackTrace();
        if (object != null) {
            logger.warning(object.toString());
        }
    }
}
