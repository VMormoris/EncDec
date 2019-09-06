package testing;

import encdec.ByteArrayOverflowException;
import encdec.Encode;

import encdec.integer;
import org.junit.Test;

import java.nio.BufferOverflowException;

import static org.junit.Assert.*;


public class EncodeLongsTest {

    @Test
    public void testTwoZeros() {
        long[] input = {(long) 0, (long) 0};
        byte[] expected = {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};
        byte[] actual = Encode.Longs(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMaxMin(){
        long[] input={Long.MAX_VALUE, Long.MIN_VALUE};
        byte[] expected={(byte)0x7f, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff,
                        (byte)0x80, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00};
        byte[] actual=Encode.Longs(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testIntegersFunc(){
        long[] input={Long.MAX_VALUE, Long.MIN_VALUE};
        byte[] expected={(byte)0x7f, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff,
                (byte)0x80, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00};
        try {
            byte[] actual = Encode.integers(input, integer.LONG);
            assertArrayEquals(expected, actual);
        }
        catch (ByteArrayOverflowException e){
            fail("Encoding integers as Longs shouldn't never throw an Exception.");
        }
    }

}
