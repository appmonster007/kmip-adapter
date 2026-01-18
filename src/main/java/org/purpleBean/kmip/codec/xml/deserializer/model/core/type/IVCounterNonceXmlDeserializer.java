package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.IVCounterNonce;

import java.nio.ByteBuffer;

public class IVCounterNonceXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<IVCounterNonce, ByteBuffer> {

    public IVCounterNonceXmlDeserializer() {
        super(IVCounterNonce.kmipTag, IVCounterNonce.encodingType, ByteBuffer.class, value -> IVCounterNonce.builder().value(value).build());
    }
}