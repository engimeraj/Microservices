package org.userservices.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private List<UserDto> content;
    private Long totalElement;
    private int pageNumber;
    private int pageSize;
    private boolean lastPage;

}
