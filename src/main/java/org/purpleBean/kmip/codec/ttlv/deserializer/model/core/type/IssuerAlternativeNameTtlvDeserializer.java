package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.IssuerAlternativeName;

import java.io.IOException;
import java.nio.ByteBuffer;

public class IssuerAlternativeNameTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<IssuerAlternativeName, IssuerAlternativeName.IssuerAlternativeNameBuilder> {

    public IssuerAlternativeNameTtlvDeserializer() {
        super(IssuerAlternativeName.kmipTag, IssuerAlternativeName.encodingType);
    }

    @Override
    protected IssuerAlternativeName.IssuerAlternativeNameBuilder createBuilder() {
        return IssuerAlternativeName.builder();
    }

    @Override
    protected void setValue(IssuerAlternativeName.IssuerAlternativeNameBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
    }

    @Override
    protected IssuerAlternativeName build(IssuerAlternativeName.IssuerAlternativeNameBuilder builder) {
        return builder.build();
    }
}
