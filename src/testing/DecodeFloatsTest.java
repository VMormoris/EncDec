package testing;

import encdec.Decode;
import encdec.DecodeException;

import org.junit.Test;
import static org.junit.Assert.*;

public class DecodeFloatsTest {

    @Test
    public void testThreeZeros(){
        byte[] input={(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00};
        float[] expected={0.0f, 0.0f, 0.0f};
        try{
            float[] actual=Decode.Floats(input);
            assertArrayEquals(expected, actual, 0.0f);
        }
        catch (DecodeException e){
            fail("Decoding multiple of 4 bytes into a float array should not throw any Exceptions!");
        }
    }

    @Test
    public void testMinMax(){
        byte[] input={(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x7f, (byte)0x7f, (byte)0xff, (byte)0xff};
        float[] expected={Float.MIN_VALUE, Float.MAX_VALUE};
        try{
            float[] actual=Decode.Floats(input);
            assertArrayEquals(expected, actual, 0.0f);
        }
        catch (DecodeException e){
            fail("Decoding multiple of 4 bytes into a float array should not throw any Exceptions!");
        }
    }

    @Test
    public void testException(){
        byte[] input={(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x7f, (byte)0x7f, (byte)0xff};
        try{
            Decode.Floats(input);
            fail("Decoding an none multiple of 4 bytes into floatS array should throw a DecodeException.");
        }
        catch (DecodeException e){
            assertEquals("Decoding a FLOAT array needs multiple of 4 bytes", e.getMessage());
        }
    }

}
