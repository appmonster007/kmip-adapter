package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.IssuerAlternativeName;

import java.nio.ByteBuffer;

public class IssuerAlternativeNameJsonSerializer extends AbstractKmipJsonSerializer<IssuerAlternativeName, ByteBuffer> {

    public IssuerAlternativeNameJsonSerializer() {
        super(IssuerAlternativeName::getValue);
    }
}