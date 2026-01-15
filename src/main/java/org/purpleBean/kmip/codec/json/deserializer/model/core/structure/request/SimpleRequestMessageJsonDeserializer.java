package org.purpleBean.kmip.codec.json.deserializer.model.core.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestBatchItem;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestHeader;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestMessage;

import java.io.IOException;

public class SimpleRequestMessageJsonDeserializer extends AbstractKmipStructureJsonDeserializer<SimpleRequestMessage, SimpleRequestMessage.SimpleRequestMessageBuilder> {

    public SimpleRequestMessageJsonDeserializer() {
        super(SimpleRequestMessage.kmipTag, SimpleRequestMessage.encodingType);
    }

    @Override
    protected SimpleRequestMessage.SimpleRequestMessageBuilder createBuilder() {
        return SimpleRequestMessage.builder();
    }

    @Override
    protected void setValue(SimpleRequestMessage.SimpleRequestMessageBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.REQUEST_HEADER -> builder.requestHeader(ctxt.readValue(p, SimpleRequestHeader.class));
            case KmipTag.Standard.BATCH_ITEM -> {
                if (p.isExpectedStartArrayToken()) {
                    while (p.nextToken() != com.fasterxml.jackson.core.JsonToken.END_ARRAY) {
                        try {
                            builder.requestBatchItem(ctxt.readValue(p, SimpleRequestBatchItem.class));
                        } catch (Exception e) {
                            builder.requestBatchItemError(e);
                        }
                    }
                } else {
                    try {
                        builder.requestBatchItem(ctxt.readValue(p, SimpleRequestBatchItem.class));
                    } catch (Exception e) {
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