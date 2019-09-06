package testing;

import encdec.Encode;


import org.junit.Test;
import static org.junit.Assert.*;

public class EncodeFloatTest {

    @Test
    public void test(){
        float input=0.77958846f;
        byte[] expected={(byte)0x3f, (byte)0x47, (byte)0x93, (byte)0x1c};
        byte[] actual= Encode.Float(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMAX_FLOAT(){
        float input= Float.MAX_VALUE;
        byte[] expected={(byte)0x7f, (byte)0x7f, (byte)0xff, (byte)0xff};
        byte[] actual= Encode.Float(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMIN_FLOAT(){
        float input=Float.MIN_VALUE;
        byte[] expected={(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x01};
        byte[] actual=Encode.Float(input);
        assertArrayEquals(expected, actual);
    }

}