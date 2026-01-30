package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.QueryFunction;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.QueryOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class QueryOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<QueryOpRequestPayload, QueryOpRequestPayload.QueryOpRequestPayloadBuilder> {

    public QueryOpRequestPayloadTtlvDeserializer() {
        super(QueryOpRequestPayload.kmipTag, QueryOpRequestPayload.encodingType);
    }

    @Override
    protected QueryOpRequestPayload.QueryOpRequestPayloadBuilder createBuilder() {
        return QueryOpRequestPayload.builder();
    }

    @Override
    protected void setValue(QueryOpRequestPayload.QueryOpRequestPayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        if (nodeTag.equals(KmipTag.Standard.QUERY_FUNCTION)) {
            builder.queryFunction(mapper.readValue(p, QueryFunction.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected QueryOpRequestPayload build(QueryOpRequestPayload.QueryOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
