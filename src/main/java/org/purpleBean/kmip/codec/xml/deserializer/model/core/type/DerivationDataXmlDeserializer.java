package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.DerivationData;

import java.nio.ByteBuffer;

public class DerivationDataXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DerivationData, ByteBuffer> {

    public DerivationDataXmlDeserializer() {
        super(DerivationData.kmipTag, DerivationData.encodingType, ByteBuffer.class, value -> DerivationData.builder().value(value).build());
    }
}