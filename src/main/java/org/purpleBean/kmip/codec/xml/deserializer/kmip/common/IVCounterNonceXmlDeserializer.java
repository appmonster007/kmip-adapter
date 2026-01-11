package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.IVCounterNonce;

import java.nio.ByteBuffer;

public class IVCounterNonceXmlDeserializer extends AbstractKmipXmlDeserializer<IVCounterNonce, ByteBuffer> {

    public IVCounterNonceXmlDeserializer() {
        super(IVCounterNonce.kmipTag, IVCounterNonce.encodingType, ByteBuffer.class, value -> IVCounterNonce.builder().value(value).build());
    }
}