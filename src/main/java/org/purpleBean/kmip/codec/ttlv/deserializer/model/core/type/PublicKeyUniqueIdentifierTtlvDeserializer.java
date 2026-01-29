package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.PublicKeyUniqueIdentifier;

import java.io.IOException;
import java.nio.ByteBuffer;

public class PublicKeyUniqueIdentifierTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PublicKeyUniqueIdentifier, PublicKeyUniqueIdentifier.PublicKeyUniqueIdentifierBuilder> {

    public PublicKeyUniqueIdentifierTtlvDeserializer() {
        super(PublicKeyUniqueIdentifier.kmipTag, PublicKeyUniqueIdentifier.encodingType);
    }

    @Override
    protected PublicKeyUniqueIdentifier.PublicKeyUniqueIdentifierBuilder createBuilder() {
        return PublicKeyUniqueIdentifier.builder();
    }

    @Override
    protected void setValue(PublicKeyUniqueIdentifier.PublicKeyUniqueIdentifierBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, String.class));
    }

    @Override
    protected PublicKeyUniqueIdentifier build(PublicKeyUniqueIdentifier.PublicKeyUniqueIdentifierBuilder builder) {
        return builder.build();
    }
}
