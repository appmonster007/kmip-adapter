package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.QueryFunction;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.QueryOpRequestPayload;

import java.io.IOException;

public class QueryOpRequestPayloadJsonDeserializer extends AbstractKmipStructureJsonDeserializer<QueryOpRequestPayload, QueryOpRequestPayload.QueryOpRequestPayloadBuilder> {

    public QueryOpRequestPayloadJsonDeserializer() {
        super(QueryOpRequestPayload.kmipTag, QueryOpRequestPayload.encodingType);
    }

    @Override
    protected QueryOpRequestPayload.QueryOpRequestPayloadBuilder createBuilder() {
        return QueryOpRequestPayload.builder();
    }

    @Override
    protected void setValue(QueryOpRequestPayload.QueryOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.QUERY_FUNCTION)) {
            builder.queryFunction(ctxt.readValue(p, QueryFunction.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected QueryOpRequestPayload build(QueryOpRequestPayload.QueryOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
