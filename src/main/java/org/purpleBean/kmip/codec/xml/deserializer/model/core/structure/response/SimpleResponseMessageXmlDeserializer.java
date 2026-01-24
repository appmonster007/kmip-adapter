package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.response.ResponseBatchItemStructure;
import org.purpleBean.kmip.api.response.ResponseHeaderStructure;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseMessage;

import java.io.IOException;

public class SimpleResponseMessageXmlDeserializer extends AbstractKmipStructureXmlDeserializer<SimpleResponseMessage, SimpleResponseMessage.SimpleResponseMessageBuilder> {

    public SimpleResponseMessageXmlDeserializer() {
        super(SimpleResponseMessage.kmipTag);
    }

    @Override
    protected SimpleResponseMessage.SimpleResponseMessageBuilder createBuilder() {
        return SimpleResponseMessage.builder();
    }

    @Override
    protected void setValue(SimpleResponseMessage.SimpleResponseMessageBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.RESPONSE_HEADER ->
                    builder.responseHeader(ctxt.readValue(p, ResponseHeaderStructure.class));
            case KmipTag.Standard.BATCH_ITEM -> {
                if (p.isExpectedStartArrayToken()) { // TODO: can be removed?
                    while (p.nextToken() != com.fasterxml.jackson.core.JsonToken.END_ARRAY) {
                        try {
                            builder.responseBatchItem(ctxt.readValue(p, ResponseBatchItemStructure.class));
                            builder.responseBatchItemError(null);
                        } catch (Exception e) {
                            builder.responseBatchItem(null);
                            builder.responseBatchItemError(e);
                        }
                    }
                } else {
                    try {
                        builder.responseBatchItem(ctxt.readValue(p, ResponseBatchItemStructure.class));
                        builder.responseBatchItemError(null);
                    } catch (Exception e) {
                        builder.responseBatchItem(null);
                        builder.responseBatchItemError(e);
                    }
                }
            }
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SimpleResponseMessage build(SimpleResponseMessage.SimpleResponseMessageBuilder builder) {
        return builder.build();
    }
}
