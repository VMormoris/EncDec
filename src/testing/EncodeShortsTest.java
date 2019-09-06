package testing;

import encdec.ByteArrayOverflowException;
import encdec.Encode;

import encdec.integer;
import org.junit.Test;
import static org.junit.Assert.*;

public class EncodeShortsTest {

    @Test
    public void testFourZeros(){
        short[] input={(short)0, (short)0, (short)0, (short)0};
        byte[] expected={(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,  (byte)0x00, (byte) 0x00};
        byte[] actual=Encode.Shorts(input);
        assertArrayEquals(expected, actual);
    }


    @Test
    public void testMinMax(){
        short[] input={Short.MIN_VALUE, Short.MAX_VALUE};
        byte[] expected={(byte)0x80, (byte)0x00, (byte)0x7f, (byte)0xff};
        byte[] actual=Encode.Shorts(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testIntegersLFunc(){
        long[] input={(long)Short.MIN_VALUE, (long)Short.MAX_VALUE};
        byte[] expected={(byte)0x80, (byte)0x00, (byte)0x7f, (byte)0xff};
        try{
            byte[] actual=Encode.integers(input, integer.SHORT);
            assertArrayEquals(expected, actual);
        }
        catch (ByteArrayOverflowException e){
            fail("Encoding Short.MAX_VALUE and Short.MIN_VALUE as shorts should not throw any Exception.");
        }
    }

    @Test
    public void testIntegersIFunc(){
        int[] input={(int)Short.MIN_VALUE, (int)Short.MAX_VALUE};
        byte[] expected={(byte)0x80, (byte)0x00, (byte)0x7f, (byte)0xff};
        try{
            byte[] actual=Encode.integers(input, integer.SHORT);
            assertArrayEquals(expected, actual);
        }
        catch (ByteArrayOverflowException e){
            fail("Encoding Short.MAX_VALUE and Short.MIN_VALUE as shorts should not throw any Exception.");
        }
    }

    @Test
    public void testMaxExceptionL(){
        long[] input={((long)Short.MAX_VALUE)+1, (long)Short.MIN_VALUE};
        try{
            Encode.integers(input, integer.SHORT);
            fail("Encoding Short.MAX_VALUE+1 as a short should throw a ByteArrayOverflowException.");
        }
        catch (ByteArrayOverflowException e){
            assertEquals("Can't hold value in 2 bytes!", e.getMessage());
        }
    }

    @Test
    public void testMaxExceptionI(){
        int[] input={((int)Short.MAX_VALUE)+1, (int)Short.MIN_VALUE};
        try{
            Encode.integers(input, integer.SHORT);
            fail("Encoding Short.MAX_VALUE+1 as a short should throw a ByteArrayOverflowException.");
        }
        catch (ByteArrayOverflowException e){
            assertEquals("Can't hold value in 2 bytes!", e.getMessage());
        }
    }

    @Test
    public void testMinExceptionL(){
        long[] input={(long)Short.MAX_VALUE, ((long)Short.MIN_VALUE)-1};
        try{
            Encode.integers(input, integer.SHORT);
            fail("Encoding Short.MIN_VALUE-1 as a short should throw a ByteArrayOverflowException.");
        }
        catch (ByteArrayOverflowException e){
            assertEquals("Can't hold value in 2 bytes!", e.getMessage());
        }
    }

    @Test
    public void testMinExceptionI(){
        int[] input={(int)Short.MAX_VALUE, ((int)Short.MIN_VALUE)-1};
        try{
            Encode.integers(input, integer.SHORT);
            fail("Encoding Short.MIN_VALUE-1 as a short should throw a ByteArrayOverflowException.");
        }
        catch (ByteArrayOverflowException e){
            assertEquals("Can't hold value in 2 bytes!", e.getMessage());
        }
    }

}
