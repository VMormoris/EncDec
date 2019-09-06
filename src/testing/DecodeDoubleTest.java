package testing;

import encdec.Decode;
import encdec.DecodeException;

import org.junit.Test;
import static org.junit.Assert.*;

public class DecodeDoubleTest {

    @Test
    public void test(){
        byte[] input={(byte) 0x3f, (byte) 0xe8, (byte) 0xf2, (byte) 0x63, (byte) 0x7f, (byte) 0x81, (byte) 0x3f, (byte) 0x86};
        double expected=0.77958846;
        try{
            double actual=Decode.Double(input);
            assertEquals(expected, actual, 0.0);
        }
        catch (DecodeException e){
            fail("Decoding 8 byte Array int to Double shouldn't throw Exceptions!");
        }
    }

    @Test
    public void testMAX_DOUBLE(){
        byte[] input={(byte) 0x7f, (byte) 0xef, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff};
        double expected=Double.MAX_VALUE;
        try{
            double actual=Decode.Double(input);
            assertEquals(expected, actual, 0.0);
        }
        catch (DecodeException e){
            fail("Decoding 8 byte Array int to Double shouldn't throw Exceptions!");
        }
    }

    @Test
    public void testMIN_DOUBLE(){
        byte[] input={(byte) 0x00, (byte)0x00, (byte) 0x00, (byte)0x00, (byte) 0x00, (byte)0x00, (byte) 0x00, (byte)0x01};
        double expected=Double.MIN_VALUE;
        try{
            double actual=Decode.Double(input);
            assertEquals(expected, actual, 0.0);
        }
        catch (DecodeException e){
            fail("Decoding 8 byte Array int to Double shouldn't throw Exceptions!");
        }
    }

    @Test
    public void testException(){
        byte[] input={(byte)0x00, (byte) 0x00, (byte)0x00, (byte) 0x00, (byte)0x00, (byte) 0x00, (byte)0x01};
        try{
            Decode.Double(input);
            fail("Decode Exception should be thrown when trying to Decode byte[] with length different than 8 into DOUBLE");
        }
        catch (DecodeException e){
            assertEquals("Decoding a DOUBLE needs 8 bytes", e.getMessage());
        }
    }



}
