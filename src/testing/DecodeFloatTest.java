package testing;

import encdec.Decode;
import encdec.DecodeException;

import org.junit.Test;
import static org.junit.Assert.*;

public class DecodeFloatTest {

    @Test
    public void test(){
        byte[] input={(byte)0x3f, (byte)0x47, (byte)0x93, (byte)0x1c};
        float expected=0.77958846f;
        try{
            float actual=Decode.Float(input);
            assertEquals(expected, actual, 0.0f);
        }
        catch (DecodeException e){
            fail("Decoding 4 byte Array int to Float shouldn't throw Exceptions!");
        }
    }

    @Test
    public void testMAX_FLOAT(){
        byte[] input={(byte)0x7f, (byte)0x7f, (byte)0xff, (byte)0xff};
        float expected=Float.MAX_VALUE;
        try{
            float actual=Decode.Float(input);
            assertEquals(expected, actual, 0.0f);
        }
        catch (DecodeException e){
            fail("Decoding 4 byte Array int to Float shouldn't throw Exceptions!");
        }
    }

    @Test
    public void testMIN_FLOAT(){
        byte[] input={(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x01};
        float expected=Float.MIN_VALUE;
        try{
            float actual=Decode.Float(input);
            assertEquals(expected, actual, 0.0f);
        }
        catch (DecodeException e){
            fail("Decoding 4 byte Array int to Float shouldn't throw Exceptions!");
        }
    }

    @Test
    public void testException(){
        byte[] input= {(byte)0x00, (byte)0x00, (byte)0xff};
        try{
            Decode.Float(input);
            fail("Decode Exception should be thrown when trying to Decode byte[] with length different than 4 into FLOAT");
        }
        catch (DecodeException e){
            assertEquals("Decoding a FLOAT needs 4 bytes", e.getMessage());
        }
    }


}
