package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.SignatureData;

import java.nio.ByteBuffer;

public class SignatureDataJsonSerializer extends AbstractKmipJsonSerializer<SignatureData, ByteBuffer> {

    public SignatureDataJsonSerializer() {
        super(SignatureData::getValue);
    }
}