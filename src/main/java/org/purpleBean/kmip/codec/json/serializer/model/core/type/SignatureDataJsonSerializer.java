package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.SignatureData;

import java.nio.ByteBuffer;

public class SignatureDataJsonSerializer extends AbstractKmipDataTypeJsonSerializer<SignatureData, ByteBuffer> {

    public SignatureDataJsonSerializer() {
        super(SignatureData::getValue);
    }
}