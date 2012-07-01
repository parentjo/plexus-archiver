package org.codehaus.plexus.archiver.zip;

/*
 * Copyright  2001-2002,2004 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

/**
 * Utility class that represents a four byte integer with conversion
 * rules for the big endian byte order of ZIP files.
 *
 * @version $Revision$ $Date$
 *          from org.apache.ant.tools.zip.ZipLong v1.10
 */
public final class ZipLong
    implements Cloneable
{

    private long value;

    /**
     * Create instance from a number.
     *
     * @since 1.1
     */
    public ZipLong( long value )
    {
        this.value = value;
    }

    /**
     * Create instance from bytes.
     *
     * @since 1.1
     */
    public ZipLong( byte[] bytes )
    {
        this( bytes, 0 );
    }

    /**
     * Create instance from the four bytes starting at offset.
     *
     * @since 1.1
     */
    public ZipLong( byte[] bytes, int offset )
    {
        value = convert(bytes, offset);
    }

    /**
     * Get value as two bytes in big endian byte order.
     *
     * @since 1.1
     */
    public byte[] getBytes()
    {
        return bytes(value);
    }

    /**
     * Get value as Java int.
     *
     * @since 1.1
     */
    public long getValue()
    {
        return value;
    }

    /**
     * Override to make two instances with same value equal.
     *
     * @since 1.1
     */
    public boolean equals( Object o )
    {
        if ( o == null || !( o instanceof ZipLong ) )
        {
            return false;
        }
        return value == ( (ZipLong) o ).getValue();
    }

    /**
     * Override to make two instances with same value equal.
     *
     * @since 1.1
     */
    public int hashCode()
    {
        return (int) value;
    }

    public static long convert(byte[] bytes) {
        return convert(bytes, 0);
    }

    public static long convert(byte[] bytes, int offset) {
        long value = (bytes[offset + 3] << 24) & 0xFF000000L;
        value += (bytes[offset + 2] << 16) & 0xFF0000;
        value += (bytes[offset + 1] << 8) & 0xFF00;
        value += (bytes[offset] & 0xFF);

        return value;
    }

    public static byte[] bytes(long value) {
        return bytes(value, new byte[4]);
    }

    public static byte[] bytes(long value, byte[] result) {
        result[0] = (byte) ((value & 0xFF));
        result[1] = (byte) ((value & 0xFF00) >> 8);
        result[2] = (byte) ((value & 0xFF0000) >> 16);
        result[3] = (byte) ((value & 0xFF000000l) >> 24);
        return result;
    }
}
