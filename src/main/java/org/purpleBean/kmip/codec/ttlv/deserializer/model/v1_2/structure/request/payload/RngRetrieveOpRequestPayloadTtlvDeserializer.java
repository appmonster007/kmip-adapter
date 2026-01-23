package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.DataLength;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.RngRetrieveOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class RngRetrieveOpRequestPayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<RngRetrieveOpRequestPayload, RngRetrieveOpRequestPayload.RngRetrieveOpRequestPayloadBuilder> {

    public RngRetrieveOpRequestPayloadTtlvDeserializer() {
        super(RngRetrieveOpRequestPayload.kmipTag);
    }

    @Override
    protected RngRetrieveOpRequestPayload.RngRetrieveOpRequestPayloadBuilder createBuilder() {
        return RngRetrieveOpRequestPayload.builder();
    }

    @Override
    protected void setValue(RngRetrieveOpRequestPayload.RngRetrieveOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.DATA_LENGTH)) {
            builder.dataLength(mapper.readValue(p, DataLength.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected RngRetrieveOpRequestPayload build(RngRetrieveOpRequestPayload.RngRetrieveOpRequestPayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return RngRetrieveOpRequestPayload.encodingType;
    }
}
