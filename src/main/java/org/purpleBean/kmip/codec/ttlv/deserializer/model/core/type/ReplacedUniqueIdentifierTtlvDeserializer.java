package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.ReplacedUniqueIdentifier;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ReplacedUniqueIdentifierTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ReplacedUniqueIdentifier, ReplacedUniqueIdentifier.ReplacedUniqueIdentifierBuilder> {

    public ReplacedUniqueIdentifierTtlvDeserializer() {
        super(ReplacedUniqueIdentifier.kmipTag, ReplacedUniqueIdentifier.encodingType);
    }

    @Override
    protected ReplacedUniqueIdentifier.ReplacedUniqueIdentifierBuilder createBuilder() {
        return ReplacedUniqueIdentifier.builder();
    }

    @Override
    protected void setValue(ReplacedUniqueIdentifier.ReplacedUniqueIdentifierBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, String.class));
    }

    @Override
    protected ReplacedUniqueIdentifier build(ReplacedUniqueIdentifier.ReplacedUniqueIdentifierBuilder builder) {
        return builder.build();
    }
}
