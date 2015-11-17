package org.codehaus.plexus.archiver.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by jparent on 29/06/2015.
 */
public class FastByteArrayOutputStream extends ByteArrayOutputStream {

    public ByteArrayInputStream toInputStream() {
        return new ByteArrayInputStream(buf, 0, size());
    }
}