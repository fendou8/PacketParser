package com.legendmohe.packageparser;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    public static byte[] hexToBytes(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    @Test
    public void addition_isCorrect() throws Exception {
        String data = "AA11220008556677889911223344556677";
        byte[] bytes = hexToBytes(data);
        TargetObject targetObject = TargetObjectPacketParser.parse(bytes);
        assertEquals((byte) 0xAA, targetObject.header);
        assertEquals(0x1122, targetObject.cmd);
        assertEquals(0x0008, targetObject.len);
        assertEquals(0x5566, targetObject.seq);
        assertArrayEquals(new byte[]{0x77, (byte) 0x88, (byte) 0x99, 0x11, 0x22, 0x33}, targetObject.mac);
        assertArrayEquals(new byte[]{0x44, 0x55}, targetObject.data);
        assertEquals(0x66, targetObject.check);
        assertEquals(0x77, targetObject.tail);

        byte[] toBytes = TargetObjectPacketParser.toBytes(targetObject);
        assertArrayEquals(bytes, toBytes);
    }

    @Test
    public void addition_isCorrect2() throws Exception {
        String data = "AA11220008556677889911223344556677";
        byte[] bytes = hexToBytes(data);
        ChildObject childObject = ChildObjectPacketParser.parse(bytes);
        assertEquals((byte) 0xAA, childObject.header);
        assertEquals(0x1122, childObject.cmd);
        assertEquals(0x0008, childObject.len);
        assertEquals(0x5566, childObject.seq);
        assertArrayEquals(new byte[]{0x77, (byte) 0x88, (byte) 0x99, 0x11, 0x22, 0x33}, childObject.mac);
        assertArrayEquals(new byte[]{0x44, 0x55}, childObject.data);
        assertEquals(0x66, childObject.check);
        assertEquals(0x77, childObject.tail);

        byte[] toBytes = ChildObjectPacketParser.toBytes(childObject);
        assertArrayEquals(bytes, toBytes);
    }

    @Test
    public void addition_isCorrect3() throws Exception {
        String data = "AA11220008556677889911223344556677";
        byte[] bytes = hexToBytes(data);
        ChildObject childObject = ChildObjectPacketParser.parse(bytes);
        assertEquals((byte) 0xAA, childObject.header);
        assertEquals(0x1122, childObject.cmd);
        assertEquals(0x0008, childObject.len);
        assertEquals(0x5566, childObject.seq);
        assertArrayEquals(new byte[]{0x77, (byte) 0x88, (byte) 0x99, 0x11, 0x22, 0x33}, childObject.mac);
        assertArrayEquals(new byte[]{0x44, 0x55}, childObject.data);
        assertEquals(0x66, childObject.check);
        assertEquals(0x77, childObject.tail);

        byte[] toBytes = ChildObjectPacketParser.toBytes(childObject);
        assertArrayEquals(bytes, toBytes);
    }

    @Test
    public void addition_isCorrect4() throws Exception {
        String data = "0a0007BB00030101010c";
        byte[] bytes = hexToBytes(data);
        TLVHolderHeaderObject tlvHolderObject = TLVHolderHeaderObjectPacketParser.parse(bytes);
        assertEquals((byte) 0x0A, tlvHolderObject.type);
        assertEquals(0x0007, tlvHolderObject.length);
        assertEquals((byte) 0xBB, tlvHolderObject.tlvObject.type);
        assertEquals(0x0003, tlvHolderObject.tlvObject.length);
        assertArrayEquals(new byte[]{0x01, 0x01, 0x01}, tlvHolderObject.tlvObject.value);
        assertEquals((byte) 0x0c, tlvHolderObject.a);

        byte[] toBytes = TLVHolderHeaderObjectPacketParser.toBytes(tlvHolderObject);
        assertArrayEquals(bytes, toBytes);
    }
}