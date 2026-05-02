package com.focusquest.dto.request;


import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestRequestDTO {

    private String title;
    private String description;
    private int xpReward;
}
