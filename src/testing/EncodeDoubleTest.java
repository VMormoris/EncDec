package testing;

import encdec.Encode;
import org.junit.Test;
import static org.junit.Assert.*;

public class EncodeDoubleTest {

    @Test
    public void test() {
        double input = 0.77958846;
        byte[] expected = {(byte) 0x3f, (byte) 0xe8, (byte) 0xf2, (byte) 0x63, (byte) 0x7f, (byte) 0x81, (byte) 0x3f, (byte) 0x86};
        byte[] actual = Encode.Double(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMAX_DOUBLE(){
        double input=Double.MAX_VALUE;
        byte[] expected={(byte) 0x7f, (byte) 0xef, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff};
        byte[] actual=Encode.Double(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMIN_DOUBLE(){
        double input=Double.MIN_VALUE;
        byte[] expected={(byte) 0x00, (byte)0x00, (byte) 0x00, (byte)0x00, (byte) 0x00, (byte)0x00, (byte) 0x00, (byte)0x01};
        byte[] actual=Encode.Double(input);
        assertArrayEquals(expected, actual);
    }

}