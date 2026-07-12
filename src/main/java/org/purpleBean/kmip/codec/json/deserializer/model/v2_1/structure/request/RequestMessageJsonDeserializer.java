package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestBatchItemStructure;
import org.purpleBean.kmip.api.request.RequestHeaderStructure;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.request.RequestMessage;

import java.io.IOException;

public class RequestMessageJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<RequestMessage, RequestMessage.RequestMessageBuilder> {

    public RequestMessageJsonDeserializer() {
        super(RequestMessage.kmipTag, RequestMessage.encodingType);
    }

    @Override
    protected RequestMessage.RequestMessageBuilder createBuilder() {
        return RequestMessage.builder();
    }

    @Override
    protected void setValue(RequestMessage.RequestMessageBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.REQUEST_HEADER ->
                    builder.requestHeader(ctxt.readValue(p, RequestHeaderStructure.class));
            case KmipTag.Standard.BATCH_ITEM -> {
                if (p.isExpectedStartArrayToken()) {
                    while (p.nextToken() != JsonToken.END_ARRAY) {
                        try {
                            builder.requestBatchItem(ctxt.readValue(p, RequestBatchItemStructure.class));
                            builder.requestBatchItemError(null);
                        } catch (Exception e) {
                            builder.requestBatchItem(null);
                            builder.requestBatchItemError(e);
                        }
                    }
                } else {
                    try {
                        builder.requestBatchItem(ctxt.readValue(p, RequestBatchItemStructure.class));
                        builder.requestBatchItemError(null);
                    } catch (Exception e) {
                        builder.requestBatchItem(null);
                        builder.requestBatchItemError(e);
                    }
                }
            }
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected RequestMessage build(RequestMessage.RequestMessageBuilder builder) {
        return builder.build();
    }
}
