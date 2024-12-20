package com.human.hanmat.entity;

import lombok.Data;

@Data
public class CommonVO<T> {
    private String id;
    private T data;
}
