package org.gfg.JBDL_76_Minor1.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserCreationResponse {

    private String userName;

    private String userEmail;

    private String userAddress;

    private String userPhone;

}
