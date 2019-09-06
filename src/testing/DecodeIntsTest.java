package testing;

import encdec.Decode;
import encdec.DecodeException;

import org.junit.Test;
import static org.junit.Assert.*;

public class DecodeIntsTest {

    @Test
    public void testThreeZeros(){
        byte[] input={(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00};
        int[] expected={0, 0, 0};
        try{
            int[] actual=Decode.Ints(input);
            assertArrayEquals(expected, actual);
        }
        catch (DecodeException e){
            fail("Decoding multiple of 4 bytes into a int array should not throw any Exceptions!");
        }
    }

    @Test
    public void testMaxMin(){
        byte[] input={(byte)0x7f, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x80, (byte)0x00, (byte)0x00, (byte)0x00};
        int[] expected={Integer.MAX_VALUE, Integer.MIN_VALUE};
        try{
            int[] actual=Decode.Ints(input);
            assertArrayEquals(expected, actual);
        }
        catch (DecodeException e){
            fail("Decoding multiple of 4 bytes into a int array should not throw any Exceptions!");
        }
    }

    @Test
    public void testException(){
        byte[] input={(byte)0x7f, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x80, (byte)0x00, (byte)0x00};
        try{
            Decode.Ints(input);
            fail("Decoding an none multiple of 4 bytes into ints array should throw a DecodeException.");
        }
        catch (DecodeException e){
            assertEquals("Decoding a INT array needs multiple of 4 bytes", e.getMessage());
        }
    }

}
