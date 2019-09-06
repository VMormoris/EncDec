package testing;

import  encdec.Decode;
import org.junit.Test;

import java.nio.charset.Charset;

import static org.junit.Assert.*;

public class DecodeStringTest {

    private String expected="Hello";

    @Test
    public void testUTF8(){
        byte[] input={(byte)0x48, (byte)0x65, (byte)0x6c, (byte)0x6c, (byte)0x6f};
        Charset input_charset=Charset.forName("UTF-8");
        String actual=Decode.string(input, input_charset);
        assertEquals(expected, actual);
    }

    @Test
    public void testUTF16(){
        byte[] input={(byte)0xfe, (byte)0xff, (byte)0x00, (byte)0x48, (byte) 0x00, (byte) 0x65, (byte)0x00, (byte)0x6c, (byte)0x00, (byte)0x6c, (byte)0x00, (byte)0x6f};
        Charset input_charset=Charset.forName("UTF-16");
        String actual=Decode.string(input, input_charset);
        assertEquals(expected, actual);
    }


    @Test
    public void testUTF16LE(){
        byte[] input={(byte)0x48, (byte)0x00, (byte)0x65, (byte)0x00, (byte)0x6C, (byte)0x00, (byte)0x6C, (byte)0x00, (byte)0x6F, (byte)0x00};
        Charset input_charset=Charset.forName("UTF-16LE");
        String actual=Decode.string(input, input_charset);
        assertEquals(expected, actual);
    }

    @Test
    public void testUTF16BE(){
        byte[] input={(byte)0x00, (byte)0x48, (byte)0x00, (byte)0x65, (byte)0x00, (byte)0x6c, (byte)0x00, (byte)0x6c, (byte)0x00, (byte)0x6f};
        Charset input_charset=Charset.forName("UTF-16BE");
        String actual=Decode.string(input, input_charset);
        assertEquals(expected, actual);
    }


    @Test
    public void testUSASCII(){
        byte[] input={(byte)0x48, (byte)0x65, (byte)0x6c, (byte)0x6c, (byte)0x6f};
        Charset input_charset=Charset.forName("US-ASCII");
        String actual=Decode.string(input, input_charset);
        assertEquals(expected, actual);
    }

    @Test
    public void testLatin1(){
        byte[] input={(byte)0x48, (byte)0x65, (byte)0x6c, (byte)0x6c, (byte)0x6f};
        Charset input_charset=Charset.forName("ISO-8859-1");
        String actual=Decode.string(input, input_charset);
        assertEquals(expected, actual);
    }

}
