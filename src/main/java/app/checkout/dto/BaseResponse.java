package app.checkout.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
    
    private String code;
    private Boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;
    private String path;

    private BaseResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public static <T> BaseResponse<T> success(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.code = "200";
        response.success = true;
        response.message = "Success";
        response.data = data;
        return response;
    }

    public static <T> BaseResponse<T> success(String message, T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.code = "200";
        response.success = true;
        response.message = message;
        response.data = data;
        return response;
    }

    public static <T> BaseResponse<T> created(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.code = "201";
        response.success = true;
        response.message = "Resource created successfully";
        response.data = data;
        return response;
    }

    public static <T> BaseResponse<T> created(String message, T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.code = "201";
        response.success = true;
        response.message = message;
        response.data = data;
        return response;
    }

    public static <T> BaseResponse<T> error(String code, String message) {
        BaseResponse<T> response = new BaseResponse<>();
        response.code = code;
        response.success = false;
        response.message = message;
        return response;
    }

    public static <T> BaseResponse<T> badRequest(String message) {
        BaseResponse<T> response = new BaseResponse<>();
        response.code = "400";
        response.success = false;
        response.message = message;
        return response;
    }

    public static <T> BaseResponse<T> notFound(String message) {
        BaseResponse<T> response = new BaseResponse<>();
        response.code = "404";
        response.success = false;
        response.message = message;
        return response;
    }

    public static <T> BaseResponse<T> internalServerError(String message) {
        BaseResponse<T> response = new BaseResponse<>();
        response.code = "500";
        response.success = false;
        response.message = message != null ? message : "Internal server error";
        return response;
    }

    public static <T> BaseResponse<T> unauthorized(String message) {
        BaseResponse<T> response = new BaseResponse<>();
        response.code = "401";
        response.success = false;
        response.message = message;
        return response;
    }

    public static <T> BaseResponse<T> forbidden(String message) {
        BaseResponse<T> response = new BaseResponse<>();
        response.code = "403";
        response.success = false;
        response.message = message;
        return response;
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static class Builder<T> {
        private final BaseResponse<T> response = new BaseResponse<>();

        public Builder<T> code(String code) {
            response.code = code;
            return this;
        }

        public Builder<T> success(Boolean success) {
            response.success = success;
            return this;
        }

        public Builder<T> message(String message) {
            response.message = message;
            return this;
        }

        public Builder<T> data(T data) {
            response.data = data;
            return this;
        }

        public Builder<T> path(String path) {
            response.path = path;
            return this;
        }

        public BaseResponse<T> build() {
            return response;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code='" + code + '\'' +
                ", success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                ", path='" + path + '\'' +
                '}';
    }
}