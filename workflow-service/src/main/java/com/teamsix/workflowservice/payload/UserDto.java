package com.teamsix.workflowservice.payload;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class UserDto {
    private Long userId;
    private String username;
    private String role;

}
