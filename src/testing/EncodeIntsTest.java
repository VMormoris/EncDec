package testing;

import encdec.ByteArrayOverflowException;
import encdec.Encode;

import encdec.integer;
import org.junit.Test;
import static org.junit.Assert.*;

public class EncodeIntsTest {

    @Test
    public void testThreeZeros(){
        int[] input={0, 0, 0};
        byte[] expected={(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00};
        byte[] actual=Encode.Ints(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMaxMin(){
        int[] input= {Integer.MAX_VALUE, Integer.MIN_VALUE};
        byte[] expected={(byte)0x7f, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x80, (byte)0x00, (byte)0x00, (byte)0x00};
        byte[] actual= Encode.Ints(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testIntegersLFunc(){
        long[] input= {(long)Integer.MAX_VALUE, (long)Integer.MIN_VALUE};
        byte[] expected={(byte)0x7f, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x80, (byte)0x00, (byte)0x00, (byte)0x00};
        try{
            byte[] actual=Encode.integers(input, integer.INT);
            assertArrayEquals(expected, actual);
        }catch (ByteArrayOverflowException e){
            fail("Encoding Integer.MAX_VALUE and Integer.MIN_VALUE as ints should not throw an Exception.");
        }
    }

    @Test
    public void testIntegersIFunc(){
        int[] input= {Integer.MAX_VALUE, Integer.MIN_VALUE};
        byte[] expected={(byte)0x7f, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x80, (byte)0x00, (byte)0x00, (byte)0x00};
        try{
            byte[] actual=Encode.integers(input, integer.INT);
            assertArrayEquals(expected, actual);
        }catch (ByteArrayOverflowException e){
            fail("Encoding Integer.MAX_VALUE and Integer.MIN_VALUE as ints should not throw an Exception.");
        }
    }

    @Test
    public void testMaxExceptionL(){
        long[] input={((long)Integer.MAX_VALUE)+1, (long)Integer.MIN_VALUE};
        try{
            Encode.integers(input, integer.INT);
            fail("Encoding Integer.MAX_VALUE+1 as an int should throw an ByteArrayOverflowException.");
        }
        catch (ByteArrayOverflowException e){
            assertEquals("Can't hold value in 4 bytes!", e.getMessage());
        }
    }

    @Test
    public void testMinExceptionL(){
        long[] input={(long)Integer.MAX_VALUE, ((long)Integer.MIN_VALUE)-1};
        try{
            Encode.integers(input, integer.INT);
            fail("Encoding Integer.MIN_VALUE-1 as an int should throw an ByteArrayOverflowException.");
        }
        catch (ByteArrayOverflowException e){
            assertEquals("Can't hold value in 4 bytes!", e.getMessage());
        }
    }

}
