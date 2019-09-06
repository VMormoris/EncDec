package testing;


import encdec.EmptyStringException;

import encdec.Encode;
import org.junit.Test;

import java.nio.charset.Charset;

import static org.junit.Assert.*;

public class EncodeStringTest {
    private String input="Hello";
    @Test
    public void testEmptyString() {
        String input_str = "";
        Charset input_charset=Charset.forName("UTF-8");
        String expected = "Empty String cannot be encoded.";
        try {
            Encode.string(input_str, input_charset);
            fail("EmptyStringException should be thrown when trying to Encode an empty String");
        } catch (EmptyStringException e) {
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void testUTF8(){
        Charset input_charset=Charset.forName("UTF-8");
        byte[] expected={(byte)0x48, (byte)0x65, (byte)0x6c, (byte)0x6c, (byte)0x6f};
        byte[] actual;
        try{
            actual=Encode.string(input, input_charset);
            assertArrayEquals(expected, actual);
        }
        catch (EmptyStringException e){
            fail("Encoding an non empty String shouldn't produce any Exceptions!");
        }
    }

    @Test
    public void testUTF16(){
        Charset input_charset=Charset.forName("UTF-16");
        byte[] expected={(byte)0xfe, (byte)0xff, (byte)0x00, (byte)0x48, (byte) 0x00, (byte) 0x65, (byte)0x00, (byte)0x6c, (byte)0x00, (byte)0x6c, (byte)0x00, (byte)0x6f};
        byte[] actual;
        try{
            actual=Encode.string(input, input_charset);
            assertArrayEquals(expected, actual);
        }
        catch (EmptyStringException e){
            fail("Encoding an non empty String shouldn't produce any Exceptions!");
        }
    }

    @Test
    public void testUTF16BE(){
        Charset input_charset=Charset.forName("UTF-16BE");
        byte[] expected={(byte)0x00, (byte)0x48, (byte)0x00, (byte)0x65, (byte)0x00, (byte)0x6c, (byte)0x00, (byte)0x6c, (byte)0x00, (byte)0x6f};
        byte[] actual;
        try{
            actual=Encode.string(input, input_charset);
            assertArrayEquals(expected, actual);
        }
        catch (EmptyStringException e){
            fail("Encoding an non empty String shouldn't produce any Exceptions!");
        }
    }

    @Test
    public void testUTF16LE(){
        Charset input_charset=Charset.forName("UTF-16LE");
        byte[] expected={(byte)0x48, (byte)0x00, (byte)0x65, (byte)0x00, (byte)0x6C, (byte)0x00, (byte)0x6C, (byte)0x00, (byte)0x6F, (byte)0x00};
        byte[] actual;
        try{
            actual=Encode.string(input, input_charset);
            assertArrayEquals(expected, actual);
        }
        catch (EmptyStringException e){
            fail("Encoding an non empty String shouldn't produce any Exceptions!");
        }
    }

    @Test
    public void testUSASCII(){
        Charset input_charset=Charset.forName("US-ASCII");
        byte[] expected={(byte)0x48, (byte)0x65, (byte)0x6c, (byte)0x6c, (byte)0x6f};
        byte[] actual;
        try{
            actual=Encode.string(input, input_charset);
            assertArrayEquals(expected, actual);
        }
        catch (EmptyStringException e){
            fail("Encoding an non empty String shouldn't produce any Exceptions!");
        }
    }


    @Test
    public void testLatin1(){
        Charset input_charset=Charset.forName("ISO-8859-1");
        byte[] expected={(byte)0x48, (byte)0x65, (byte)0x6c, (byte)0x6c, (byte)0x6f};
        byte[] actual;
        try{
            actual=Encode.string(input, input_charset);
            assertArrayEquals(expected, actual);
        }
        catch (EmptyStringException e){
            fail("Encoding an non empty String shouldn't produce any Exceptions!");
        }
    }


}