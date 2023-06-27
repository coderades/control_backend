package com.control.model.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsCpfValidator implements ConstraintValidator<IsCpf, String> {

	private final int[] PESO_CPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

	@Override
	public boolean isValid(String cpf, ConstraintValidatorContext context) {
		final String cpfDigitosOnly = cpf.replaceAll("\\D", "");
		if ((cpfDigitosOnly == null) || (cpfDigitosOnly.length() != 11) || "00000000000".equals(cpfDigitosOnly)
				|| "11111111111".equals(cpfDigitosOnly) || "22222222222".equals(cpfDigitosOnly)
				|| "33333333333".equals(cpfDigitosOnly) || "44444444444".equals(cpfDigitosOnly)
				|| "55555555555".equals(cpfDigitosOnly) || "66666666666".equals(cpfDigitosOnly)
				|| "77777777777".equals(cpfDigitosOnly) || "88888888888".equals(cpfDigitosOnly)
				|| "99999999999".equals(cpfDigitosOnly)) {
			return false;
		}

		final Integer digit1 = calculateDigit(cpfDigitosOnly.substring(0, 9), PESO_CPF);
		final Integer digit2 = calculateDigit(cpfDigitosOnly.substring(0, 9) + digit1, PESO_CPF);
		return cpfDigitosOnly.equals(cpfDigitosOnly.substring(0, 9) + digit1.toString() + digit2.toString());
	}

	private int calculateDigit(String str, int[] peso) {
		int sum = 0;
		for (int index = str.length() - 1, digit; index >= 0; index--) {
			digit = Integer.parseInt(str.substring(index, index + 1));
			sum += digit * peso[peso.length - str.length() + index];
		}

		sum = 11 - sum % 11;
		return sum > 9 ? 0 : sum;
	}

}