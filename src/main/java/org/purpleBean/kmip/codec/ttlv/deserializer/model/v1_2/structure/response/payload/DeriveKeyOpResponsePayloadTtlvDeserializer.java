package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.DeriveKeyOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DeriveKeyOpResponsePayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<DeriveKeyOpResponsePayload, DeriveKeyOpResponsePayload.DeriveKeyOpResponsePayloadBuilder> {

    public DeriveKeyOpResponsePayloadTtlvDeserializer() {
        super(DeriveKeyOpResponsePayload.kmipTag);
    }

    @Override
    protected DeriveKeyOpResponsePayload.DeriveKeyOpResponsePayloadBuilder createBuilder() {
        return DeriveKeyOpResponsePayload.builder();
    }

    @Override
    protected void setValue(DeriveKeyOpResponsePayload.DeriveKeyOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
                    builder.templateAttribute(mapper.readValue(p, TemplateAttribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DeriveKeyOpResponsePayload build(DeriveKeyOpResponsePayload.DeriveKeyOpResponsePayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return DeriveKeyOpResponsePayload.encodingType;
    }
}
