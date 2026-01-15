package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.IVCounterNonce;

import java.nio.ByteBuffer;

public class IVCounterNonceXmlSerializer extends AbstractKmipDataTypeXmlSerializer<IVCounterNonce, ByteBuffer> {

    public IVCounterNonceXmlSerializer() {
        super(IVCounterNonce::getValue);
    }
}