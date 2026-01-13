package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.DigestValue;

import java.nio.ByteBuffer;

public class DigestValueJsonSerializer extends AbstractKmipDataTypeJsonSerializer<DigestValue, ByteBuffer> {

    public DigestValueJsonSerializer() {
        super(DigestValue::getValue);
    }
}