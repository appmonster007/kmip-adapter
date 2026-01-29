package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.MessageExtension;
import org.purpleBean.kmip.model.core.type.UniqueBatchItemID;
import org.purpleBean.kmip.model.v1_2.structure.request.RequestBatchItem;

import java.io.IOException;
import java.nio.ByteBuffer;

public class RequestBatchItemTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RequestBatchItem, RequestBatchItem.RequestBatchItemBuilder> {

    public RequestBatchItemTtlvDeserializer() {
        super(RequestBatchItem.kmipTag, RequestBatchItem.encodingType);
    }

    @Override
    protected RequestBatchItem.RequestBatchItemBuilder createBuilder() {
        return RequestBatchItem.builder();
    }

    @Override
    protected void setValue(RequestBatchItem.RequestBatchItemBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        switch (nodeTag) {
            case KmipTag.Standard.OPERATION -> {
                Operation operation = mapper.readValue(p, Operation.class);
                builder.operation(operation);
                mapper.setAttribute("operation", operation.getDescription());
            }
            case KmipTag.Standard.UNIQUE_BATCH_ITEM_ID ->
                    builder.uniqueBatchItemID(mapper.readValue(p, UniqueBatchItemID.class));
            case KmipTag.Standard.REQUEST_PAYLOAD ->
                    builder.requestPayloadStructure(mapper.readValue(p, RequestPayloadStructure.class));
            case KmipTag.Standard.MESSAGE_EXTENSION ->
                    builder.messageExtension(mapper.readValue(p, MessageExtension.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected RequestBatchItem build(RequestBatchItem.RequestBatchItemBuilder builder) {
        return builder.build();
    }
}