package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.Offset;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.ReKeyOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ReKeyOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ReKeyOpRequestPayload, ReKeyOpRequestPayload.ReKeyOpRequestPayloadBuilder> {

    public ReKeyOpRequestPayloadTtlvDeserializer() {
        super(ReKeyOpRequestPayload.kmipTag, ReKeyOpRequestPayload.encodingType);
    }

    @Override
    protected ReKeyOpRequestPayload.ReKeyOpRequestPayloadBuilder createBuilder() {
        return ReKeyOpRequestPayload.builder();
    }

    @Override
    protected void setValue(ReKeyOpRequestPayload.ReKeyOpRequestPayloadBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.OFFSET -> builder.offset(mapper.readValue(p, Offset.class));
            case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
                    builder.templateAttribute(mapper.readValue(p, TemplateAttribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ReKeyOpRequestPayload build(ReKeyOpRequestPayload.ReKeyOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
