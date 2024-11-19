package org.example.postproject.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseData<T> {

    String message;
    T data;
    boolean success;

    public ResponseData(Boolean success) {
        this.success = success;
    }

    public ResponseData(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }

    public ResponseData(T data, Boolean success) {
        this.data = data;
        this.success = success;
    }

    public ResponseData(String message, String path, Integer errorCode) {
        this.success = false;
    }


    public static <E> ResponseData<E> successResponse(E data) {
        return new ResponseData<>(data, true);
    }

    public static ResponseData<String> successResponse(String message) {
        return new ResponseData<>(message, true);
    }


}
