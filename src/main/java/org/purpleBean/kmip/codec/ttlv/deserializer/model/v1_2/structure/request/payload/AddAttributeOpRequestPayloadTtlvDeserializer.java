package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.AddAttributeOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AddAttributeOpRequestPayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<AddAttributeOpRequestPayload, AddAttributeOpRequestPayload.AddAttributeOpRequestPayloadBuilder> {

    public AddAttributeOpRequestPayloadTtlvDeserializer() {
        super(AddAttributeOpRequestPayload.kmipTag);
    }

    @Override
    protected AddAttributeOpRequestPayload.AddAttributeOpRequestPayloadBuilder createBuilder() {
        return AddAttributeOpRequestPayload.builder();
    }

    @Override
    protected void setValue(AddAttributeOpRequestPayload.AddAttributeOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.ATTRIBUTE -> builder.attribute(mapper.readValue(p, Attribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected AddAttributeOpRequestPayload build(AddAttributeOpRequestPayload.AddAttributeOpRequestPayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return AddAttributeOpRequestPayload.encodingType;
    }
}
