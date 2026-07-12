package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure.response.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.AsynchronousRequest;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.QueryAsynchronousRequestsOpResponsePayload;

import java.io.IOException;

public class QueryAsynchronousRequestsOpResponsePayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<QueryAsynchronousRequestsOpResponsePayload, QueryAsynchronousRequestsOpResponsePayload.QueryAsynchronousRequestsOpResponsePayloadBuilder> {

    public QueryAsynchronousRequestsOpResponsePayloadJsonDeserializer() {
        super(QueryAsynchronousRequestsOpResponsePayload.kmipTag, QueryAsynchronousRequestsOpResponsePayload.encodingType);
    }

    @Override
    protected QueryAsynchronousRequestsOpResponsePayload.QueryAsynchronousRequestsOpResponsePayloadBuilder createBuilder() {
        return QueryAsynchronousRequestsOpResponsePayload.builder();
    }

    @Override
    protected void setValue(QueryAsynchronousRequestsOpResponsePayload.QueryAsynchronousRequestsOpResponsePayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.ASYNCHRONOUS_REQUEST -> builder.asynchronousRequest(ctxt.readValue(p, AsynchronousRequest.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected QueryAsynchronousRequestsOpResponsePayload build(QueryAsynchronousRequestsOpResponsePayload.QueryAsynchronousRequestsOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}