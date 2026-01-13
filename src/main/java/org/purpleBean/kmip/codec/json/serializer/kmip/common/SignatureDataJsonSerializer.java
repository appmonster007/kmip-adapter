package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.SignatureData;

import java.nio.ByteBuffer;

public class SignatureDataJsonSerializer extends AbstractKmipDataTypeJsonSerializer<SignatureData, ByteBuffer> {

    public SignatureDataJsonSerializer() {
        super(SignatureData::getValue);
    }
}