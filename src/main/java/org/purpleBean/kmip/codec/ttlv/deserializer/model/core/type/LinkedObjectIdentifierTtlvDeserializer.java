package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.LinkedObjectIdentifier;

import java.io.IOException;
import java.nio.ByteBuffer;

public class LinkedObjectIdentifierTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<LinkedObjectIdentifier, LinkedObjectIdentifier.LinkedObjectIdentifierBuilder> {

    public LinkedObjectIdentifierTtlvDeserializer() {
        super(LinkedObjectIdentifier.kmipTag, LinkedObjectIdentifier.encodingType);
    }

    @Override
    protected LinkedObjectIdentifier.LinkedObjectIdentifierBuilder createBuilder() {
        return LinkedObjectIdentifier.builder();
    }

    @Override
    protected void setValue(LinkedObjectIdentifier.LinkedObjectIdentifierBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, String.class));
    }

    @Override
    protected LinkedObjectIdentifier build(LinkedObjectIdentifier.LinkedObjectIdentifierBuilder builder) {
        return builder.build();
    }
}
