package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.JoinSplitKeyOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class JoinSplitKeyOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<JoinSplitKeyOpResponsePayload, JoinSplitKeyOpResponsePayload.JoinSplitKeyOpResponsePayloadBuilder> {

    public JoinSplitKeyOpResponsePayloadTtlvDeserializer() {
        super(JoinSplitKeyOpResponsePayload.kmipTag, JoinSplitKeyOpResponsePayload.encodingType);
    }

    @Override
    protected JoinSplitKeyOpResponsePayload.JoinSplitKeyOpResponsePayloadBuilder createBuilder() {
        return JoinSplitKeyOpResponsePayload.builder();
    }

    @Override
    protected void setValue(JoinSplitKeyOpResponsePayload.JoinSplitKeyOpResponsePayloadBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        switch (nodeTag) {
            case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(mapper.readValue(p, ObjectType.class));
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
                    builder.templateAttribute(mapper.readValue(p, TemplateAttribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected JoinSplitKeyOpResponsePayload build(JoinSplitKeyOpResponsePayload.JoinSplitKeyOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
