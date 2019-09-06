package testing;

import encdec.Decode;
import encdec.DecodeException;

import org.junit.Test;
import static org.junit.Assert.*;


public class DecodeShortsTest {

   @Test
   public void testFourZeros(){
       byte[] input={(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,  (byte)0x00, (byte) 0x00};
       short[] expected={(short)0, (short)0, (short)0, (short)0};
       try{
           short[] actual=Decode.Shorts(input);
           assertArrayEquals(expected, actual);
       }
       catch (DecodeException e){
           fail("Decoding multiple of 2 bytes into a short array should not throw any Exceptions!");
       }
   }

    @Test
    public void testMinMax(){
       byte[] input={(byte)0x80, (byte)0x00, (byte)0x7f, (byte)0xff};
       short[] expected={Short.MIN_VALUE, Short.MAX_VALUE};
       try{
           short[] actual=Decode.Shorts(input);
           assertArrayEquals(expected, actual);
       }
       catch (DecodeException e){
           fail("Decoding multiple of 2 bytes into a short array should not throw any Exceptions!");
       }
    }

    @Test
    public void testException(){
        byte[] input={(byte)0x80, (byte)0x7f, (byte)0xff};
        try{
           Decode.Shorts(input);
           fail("Decoding an none multiple of 2 bytes array into shorts should throw a DecodeException.");
        }
        catch (DecodeException e){
            assertEquals("Decoding a SHORT array needs multiple of 2 bytes", e.getMessage());
        }
    }

}
