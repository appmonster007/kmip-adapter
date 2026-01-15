package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.MACSignature;

import java.nio.ByteBuffer;

public class MACSignatureJsonSerializer extends AbstractKmipDataTypeJsonSerializer<MACSignature, ByteBuffer> {

    public MACSignatureJsonSerializer() {
        super(MACSignature::getValue);
    }
}