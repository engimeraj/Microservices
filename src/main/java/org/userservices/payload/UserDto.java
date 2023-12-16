package org.userservices.payload;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.userservices.entities.Rating;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private Long mobile;
    private List<Rating> ratings = new ArrayList<>();
}
