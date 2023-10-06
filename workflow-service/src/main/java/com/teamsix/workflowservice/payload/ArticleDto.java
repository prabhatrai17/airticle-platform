package com.teamsix.workflowservice.payload;

import com.teamsix.workflowservice.utils.Status;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder@ToString
@Getter
@Setter
public class ArticleDto {
    private Long srNo;
    private String articleId;
    private String title;
    private String content;
    private Status status;
    private LocalDateTime creationDate;
    private Date lastModifiedDate;
    private Integer currentVersionId;
    @Embedded
    private UserDto user;

}
