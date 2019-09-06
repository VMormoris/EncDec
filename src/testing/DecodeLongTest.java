package testing;

import encdec.Decode;

import encdec.DecodeException;
import org.junit.Test;
import static org.junit.Assert.*;

public class DecodeLongTest {

    @Test
    public void test255(){
        byte[] input={(byte)0x00, (byte) 0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff};
        long expected=255;
        try {
            long actual=Decode.Long(input);
            assertEquals(expected, actual);
        } catch (DecodeException e) {
            fail("Decoding 8 byte Array into Long shouldn't throw Exceptions!");
        }
    }

    @Test
    public void testMIN_LONG(){
        byte[] input={(byte)0x80, (byte) 0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00};
        long expected=Long.MIN_VALUE;
        try {
            long actual=Decode.Long(input);
            assertEquals(expected, actual);
        } catch (DecodeException e) {
            fail("Decoding 8 byte Array into Long shouldn't throw Exceptions!");
        }
    }

    @Test
    public void testMAX_LONG(){
        byte[] input={(byte)0x7F, (byte) 0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
        long expected=Long.MAX_VALUE;
        try {
            long actual=Decode.Long(input);
            assertEquals(expected, actual);
        } catch (DecodeException e) {
            fail("Decoding 8 byte Array into Long shouldn't throw Exceptions!");
        }
    }

    @Test
    public void testException(){
        byte[] input={(byte)0x00, (byte)0x00, (byte)0x00,(byte)0x00};
        try{
            Decode.Long(input);
            fail("Decode Exception should be thrown when trying to Decode byte[] with length different than 8 into LONG");
        }
        catch (DecodeException e){
            assertEquals("Decoding a LONG needs 8 bytes", e.getMessage());
        }
    }

}
