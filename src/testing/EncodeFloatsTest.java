package testing;

import encdec.Encode;

import org.junit.Test;
import static org.junit.Assert.*;

public class EncodeFloatsTest {

    @Test
    public void testThreeZeros(){
        float[] input={0.0f, 0.0f, 0.0f};
        byte[] expected={(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00};
        byte[] actual=Encode.Floats(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMinMax(){
        float[] input={Float.MIN_VALUE, Float.MAX_VALUE};
        byte[] expected={(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x7f, (byte)0x7f, (byte)0xff, (byte)0xff};
        byte[] actual=Encode.Floats(input);
        assertArrayEquals(expected, actual);
    }

}
