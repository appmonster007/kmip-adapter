package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.DerivationData;

import java.nio.ByteBuffer;

public class DerivationDataTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DerivationData, ByteBuffer> {

    public DerivationDataTtlvDeserializer() {
        super(DerivationData.kmipTag, DerivationData.encodingType, ByteBuffer.class, value -> DerivationData.builder().value(value).build());
    }
}