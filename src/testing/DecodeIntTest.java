package testing;

import encdec.Decode;

import encdec.DecodeException;
import org.junit.Test;
import static org.junit.Assert.*;

public class DecodeIntTest {

    @Test
    public void test255(){
        byte[] input= {(byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff};
        int expected=255;
        try{
            int actual=Decode.Int(input);
            assertEquals(expected, actual);
        }
        catch (DecodeException e){
            fail("Decoding 4 byte Array int to Integer shouldn't throw Exceptions!");
        }
    }

    @Test
    public void testMIN_INT(){
        byte[] input={(byte)0x80, (byte)0x00, (byte)0x00, (byte)0x00};
        int expected=Integer.MIN_VALUE;
        try{
            int actual=Decode.Int(input);
            assertEquals(expected, actual);
        }
        catch (DecodeException e){
            fail("Decoding 4 byte Array int to Integer shouldn't throw Exceptions!");
        }
    }

    @Test
    public void testMAX_INT(){
        byte[] input={(byte)0x7f, (byte)0xff, (byte)0xff, (byte)0xff};
        int expected=Integer.MAX_VALUE;
        try{
            int actual=Decode.Int(input);
            assertEquals(expected, actual);
        }
        catch (DecodeException e){
            fail("Decoding 4 byte Array int to Integer shouldn't throw Exceptions!");
        }
    }

    @Test
    public void testException(){
        byte[] input={(byte)0x00, (byte)0x00, (byte)0x00};
        try{
            Decode.Int(input);
            fail("Decode Exception should be thrown when trying to Decode byte[] with length different than 4 into INT");
        }
        catch (DecodeException e){
            assertEquals("Decoding a INT needs 4 bytes", e.getMessage());
        }
    }

}
