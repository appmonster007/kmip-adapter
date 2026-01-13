package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.IssuerAlternativeName;

import java.nio.ByteBuffer;

public class IssuerAlternativeNameJsonSerializer extends AbstractKmipDataTypeJsonSerializer<IssuerAlternativeName, ByteBuffer> {

    public IssuerAlternativeNameJsonSerializer() {
        super(IssuerAlternativeName::getValue);
    }
}