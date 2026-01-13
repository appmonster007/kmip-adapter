package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.IVCounterNonce;

import java.nio.ByteBuffer;

public class IVCounterNonceXmlSerializer extends AbstractKmipDataTypeXmlSerializer<IVCounterNonce, ByteBuffer> {

    public IVCounterNonceXmlSerializer() {
        super(IVCounterNonce::getValue);
    }
}