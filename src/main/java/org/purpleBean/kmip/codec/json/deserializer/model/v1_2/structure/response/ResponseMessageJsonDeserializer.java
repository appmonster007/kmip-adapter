package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.v1_2.structure.response.ResponseBatchItem;
import org.purpleBean.kmip.model.v1_2.structure.response.ResponseHeader;
import org.purpleBean.kmip.model.v1_2.structure.response.ResponseMessage;

import java.io.IOException;

public class ResponseMessageJsonDeserializer extends AbstractKmipStructureJsonDeserializer<ResponseMessage, ResponseMessage.ResponseMessageBuilder> {

    public ResponseMessageJsonDeserializer() {
        super(ResponseMessage.kmipTag, ResponseMessage.encodingType);
    }

    @Override
    protected ResponseMessage.ResponseMessageBuilder createBuilder() {
        return ResponseMessage.builder();
    }

    @Override
    protected void setValue(ResponseMessage.ResponseMessageBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.RESPONSE_HEADER -> builder.responseHeader(ctxt.readValue(p, ResponseHeader.class));
            case KmipTag.Standard.BATCH_ITEM -> {
                if (p.isExpectedStartArrayToken()) {
                    while (p.nextToken() != com.fasterxml.jackson.core.JsonToken.END_ARRAY) {
                        try {
                            builder.responseBatchItem(ctxt.readValue(p, ResponseBatchItem.class));
                            builder.responseBatchItemError(null);
                        } catch (Exception e) {
                            builder.responseBatchItem(null);
                            builder.responseBatchItemError(e);
                        }
                    }
                } else {
                    try {
                        builder.responseBatchItem(ctxt.readValue(p, ResponseBatchItem.class));
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
    protected ResponseMessage build(ResponseMessage.ResponseMessageBuilder builder) {
        return builder.build();
    }
}
