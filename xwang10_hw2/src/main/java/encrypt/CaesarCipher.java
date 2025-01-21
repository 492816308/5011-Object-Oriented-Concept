package encrypt;

import java.util.Random;

/**
 * The CaesarCipher class implements Encryptor interface. It encrypts and decrypts the input password and return it.
 */
public class CaesarCipher implements Encryptor {

	/**
	 * Constructor.
	 */
	public CaesarCipher() {
		shift = getShift();
	}

	/**
	 * Encrypts the passed in string
	 * @param s The string to encrypt
	 * @return The encrypted string
	 */
	@Override
	public String encrypt(String s) {
		return encryptDecrypt(s, true);
	}

	/**
	 * Decrypts the passed in string
	 * @param s The string to decrypt
	 * @return The decrypted string (plaintext)
	 */
	@Override
	public String decrypt(String s) {
		return encryptDecrypt(s, false);
	}

	/**
	 * Create the number to shift
	 * @return The number to shift
	 */
	static int getShift() {
		Random r = new Random();
		int low = 1;
		int high = OFFSET_MAX - OFFSET_MIN;
		return r.nextInt(high - low) + low;
	}

	/**
	 * Encrypts or decrypts the passed in password
	 * @param s The string to encrypt or decrypt
	 * @param encrypt boolean param to decide encrypt or decrypt
	 * @return The encrypted or decrypted password
	 * @throws IllegalArgumentException The argument is illegal
	 */
	private String encryptDecrypt(String s, boolean encrypt) throws IllegalArgumentException {
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			int indx = c, cpos;
			if (!isPositionInRange(indx))
				throw new IllegalArgumentException("String to be encrypted has unrecognized character " + c);

			if (encrypt) {
				cpos = indx + shift;
				if (cpos > OFFSET_MAX)
					cpos = OFFSET_MIN + (cpos - OFFSET_MAX);
			} else {
				cpos = indx - shift;
				if (cpos < OFFSET_MIN)
					cpos = OFFSET_MAX - (OFFSET_MIN - cpos);	
			}
			sb.append((char)cpos);
		}
		return sb.toString();		
	}

	/**
	 * Checks if indx is within the range
	 * @param indx char index.
	 * @return true if indx is winthin range. Otherwise, false.
	 */
	private boolean isPositionInRange(int indx) {
		return indx >= OFFSET_MIN && indx <= OFFSET_MAX;
	}

	private int shift;
	static final int OFFSET_MIN = 32;
	static final int OFFSET_MAX = 126;
}
