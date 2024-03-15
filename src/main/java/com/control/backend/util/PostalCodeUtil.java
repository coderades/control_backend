package com.control.backend.util;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.control.backend.model.util.PostalCode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PostalCodeUtil {
	private static final String API_BR_PT = "https://viacep.com.br/ws/";

	public static PostalCode findPostalCode(String cep) {
		// CEPUtils.validaCep(cepString);
		try {
			final var httpClient = HttpClient.newBuilder().connectTimeout(Duration.of(1, MINUTES)).build();
			final var httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(API_BR_PT + cep + "/json")).build();
			final var httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

			final var objectMapper = new ObjectMapper();
			final var jsonNode = objectMapper.readTree(httpResponse.body());

			final var postalCode = new PostalCode();
			postalCode.setPostalCode(jsonNode.get("cep").asText().replace("-", ""));
			postalCode.setStateId(jsonNode.get("uf").asText());
			postalCode.setCity(jsonNode.get("localidade").asText());
			postalCode.setNeighborhood(jsonNode.get("bairro").asText());
			postalCode.setAdress(jsonNode.get("logradouro").asText());
			postalCode.setComplement(jsonNode.get("complemento").asText());

			return postalCode;
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
}
