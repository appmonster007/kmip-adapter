package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.DigestValue;

import java.nio.ByteBuffer;

public class DigestValueJsonSerializer extends AbstractKmipJsonSerializer<DigestValue, ByteBuffer> {

    public DigestValueJsonSerializer() {
        super(DigestValue::getValue);
    }
}