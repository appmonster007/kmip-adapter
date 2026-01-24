package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.ManagedObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.PutFunction;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.ReplacedUniqueIdentifier;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.PutOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class PutOpRequestPayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<PutOpRequestPayload, PutOpRequestPayload.PutOpRequestPayloadBuilder> {

    public PutOpRequestPayloadTtlvDeserializer() {
        super(PutOpRequestPayload.kmipTag);
    }

    @Override
    protected PutOpRequestPayload.PutOpRequestPayloadBuilder createBuilder() {
        return PutOpRequestPayload.builder();
    }

    @Override
    protected void setValue(PutOpRequestPayload.PutOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.PUT_FUNCTION -> builder.putFunction(mapper.readValue(p, PutFunction.class));
            case KmipTag.Standard.REPLACED_UNIQUE_IDENTIFIER ->
                    builder.replacedUniqueIdentifier(mapper.readValue(p, ReplacedUniqueIdentifier.class));
            case KmipTag.Standard.ATTRIBUTE -> builder.attribute(mapper.readValue(p, Attribute.class));
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
    protected PutOpRequestPayload build(PutOpRequestPayload.PutOpRequestPayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return PutOpRequestPayload.encodingType;
    }
}
