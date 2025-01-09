package com.human.hanmat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {
    private int page;
    private int totalPages;
    private int totalItems;
    private List<T> items;
}
