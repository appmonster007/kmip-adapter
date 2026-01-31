package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v1_2.structure.response.ResponseBatchItem;
import org.purpleBean.kmip.model.v1_2.structure.response.ResponseHeader;
import org.purpleBean.kmip.model.v1_2.structure.response.ResponseMessage;

import java.io.IOException;

public class ResponseMessageXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ResponseMessage, ResponseMessage.ResponseMessageBuilder> {

    public ResponseMessageXmlDeserializer() {
        super(ResponseMessage.kmipTag, ResponseMessage.encodingType);
    }

    @Override
    protected ResponseMessage.ResponseMessageBuilder createBuilder() {
        return ResponseMessage.builder();
    }

    @Override
    protected void setValue(ResponseMessage.ResponseMessageBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.RESPONSE_HEADER -> builder.responseHeader(ctxt.readValue(p, ResponseHeader.class));
            case KmipTag.Standard.BATCH_ITEM -> {
                if (p.isExpectedStartArrayToken()) { // TODO: can be removed?
                    while (p.nextToken() != JsonToken.END_ARRAY) {
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
