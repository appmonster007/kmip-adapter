package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.QueryFunction;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.QueryOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class QueryOpRequestPayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<QueryOpRequestPayload, QueryOpRequestPayload.QueryOpRequestPayloadBuilder> {

    public QueryOpRequestPayloadTtlvDeserializer() {
        super(QueryOpRequestPayload.kmipTag);
    }

    @Override
    protected QueryOpRequestPayload.QueryOpRequestPayloadBuilder createBuilder() {
        return QueryOpRequestPayload.builder();
    }

    @Override
    protected void setValue(QueryOpRequestPayload.QueryOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
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

    @Override
    protected EncodingType getEncodingType() {
        return QueryOpRequestPayload.encodingType;
    }
}
