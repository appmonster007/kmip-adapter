package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.PrivateKeyUniqueIdentifier;

import java.io.IOException;
import java.nio.ByteBuffer;

public class PrivateKeyUniqueIdentifierTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PrivateKeyUniqueIdentifier, PrivateKeyUniqueIdentifier.PrivateKeyUniqueIdentifierBuilder> {

    public PrivateKeyUniqueIdentifierTtlvDeserializer() {
        super(PrivateKeyUniqueIdentifier.kmipTag, PrivateKeyUniqueIdentifier.encodingType);
    }

    @Override
    protected PrivateKeyUniqueIdentifier.PrivateKeyUniqueIdentifierBuilder createBuilder() {
        return PrivateKeyUniqueIdentifier.builder();
    }

    @Override
    protected void setValue(PrivateKeyUniqueIdentifier.PrivateKeyUniqueIdentifierBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, String.class));
    }

    @Override
    protected PrivateKeyUniqueIdentifier build(PrivateKeyUniqueIdentifier.PrivateKeyUniqueIdentifierBuilder builder) {
        return builder.build();
    }
}