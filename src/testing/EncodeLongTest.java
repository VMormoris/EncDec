package testing;

import encdec.ByteArrayOverflowException;
import encdec.Encode;
import encdec.integer;

import org.junit.Test;
import static org.junit.Assert.*;

public class EncodeLongTest {

    @Test
    public void test255(){
        long input=255;
        byte[] expected={(byte)0x00, (byte) 0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff};
        byte[] actual= Encode.Long(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMIN_LONG(){
        long input=Long.MIN_VALUE;
        byte[] expected={(byte)0x80, (byte) 0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00};
        byte[] actual=Encode.Long(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMAX_LONG(){
        long input=Long.MAX_VALUE;
        byte[] expected={(byte)0x7F, (byte) 0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
        byte[] actual=Encode.Long(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testIntegerFunc(){
        long input=255;
        byte[] expected={(byte)0x00, (byte) 0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff};
        byte[] actual;
        try {
            actual = Encode.integer(input, integer.LONG);
            assertArrayEquals(expected, actual);
        } catch (ByteArrayOverflowException e) {
            fail("Exception shouldn't be thrown when encoding 255 as LONG");
        }
    }

}