package com.control.backend.auth.model.dto;

import lombok.Builder;

@Builder
public record ResponseTokenDTO(

		String accessToken,
		
		String refreshToken,
		
		String tokenId,
		
		String tokenType

) {

}
