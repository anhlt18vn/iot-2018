package dev.insight.benchmark.tool;

import java.nio.ByteBuffer;

/**
 * dev.insight.benchmark.tool
 * <p>
 * TODO: Add class description
 * <p>
 * Author:  Anh Le_Tuan
 * Email:   anh.letuan@insight-centre.org
 * <p>
 * Date:  09/01/18.
 */
public class TestByteBuffer
{
    public static void main(String[] args)
    {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);

        byteBuffer.put((byte) 1);
        byteBuffer.put((byte) 1);
        byteBuffer.put((byte) 1);
        byteBuffer.put((byte) 1);

        ByteBuffer byteBuffer1 = ByteBuffer.allocate(2);
        byteBuffer1.put((byte) 2);
        byteBuffer1.put((byte) 2);

        byteBuffer1.rewind();
        byteBuffer.position(1);
        byteBuffer.put(byteBuffer1);

        for (byte b:byteBuffer.array())
        {
            System.out.println(b);
        }

    }
}
