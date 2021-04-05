package br.com.mobicare.collaborator.exceptions.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Response<T> {
    private T data;
    private List<Error> errors = new ArrayList<>();

    public static Response withDate(Object data) {
        Response response = new Response<>();
        response.setData(data);
        return response;
    }

    public void add(Error error) {
        errors.add(error);
    }
}