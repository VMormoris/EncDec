package testing;

import encdec.Decode;
import encdec.DecodeException;

import org.junit.Test;
import static org.junit.Assert.*;

public class DecodeLongsTest {

    @Test
    public void testTwoZeros(){
        byte[] input= {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};
        long[] expected={(long)0, (long)0};
        try{
            long[] actual=Decode.Longs(input);
            assertArrayEquals(expected, actual);
        }
        catch (DecodeException e){
            fail("Decoding multiple of 8 bytes into a long array should not throw any Exceptions!");
        }
    }

    @Test
    public void testMaxMin(){
        byte[] input={(byte)0x7f, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff,
                (byte)0x80, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00};
        long[] expected={Long.MAX_VALUE, Long.MIN_VALUE};
        try{
            long[] actual=Decode.Longs(input);
            assertArrayEquals(expected, actual);
        }
        catch (DecodeException e){
            fail("Decoding multiple of 8 bytes into a long array should not throw any Exceptions!");
        }
    }

    @Test
    public void testException(){
        byte[] input={(byte)0x7f, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff,
                (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00};
        try{
            Decode.Longs(input);
            fail("Decoding an none multiple of 8 bytes array into longs should throw a DecodeException.");
        }
        catch (DecodeException e){
            assertEquals("Decoding a LONG array needs multiple of 8 bytes", e.getMessage());
        }
    }

}
