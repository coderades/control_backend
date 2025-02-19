package com.control.backend.auth.util;

import java.util.Random;
import java.security.SecureRandom;

public class StringUtil {

	private static final Random rand = new SecureRandom();

	public static String stringRandomUtil(Integer length) {
		final var aToZ = "abcdefghijklmnopqrdtuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; // 36 letter.
		final var res = new StringBuilder();
		for (int i = 0; i < length; i++) {
			final int randIndex = rand.nextInt(aToZ.length());
			res.append(aToZ.charAt(randIndex));
		}
		return res.toString();
	}
	
}
