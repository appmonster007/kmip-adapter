package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestPayload;
import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.MessageExtension;
import org.purpleBean.kmip.model.core.type.UniqueBatchItemID;
import org.purpleBean.kmip.model.v1_2.structure.request.RequestBatchItem;

import java.io.IOException;

public class RequestBatchItemJsonDeserializer extends AbstractKmipStructureJsonDeserializer<RequestBatchItem, RequestBatchItem.RequestBatchItemBuilder> {

    public RequestBatchItemJsonDeserializer() {
        super(RequestBatchItem.kmipTag, RequestBatchItem.encodingType);
    }

    @Override
    protected RequestBatchItem.RequestBatchItemBuilder createBuilder() {
        return RequestBatchItem.builder();
    }

    @Override
    protected void setValue(RequestBatchItem.RequestBatchItemBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.OPERATION -> {
                Operation operation = ctxt.readValue(p, Operation.class);
                builder.operation(operation);
                ctxt.setAttribute("operation", operation.getDescription());
            }
            case KmipTag.Standard.UNIQUE_BATCH_ITEM_ID ->
                    builder.uniqueBatchItemID(ctxt.readValue(p, UniqueBatchItemID.class));
            case KmipTag.Standard.REQUEST_PAYLOAD -> builder.requestPayload(ctxt.readValue(p, RequestPayload.class));
            case KmipTag.Standard.MESSAGE_EXTENSION ->
                    builder.messageExtension(ctxt.readValue(p, MessageExtension.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected RequestBatchItem build(RequestBatchItem.RequestBatchItemBuilder builder) {
        return builder.build();
    }
}