package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.RecertifyOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class RecertifyOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RecertifyOpResponsePayload, RecertifyOpResponsePayload.RecertifyOpResponsePayloadBuilder> {

    public RecertifyOpResponsePayloadTtlvDeserializer() {
        super(RecertifyOpResponsePayload.kmipTag, RecertifyOpResponsePayload.encodingType);
    }

    @Override
    protected RecertifyOpResponsePayload.RecertifyOpResponsePayloadBuilder createBuilder() {
        return RecertifyOpResponsePayload.builder();
    }

    @Override
    protected void setValue(RecertifyOpResponsePayload.RecertifyOpResponsePayloadBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
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
}
