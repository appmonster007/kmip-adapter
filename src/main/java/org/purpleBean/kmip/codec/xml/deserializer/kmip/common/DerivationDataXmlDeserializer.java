package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.DerivationData;

import java.nio.ByteBuffer;

public class DerivationDataXmlDeserializer extends AbstractKmipXmlDeserializer<DerivationData, ByteBuffer> {

    public DerivationDataXmlDeserializer() {
        super(DerivationData.kmipTag, DerivationData.encodingType, ByteBuffer.class, value -> DerivationData.builder().value(value).build());
    }
}