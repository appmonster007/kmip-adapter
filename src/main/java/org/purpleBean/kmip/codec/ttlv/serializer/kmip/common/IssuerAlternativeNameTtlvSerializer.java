package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.IssuerAlternativeName;

import java.nio.ByteBuffer;

public class IssuerAlternativeNameTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<IssuerAlternativeName, ByteBuffer> {

    public IssuerAlternativeNameTtlvSerializer() {
        super(IssuerAlternativeName::getValue);
    }
}