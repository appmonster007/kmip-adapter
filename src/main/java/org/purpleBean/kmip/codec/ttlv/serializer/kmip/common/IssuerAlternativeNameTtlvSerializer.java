package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.IssuerAlternativeName;

import java.nio.ByteBuffer;

public class IssuerAlternativeNameTtlvSerializer extends AbstractKmipTtlvSerializer<IssuerAlternativeName, ByteBuffer> {

    public IssuerAlternativeNameTtlvSerializer() {
        super(IssuerAlternativeName::getValue);
    }
}