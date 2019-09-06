package testing;

import encdec.Decode;

import encdec.DecodeException;
import org.junit.Test;
import static org.junit.Assert.*;

public class DecodeShortTest {

    @Test
    public void test255(){
        byte[] input= {(byte)0x00, (byte)0xff};
        short expected=255;
        try {
            short actual=Decode.Short(input);
            assertEquals(expected, actual);
        } catch (DecodeException e) {
            fail("Decoding 2 byte Array into Short shouldn't throw Exceptions!");
        }
    }

    @Test
    public void testMIN_SHORT(){
        byte[] input={(byte)0x80, (byte)0x00};
        short expected=Short.MIN_VALUE;
        try {
            short actual=Decode.Short(input);
            assertEquals(expected, actual);
        } catch (DecodeException e) {
            fail("Decoding 2 byte Array into Short shouldn't throw Exceptions!");
        }
    }

    @Test
    public void testMAX_SHORT(){
        byte[] input={(byte)0x7f, (byte)0xff};
        short expected=Short.MAX_VALUE;
        try {
            short actual=Decode.Short(input);
            assertEquals(expected, actual);
        } catch (DecodeException e) {
            fail("Decoding 2 byte Array into Short shouldn't throw Exceptions!");
        }
    }

    @Test
    public void testException(){
        byte[] input={(byte)0x7f, (byte)0xff, (byte)0x00};
        try{
            Decode.Short(input);
            fail("Decode Exception should be thrown when trying to Decode byte[] with length different than 2 into SHORT");
        }
        catch (DecodeException e){
            assertEquals("Decoding a SHORT needs 2 bytes", e.getMessage());
        }
    }


}
