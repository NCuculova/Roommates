package mk.ukim.finki.mp.roommates.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import mk.ukim.finki.mp.roommates.model.Member;

import org.springframework.security.crypto.codec.Hex;


public class TokenUtils {
	public static final String MAGIC_KEY = "obfuscate";

	/**
	 * Token creation
	 * Input: Member
	 * Output: String
	 */
	public static String createToken(Member memberInfo) {

		/* Expires in one hour */
		long expires = System.currentTimeMillis() + 1000L * 60 * 60;

		StringBuilder tokenBuilder = new StringBuilder();
		tokenBuilder.append(memberInfo.getEmail());
		tokenBuilder.append(":");
		tokenBuilder.append(expires);
		tokenBuilder.append(":");
		tokenBuilder.append(TokenUtils.computeSignature(memberInfo, expires));

		return tokenBuilder.toString();
	}

	/**
	 * Does encription for the token
	 * Input: Member and expiring time
	 * Output: String
	 */
	public static String computeSignature(Member memberInfo, long expires) {

		StringBuilder signatureBuilder = new StringBuilder();
		signatureBuilder.append(memberInfo.getEmail());
		signatureBuilder.append(":");
		signatureBuilder.append(expires);
		signatureBuilder.append(":");
		signatureBuilder.append(memberInfo.getPassword());
		signatureBuilder.append(":");
		signatureBuilder.append(TokenUtils.MAGIC_KEY);

		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("No MD5 algorithm available!");
		}

		return new String(Hex.encode(digest.digest(signatureBuilder.toString()
				.getBytes())));
	}

	/**
	 * Gets the e-mail from the token
	 * Input: Sting (token)
	 * Output: String (e-mail)
	 */
	public static String getEmailFromToken(String authToken) {

		if (null == authToken) {
			return null;
		}

		String[] parts = authToken.split(":");
		return parts[0];
	}

	/**
	 * Validates if the token has expired
	 * Input: Member and token
	 * Output: boolean
	 */
	public static boolean validateToken(String authToken,
			Member memberInfo ) {

		String[] parts = authToken.split(":");
		long expires = Long.parseLong(parts[1]);
		String signature = parts[2];

		if (expires < System.currentTimeMillis()) {
			return false;
		}

		return signature.equals(TokenUtils.computeSignature(memberInfo,
				expires));
	}
}
