package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.request.payload;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.QueryAsynchronousRequestsOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class QueryAsynchronousRequestsOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<QueryAsynchronousRequestsOpRequestPayload, QueryAsynchronousRequestsOpRequestPayload.QueryAsynchronousRequestsOpRequestPayloadBuilder> {

    public QueryAsynchronousRequestsOpRequestPayloadTtlvDeserializer() {
        super(QueryAsynchronousRequestsOpRequestPayload.kmipTag, QueryAsynchronousRequestsOpRequestPayload.encodingType);
    }

    @Override
    protected QueryAsynchronousRequestsOpRequestPayload.QueryAsynchronousRequestsOpRequestPayloadBuilder createBuilder() {
        return QueryAsynchronousRequestsOpRequestPayload.builder();
    }

    @Override
    protected void setValue(QueryAsynchronousRequestsOpRequestPayload.QueryAsynchronousRequestsOpRequestPayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        // No fields per KMIP spec
    }

    @Override
    protected QueryAsynchronousRequestsOpRequestPayload build(QueryAsynchronousRequestsOpRequestPayload.QueryAsynchronousRequestsOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}