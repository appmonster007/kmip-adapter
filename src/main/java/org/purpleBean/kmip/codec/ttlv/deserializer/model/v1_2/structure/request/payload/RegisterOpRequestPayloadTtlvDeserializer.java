package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.ManagedObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.RegisterOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class RegisterOpRequestPayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<RegisterOpRequestPayload, RegisterOpRequestPayload.RegisterOpRequestPayloadBuilder> {

    public RegisterOpRequestPayloadTtlvDeserializer() {
        super(RegisterOpRequestPayload.kmipTag);
    }

    @Override
    protected RegisterOpRequestPayload.RegisterOpRequestPayloadBuilder createBuilder() {
        return RegisterOpRequestPayload.builder();
    }

    @Override
    protected void setValue(RegisterOpRequestPayload.RegisterOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.OBJECT_TYPE -> {
                ObjectType objectType = mapper.readValue(p, ObjectType.class);
                mapper.setAttribute("objectType", objectType.getDescription());
                builder.objectType(objectType);
            }
            case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
                    builder.templateAttribute(mapper.readValue(p, TemplateAttribute.class));
            default -> {
                if (ManagedObject.isManagedObject(nodeTag)) {
                    builder.object(mapper.readValue(p, ManagedObject.class));
                } else {
                    throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
                }
            }
        }
    }

    @Override
    protected RegisterOpRequestPayload build(RegisterOpRequestPayload.RegisterOpRequestPayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return RegisterOpRequestPayload.encodingType;
    }
}