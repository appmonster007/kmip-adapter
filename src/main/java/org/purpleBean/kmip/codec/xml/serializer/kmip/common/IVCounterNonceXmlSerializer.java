package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.IVCounterNonce;

import java.nio.ByteBuffer;

public class IVCounterNonceXmlSerializer extends AbstractKmipXmlSerializer<IVCounterNonce, ByteBuffer> {

    public IVCounterNonceXmlSerializer() {
        super(IVCounterNonce::getValue);
    }
}