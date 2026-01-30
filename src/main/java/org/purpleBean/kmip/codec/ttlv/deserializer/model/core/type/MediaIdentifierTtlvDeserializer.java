package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.MediaIdentifier;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MediaIdentifierTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<MediaIdentifier, MediaIdentifier.MediaIdentifierBuilder> {

    public MediaIdentifierTtlvDeserializer() {
        super(MediaIdentifier.kmipTag, MediaIdentifier.encodingType);
    }

    @Override
    protected MediaIdentifier.MediaIdentifierBuilder createBuilder() {
        return MediaIdentifier.builder();
    }

    @Override
    protected void setValue(MediaIdentifier.MediaIdentifierBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, String.class));
    }

    @Override
    protected MediaIdentifier build(MediaIdentifier.MediaIdentifierBuilder builder) {
        return builder.build();
    }
}
