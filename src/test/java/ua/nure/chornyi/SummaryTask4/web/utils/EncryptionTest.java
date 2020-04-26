package ua.nure.chornyi.SummaryTask4.web.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class EncryptionTest {

    @Test
    public void encrypt() {
        assertEquals(Encryption.encrypt("example"), "1a79a4d60de6718e8e5b326e338ae533");
    }
}