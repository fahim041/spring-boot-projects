package com.dev.restapiblog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private List<PostDto> data;
    private int page;
    private int size;
    private Long totalElement;
    private int totalPages;
    private boolean last;
}
