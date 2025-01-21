package encrypt;

import encrypt.CaesarCipher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaesarCipherTest {
    private CaesarCipher caesarCipher;

    @BeforeEach
    public void setUp() {
        // Create a new CaesarCipher instance before each test
        caesarCipher = new CaesarCipher();
    }

    // // Test: Verify that the constructor initializes the shift value correctly
    @Test
    public void testConstructor() {
        int shift = CaesarCipher.getShift();
        assertTrue(shift >= 1 && shift <= (CaesarCipher.OFFSET_MAX - CaesarCipher.OFFSET_MIN),
                "The shift value should be between 1 and OFFSET_MAX - OFFSET_MIN");
    }

    // Test a basic string
    @Test
    void testEncrypt() {
        String input = "Hello, World!";
        String encrypted = caesarCipher.encrypt(input);
        assertNotEquals(input,encrypted, "Encrypted text should differ from input.");
    }


    // Test an encrypted string
    @Test
    void testDecrypt() {
        String input = "Test123#$!";
        String encrypted = caesarCipher.encrypt(input);
        String decrypted = caesarCipher.decrypt(encrypted);
        assertEquals(input,decrypted, "Decrypted text should match the original text.");
    }
}