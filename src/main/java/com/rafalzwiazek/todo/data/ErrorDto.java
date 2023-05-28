package com.rafalzwiazek.todo.data;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorDto {

    public final String message;
}
