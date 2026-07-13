package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.RequestCount;

import java.io.IOException;

public class RequestCountJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<RequestCount, RequestCount.RequestCountBuilder> {

    public RequestCountJsonDeserializer() {
        super(RequestCount.kmipTag, RequestCount.encodingType);
    }

    @Override
    protected RequestCount.RequestCountBuilder createBuilder() {
        return RequestCount.builder();
    }

    @Override
    protected void setValue(RequestCount.RequestCountBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected RequestCount build(RequestCount.RequestCountBuilder builder) {
        return builder.build();
    }
}
