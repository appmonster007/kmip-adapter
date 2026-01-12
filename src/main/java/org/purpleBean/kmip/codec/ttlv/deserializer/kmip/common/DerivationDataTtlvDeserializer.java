package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.DerivationData;

import java.nio.ByteBuffer;

public class DerivationDataTtlvDeserializer extends AbstractKmipTtlvDeserializer<DerivationData, ByteBuffer> {

    public DerivationDataTtlvDeserializer() {
        super(DerivationData.kmipTag, DerivationData.encodingType, ByteBuffer.class, value -> DerivationData.builder().value(value).build());
    }
}