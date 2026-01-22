package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.ObtainLeaseOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ObtainLeaseOpRequestPayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<ObtainLeaseOpRequestPayload, ObtainLeaseOpRequestPayload.ObtainLeaseOpRequestPayloadBuilder> {

    public ObtainLeaseOpRequestPayloadTtlvDeserializer() {
        super(ObtainLeaseOpRequestPayload.kmipTag);
    }

    @Override
    protected ObtainLeaseOpRequestPayload.ObtainLeaseOpRequestPayloadBuilder createBuilder() {
        return ObtainLeaseOpRequestPayload.builder();
    }

    @Override
    protected void setValue(ObtainLeaseOpRequestPayload.ObtainLeaseOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
            builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ObtainLeaseOpRequestPayload build(ObtainLeaseOpRequestPayload.ObtainLeaseOpRequestPayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return ObtainLeaseOpRequestPayload.encodingType;
    }
}
