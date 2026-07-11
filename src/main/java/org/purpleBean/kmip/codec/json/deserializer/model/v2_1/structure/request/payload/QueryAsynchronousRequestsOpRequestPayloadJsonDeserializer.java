package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.QueryAsynchronousRequestsOpRequestPayload;

import java.io.IOException;

public class QueryAsynchronousRequestsOpRequestPayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<QueryAsynchronousRequestsOpRequestPayload, QueryAsynchronousRequestsOpRequestPayload.QueryAsynchronousRequestsOpRequestPayloadBuilder> {

    public QueryAsynchronousRequestsOpRequestPayloadJsonDeserializer() {
        super(QueryAsynchronousRequestsOpRequestPayload.kmipTag, QueryAsynchronousRequestsOpRequestPayload.encodingType);
    }

    @Override
    protected QueryAsynchronousRequestsOpRequestPayload.QueryAsynchronousRequestsOpRequestPayloadBuilder createBuilder() {
        return QueryAsynchronousRequestsOpRequestPayload.builder();
    }

    @Override
    protected void setValue(QueryAsynchronousRequestsOpRequestPayload.QueryAsynchronousRequestsOpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        // No fields per KMIP spec
    }

    @Override
    protected QueryAsynchronousRequestsOpRequestPayload build(QueryAsynchronousRequestsOpRequestPayload.QueryAsynchronousRequestsOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}