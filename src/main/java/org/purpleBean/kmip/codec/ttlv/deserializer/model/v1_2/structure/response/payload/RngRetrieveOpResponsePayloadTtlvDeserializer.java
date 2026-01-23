package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.RngRetrieveOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class RngRetrieveOpResponsePayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<RngRetrieveOpResponsePayload, RngRetrieveOpResponsePayload.RngRetrieveOpResponsePayloadBuilder> {

    public RngRetrieveOpResponsePayloadTtlvDeserializer() {
        super(RngRetrieveOpResponsePayload.kmipTag);
    }

    @Override
    protected RngRetrieveOpResponsePayload.RngRetrieveOpResponsePayloadBuilder createBuilder() {
        return RngRetrieveOpResponsePayload.builder();
    }

    @Override
    protected void setValue(RngRetrieveOpResponsePayload.RngRetrieveOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.DATA)) {
            builder.data(mapper.readValue(p, DataByteString.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected RngRetrieveOpResponsePayload build(RngRetrieveOpResponsePayload.RngRetrieveOpResponsePayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return RngRetrieveOpResponsePayload.encodingType;
    }
}
