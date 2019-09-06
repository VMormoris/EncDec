package testing;

import encdec.Decode;
import encdec.DecodeException;

import org.junit.Test;
import static org.junit.Assert.*;


public class DecodeDoublesTest {

    @Test
    public void testTwoZeros(){
        byte[] input={(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00};
        double[] expected={0.0, 0.0};
        try{
            double[] actual=Decode.Doubles(input);
            assertArrayEquals(expected, actual, 0.0f);
        }
        catch (DecodeException e){
            fail("Decoding multiple of 8 bytes into a double array should not throw any Exceptions!");
        }
    }

    @Test
    public void testMinMax(){
        byte[] input={(byte) 0x00, (byte)0x00, (byte) 0x00, (byte)0x00, (byte) 0x00, (byte)0x00, (byte) 0x00, (byte)0x01,
                (byte) 0x7f, (byte) 0xef, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff};
        double[] expected={Double.MIN_VALUE, Double.MAX_VALUE};
        try{
            double[] actual=Decode.Doubles(input);
            assertArrayEquals(expected, actual, 0.0f);
        }
        catch (DecodeException e){
            fail("Decoding multiple of 8 bytes into a double array should not throw any Exceptions!");
        }
    }

    @Test
    public void testException(){
        byte[] input={(byte) 0x00, (byte)0x00, (byte)0x00, (byte) 0x00, (byte)0x00, (byte) 0x00, (byte)0x01,
                (byte) 0x7f, (byte) 0xef, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff};
        try{
            Decode.Doubles(input);
            fail("Decoding an none multiple of 8 bytes into doubleS array should throw a DecodeException.");
        }
        catch (DecodeException e){
            assertEquals("Decoding a DOUBLE array needs multiple of 8 bytes", e.getMessage());
        }

    }


}
