package com.github.ququzone.common.web;

/**
 * noite based flash message.
 *
 * @author Yang XuePing
 */
public class FlashMessage {
    private Integer level;
    private String message;

    public FlashMessage(Level level, String message) {
        switch (level) {
            case success:
                this.level = 1;
                break;
            case warning:
                this.level = 2;
                break;
            case error:
                this.level = 3;
                break;
            case information:
                this.level = 4;
                break;
        }
        this.message = message;
    }

    public Integer getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    public enum Level {
        success, warning, error, information
    }
}
