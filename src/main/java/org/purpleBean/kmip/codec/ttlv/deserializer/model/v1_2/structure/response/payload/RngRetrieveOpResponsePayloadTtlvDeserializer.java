package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.RngRetrieveOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class RngRetrieveOpResponsePayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RngRetrieveOpResponsePayload, RngRetrieveOpResponsePayload.RngRetrieveOpResponsePayloadBuilder> {

    public RngRetrieveOpResponsePayloadTtlvDeserializer() {
        super(RngRetrieveOpResponsePayload.kmipTag, RngRetrieveOpResponsePayload.encodingType);
    }

    @Override
    protected RngRetrieveOpResponsePayload.RngRetrieveOpResponsePayloadBuilder createBuilder() {
        return RngRetrieveOpResponsePayload.builder();
    }

    @Override
    protected void setValue(RngRetrieveOpResponsePayload.RngRetrieveOpResponsePayloadBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
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
}
