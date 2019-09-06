package testing;

import encdec.Encode;

import org.junit.Test;
import static org.junit.Assert.*;

public class EncodeDoublesTest {

    @Test
    public void testTwoZeros(){
        double[] input={0.0, 0.0};
        byte[] expected={(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00};
        byte[] actual=Encode.Doubles(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMinMax(){
        double[] input={Double.MIN_VALUE, Double.MAX_VALUE};
        byte[] expected={(byte) 0x00, (byte)0x00, (byte) 0x00, (byte)0x00, (byte) 0x00, (byte)0x00, (byte) 0x00, (byte)0x01,
                (byte) 0x7f, (byte) 0xef, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff};
        byte[] actual=Encode.Doubles(input);
        assertArrayEquals(expected, actual);
    }


}
