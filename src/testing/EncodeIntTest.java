package testing;

import encdec.Encode;
import  encdec.integer;
import  encdec.ByteArrayOverflowException;

import  org.junit.Test;
import static org.junit.Assert.*;



public class EncodeIntTest {

    @Test
    public void test255() {
        int input=255;
        byte[] expected= {(byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff};
        byte[] actual= Encode.Int(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMIN_INT() {
        int input=Integer.MIN_VALUE;
        byte[] expected= {(byte)0x80, (byte)0x00, (byte)0x00, (byte)0x00};
        byte[] actual=Encode.Int(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMAX_INT() {
        int input=Integer.MAX_VALUE;
        byte[] expected= {(byte)0x7f, (byte)0xff, (byte)0xff, (byte)0xff};
        byte[] actual=Encode.Int(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testIntegerLFunc() {
        long input=(long)Integer.MAX_VALUE;
        byte[] expected= {(byte)0x7f, (byte)0xff, (byte)0xff, (byte)0xff};
        try{
            byte[] actual=Encode.integer(input, integer.INT);
            assertArrayEquals(expected, actual);
        }
        catch(ByteArrayOverflowException e) {
            fail("Exception shouldn't be thrown when encoding Integer.MAX_VALUE as INT");
        }
    }

    @Test
    public void testIntegerIFunc(){
        int input= Integer.MIN_VALUE;
        byte[] expected= {(byte)0x80, (byte)0x00, (byte)0x00, (byte)0x00};
        try{
            byte[] actual=Encode.integer(input, integer.INT);
            assertArrayEquals(expected, actual);
        }
        catch(ByteArrayOverflowException e) {
            fail("Exception shouldn't be thrown when encoding Integer.MIN_VALUE as INT");
        }
    }

    @Test
    public void testMaxExceptionL() {
        long input=((long)Integer.MAX_VALUE)+1;
        try{
            Encode.integer(input, integer.INT);
           fail("Exception should be thrown when encoding Integer.MAX_VALUE+1 as INT");
        }
        catch(ByteArrayOverflowException e) {
            assertEquals("Can't hold value in 4 bytes!", e.getMessage());
        }
    }

    @Test
    public void testMinExceptionL() {
        long input=((long)Integer.MIN_VALUE)-1;
        String expected="Can't hold value in 4 bytes!";
        try{
            Encode.integer(input, integer.INT);
            fail("Exception should be thrown when encoding Integer.MIN_VALUE-1 as INT");
        }
        catch(ByteArrayOverflowException e) {
            assertEquals(expected, e.getMessage());
        }
    }

}