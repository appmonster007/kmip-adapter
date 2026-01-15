package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.IssuerAlternativeName;

import java.nio.ByteBuffer;

public class IssuerAlternativeNameTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<IssuerAlternativeName, ByteBuffer> {

    public IssuerAlternativeNameTtlvSerializer() {
        super(IssuerAlternativeName::getValue);
    }
}