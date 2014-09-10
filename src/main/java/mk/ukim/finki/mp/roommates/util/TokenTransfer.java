package mk.ukim.finki.mp.roommates.util;

/**
 * Class for a token with getter and setter
 */

public class TokenTransfer {
	private final String token;

	public TokenTransfer(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}
}
