package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.IssuerAlternativeName;

import java.nio.ByteBuffer;

public class IssuerAlternativeNameJsonSerializer extends AbstractKmipDataTypeJsonSerializer<IssuerAlternativeName, ByteBuffer> {

    public IssuerAlternativeNameJsonSerializer() {
        super(IssuerAlternativeName::getValue);
    }
}