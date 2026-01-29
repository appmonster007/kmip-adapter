package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.KeyPartIdentifier;

import java.io.IOException;
import java.nio.ByteBuffer;

public class KeyPartIdentifierTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<KeyPartIdentifier, KeyPartIdentifier.KeyPartIdentifierBuilder> {

    public KeyPartIdentifierTtlvDeserializer() {
        super(KeyPartIdentifier.kmipTag, KeyPartIdentifier.encodingType);
    }

    @Override
    protected KeyPartIdentifier.KeyPartIdentifierBuilder createBuilder() {
        return KeyPartIdentifier.builder();
    }

    @Override
    protected void setValue(KeyPartIdentifier.KeyPartIdentifierBuilder builder, byte[] tagBytes, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Integer.class));
    }

    @Override
    protected KeyPartIdentifier build(KeyPartIdentifier.KeyPartIdentifierBuilder builder) {
        return builder.build();
    }
}
