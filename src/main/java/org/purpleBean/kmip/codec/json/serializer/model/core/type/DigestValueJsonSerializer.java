package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.DigestValue;

import java.nio.ByteBuffer;

public class DigestValueJsonSerializer extends AbstractKmipDataTypeJsonSerializer<DigestValue, ByteBuffer> {

    public DigestValueJsonSerializer() {
        super(DigestValue::getValue);
    }
}