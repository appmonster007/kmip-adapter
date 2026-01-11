package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.DerivationData;

import java.nio.ByteBuffer;

public class DerivationDataJsonDeserializer extends AbstractKmipJsonDeserializer<DerivationData, ByteBuffer> {

    public DerivationDataJsonDeserializer() {
        super(DerivationData.kmipTag, DerivationData.encodingType, ByteBuffer.class, value -> DerivationData.builder().value(value).build());
    }
}