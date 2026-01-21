package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.RecertifyOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class RecertifyOpResponsePayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<RecertifyOpResponsePayload, RecertifyOpResponsePayload.RecertifyOpResponsePayloadBuilder> {

    public RecertifyOpResponsePayloadTtlvDeserializer() {
        super(RecertifyOpResponsePayload.kmipTag);
    }

    @Override
    protected RecertifyOpResponsePayload.RecertifyOpResponsePayloadBuilder createBuilder() {
        return RecertifyOpResponsePayload.builder();
    }

    @Override
    protected void setValue(RecertifyOpResponsePayload.RecertifyOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
                    builder.templateAttribute(mapper.readValue(p, TemplateAttribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected RecertifyOpResponsePayload build(RecertifyOpResponsePayload.RecertifyOpResponsePayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return RecertifyOpResponsePayload.encodingType;
    }
}