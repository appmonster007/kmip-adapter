package org.purpleBean.kmip.codec.json.deserializer.model.core.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestBatchItemStructure;
import org.purpleBean.kmip.api.request.RequestHeaderStructure;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestMessage;

import java.io.IOException;

public class SimpleRequestMessageJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<SimpleRequestMessage, SimpleRequestMessage.SimpleRequestMessageBuilder> {

    public SimpleRequestMessageJsonDeserializer() {
        super(SimpleRequestMessage.kmipTag, SimpleRequestMessage.encodingType);
    }

    @Override
    protected SimpleRequestMessage.SimpleRequestMessageBuilder createBuilder() {
        return SimpleRequestMessage.builder();
    }

    @Override
    protected void setValue(SimpleRequestMessage.SimpleRequestMessageBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.REQUEST_HEADER ->
                    builder.requestHeader(ctxt.readValue(p, RequestHeaderStructure.class));
            case KmipTag.Standard.BATCH_ITEM -> {
                if (p.isExpectedStartArrayToken()) {
                    while (p.nextToken() != com.fasterxml.jackson.core.JsonToken.END_ARRAY) { // KMIP JSON profile encodes multiple batch items as a JSON array
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
    protected SimpleRequestMessage build(SimpleRequestMessage.SimpleRequestMessageBuilder builder) {
        return builder.build();
    }
}