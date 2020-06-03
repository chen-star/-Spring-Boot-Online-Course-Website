package com.course.server.dto;

/**
 * @author Alex CHEN
 * @version 1.0
 * @since 2020-05-29 23:36
 */
public class ResponseDto<T> {

    private boolean success = true;

    private String code;

    private String message;

    private T content;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
