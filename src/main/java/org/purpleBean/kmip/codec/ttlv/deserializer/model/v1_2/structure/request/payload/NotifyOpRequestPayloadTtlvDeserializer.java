package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.NotifyOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class NotifyOpRequestPayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<NotifyOpRequestPayload, NotifyOpRequestPayload.NotifyOpRequestPayloadBuilder> {

    public NotifyOpRequestPayloadTtlvDeserializer() {
        super(NotifyOpRequestPayload.kmipTag);
    }

    @Override
    protected NotifyOpRequestPayload.NotifyOpRequestPayloadBuilder createBuilder() {
        return NotifyOpRequestPayload.builder();
    }

    @Override
    protected void setValue(NotifyOpRequestPayload.NotifyOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.ATTRIBUTE -> builder.attribute(mapper.readValue(p, Attribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected NotifyOpRequestPayload build(NotifyOpRequestPayload.NotifyOpRequestPayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return NotifyOpRequestPayload.encodingType;
    }
}
