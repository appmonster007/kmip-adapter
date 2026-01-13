package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;
import org.purpleBean.kmip.common.structure.request.SimpleRequestMessage;

import java.io.IOException;

public class SimpleRequestMessageXmlDeserializer extends AbstractKmipStructureXmlDeserializer<SimpleRequestMessage, SimpleRequestMessage.SimpleRequestMessageBuilder> {

    public SimpleRequestMessageXmlDeserializer() {
        super(SimpleRequestMessage.kmipTag);
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