package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.PrimeFieldSize;
import org.purpleBean.kmip.model.core.type.SplitKeyParts;
import org.purpleBean.kmip.model.core.type.SplitKeyThreshold;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.CreateSplitKeyOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CreateSplitKeyOpRequestPayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<CreateSplitKeyOpRequestPayload, CreateSplitKeyOpRequestPayload.CreateSplitKeyOpRequestPayloadBuilder> {

    public CreateSplitKeyOpRequestPayloadTtlvDeserializer() {
        super(CreateSplitKeyOpRequestPayload.kmipTag);
    }

    @Override
    protected CreateSplitKeyOpRequestPayload.CreateSplitKeyOpRequestPayloadBuilder createBuilder() {
        return CreateSplitKeyOpRequestPayload.builder();
    }

    @Override
    protected void setValue(CreateSplitKeyOpRequestPayload.CreateSplitKeyOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(mapper.readValue(p, ObjectType.class));
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.SPLIT_KEY_PARTS -> builder.splitKeyParts(mapper.readValue(p, SplitKeyParts.class));
            case KmipTag.Standard.SPLIT_KEY_THRESHOLD ->
                    builder.splitKeyThreshold(mapper.readValue(p, SplitKeyThreshold.class));
            case KmipTag.Standard.SPLIT_KEY_METHOD -> builder.splitKeyMethod(mapper.readValue(p, SplitKeyMethod.class));
            case KmipTag.Standard.PRIME_FIELD_SIZE -> builder.primeFieldSize(mapper.readValue(p, PrimeFieldSize.class));
            case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
                    builder.templateAttribute(mapper.readValue(p, TemplateAttribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CreateSplitKeyOpRequestPayload build(CreateSplitKeyOpRequestPayload.CreateSplitKeyOpRequestPayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return CreateSplitKeyOpRequestPayload.encodingType;
    }
}
