package com.control.model.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsCpfValidator implements ConstraintValidator<IsCpf, String> {

	private final int[] PESO_CPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

	@Override
	public boolean isValid(String cpf, ConstraintValidatorContext context) {
		final String cpfSomenteDigitos = cpf.replaceAll("\\D", "");		
		if ((cpfSomenteDigitos == null) || (cpfSomenteDigitos.length() != 11) || "00000000000".equals(cpfSomenteDigitos)
				|| "11111111111".equals(cpfSomenteDigitos) || "22222222222".equals(cpfSomenteDigitos)
				|| "33333333333".equals(cpfSomenteDigitos) || "44444444444".equals(cpfSomenteDigitos)
				|| "55555555555".equals(cpfSomenteDigitos) || "66666666666".equals(cpfSomenteDigitos)
				|| "77777777777".equals(cpfSomenteDigitos) || "88888888888".equals(cpfSomenteDigitos)
				|| "99999999999".equals(cpfSomenteDigitos)) {
			return false;
		}
		
		final Integer digito1 = calcularDigito(cpfSomenteDigitos.substring(0, 9), PESO_CPF);
		final Integer digito2 = calcularDigito(cpfSomenteDigitos.substring(0, 9) + digito1, PESO_CPF);
		return cpfSomenteDigitos.equals(cpfSomenteDigitos.substring(0, 9) + digito1.toString() + digito2.toString());
	}

	private int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

}