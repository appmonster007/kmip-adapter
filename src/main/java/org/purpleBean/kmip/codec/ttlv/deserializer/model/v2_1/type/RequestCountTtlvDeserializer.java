package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.RequestCount;

import java.io.IOException;
import java.nio.ByteBuffer;

public class RequestCountTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RequestCount, RequestCount.RequestCountBuilder> {

    public RequestCountTtlvDeserializer() {
        super(RequestCount.kmipTag, RequestCount.encodingType);
    }

    @Override
    protected RequestCount.RequestCountBuilder createBuilder() {
        return RequestCount.builder();
    }

    @Override
    protected void setValue(RequestCount.RequestCountBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, Integer.class));
    }

    @Override
    protected RequestCount build(RequestCount.RequestCountBuilder builder) {
        return builder.build();
    }
}
