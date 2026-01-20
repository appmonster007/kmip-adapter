package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.CreateOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CreateOpRequestPayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<CreateOpRequestPayload, CreateOpRequestPayload.CreateOpRequestPayloadBuilder> {

    public CreateOpRequestPayloadTtlvDeserializer() {
        super(CreateOpRequestPayload.kmipTag);
    }

    @Override
    protected CreateOpRequestPayload.CreateOpRequestPayloadBuilder createBuilder() {
        return CreateOpRequestPayload.builder();
    }

    @Override
    protected void setValue(CreateOpRequestPayload.CreateOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(mapper.readValue(p, ObjectType.class));
            case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
                    builder.templateAttribute(mapper.readValue(p, TemplateAttribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CreateOpRequestPayload build(CreateOpRequestPayload.CreateOpRequestPayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return CreateOpRequestPayload.encodingType;
    }
}