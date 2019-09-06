package testing;

import encdec.ByteArrayOverflowException;
import encdec.Encode;
import encdec.integer;
import  org.junit.Test;
import static org.junit.Assert.*;

public class EncodeShortTest {

    @Test
    public void test255(){
        short input=255;
        byte[] expected= {(byte)0x00, (byte)0xff};
        byte[] actual= Encode.Short(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMIN_SHORT(){
        short input=Short.MIN_VALUE;
        byte[] expected= {(byte)0x80, (byte)0x00};
        byte[] actual= Encode.Short(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMAX_SHORT(){
        short input=Short.MAX_VALUE;
        byte[] expected= {(byte)0x7f, (byte)0xff};
        byte[] actual=Encode.Short(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testIntegerLFunc(){
        long input=(long)Short.MAX_VALUE;
        byte[] expected= {(byte)0x7f, (byte)0xff};

        try{
            byte[] actual=Encode.integer(input,integer.SHORT);
         assertArrayEquals(expected, actual);
        }
        catch (ByteArrayOverflowException e){
            fail("Exception shouldn't be thrown when encoding Short.MAX_VALUE as SHORT");
        }

    }

    @Test
    public void testIntegerIFunc(){
        int input=(int)Short.MIN_VALUE;
        byte[] expected= {(byte)0x80, (byte)0x00};
        try{
            byte[] actual=Encode.integer(input,integer.SHORT);
            assertArrayEquals(expected, actual);
        }
        catch (ByteArrayOverflowException e){
            fail("Exception shouldn't be thrown when encoding Short.MIN_VALUE as SHORT");
        }
    }


    @Test
    public void testMaxExceptionL(){
        long input=((long)Short.MAX_VALUE)+1;
        try{
            Encode.integer(input, integer.SHORT);
            fail("Exception should be thrown when encoding Short.MAX_VALUE+1 as SHORT");
        }
        catch (ByteArrayOverflowException e){
            assertEquals("Can't hold value in 2 bytes!", e.getMessage());
        }
    }

    @Test
    public void testMaxExceptionI(){
        int input=((int)Short.MAX_VALUE)+1;
        try{
            Encode.integer(input, integer.SHORT);
            fail("Exception should be thrown when encoding Short.MAX_VALUE+1 as SHORT");
        }
        catch (ByteArrayOverflowException e){
            assertEquals("Can't hold value in 2 bytes!", e.getMessage());
        }
    }

    @Test
    public void testMinExceptionL(){
        long input=((long)Short.MIN_VALUE)-1;
        try{
            Encode.integer(input, integer.SHORT);
            fail("Exception should be thrown when encoding Short.MIN_VALUE-1 as SHORT");
        }
        catch (ByteArrayOverflowException e){
            assertEquals("Can't hold value in 2 bytes!", e.getMessage());
        }
    }

    @Test
    public void testMinExceptionI(){
        int input=((int)Short.MIN_VALUE)-1;
        try{
            Encode.integer(input, integer.SHORT);
            fail("Exception should be thrown when encoding Short.MIN_VALUE-1 as SHORT");
        }
        catch (ByteArrayOverflowException e){
            assertEquals("Can't hold value in 2 bytes!", e.getMessage());
        }
    }


}