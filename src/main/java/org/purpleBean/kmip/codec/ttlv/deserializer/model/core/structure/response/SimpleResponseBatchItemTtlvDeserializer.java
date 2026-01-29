package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure.response;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.response.ResponsePayloadStructure;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.enumeration.ResultReason;
import org.purpleBean.kmip.model.core.enumeration.ResultStatus;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseBatchItem;
import org.purpleBean.kmip.model.core.type.ResultMessage;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SimpleResponseBatchItemTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SimpleResponseBatchItem, SimpleResponseBatchItem.SimpleResponseBatchItemBuilder> {

    public SimpleResponseBatchItemTtlvDeserializer() {
        super(SimpleResponseBatchItem.kmipTag, SimpleResponseBatchItem.encodingType);
    }

    @Override
    protected SimpleResponseBatchItem.SimpleResponseBatchItemBuilder createBuilder() {
        return SimpleResponseBatchItem.builder();
    }

    @Override
    protected void setValue(SimpleResponseBatchItem.SimpleResponseBatchItemBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        switch (nodeTag) {
            case KmipTag.Standard.OPERATION -> {
                Operation operation = mapper.readValue(p, Operation.class);
                builder.operation(operation);
                mapper.setAttribute("operation", operation.getDescription());
            }
            case KmipTag.Standard.RESULT_STATUS -> builder.resultStatus(mapper.readValue(p, ResultStatus.class));
            case KmipTag.Standard.RESULT_REASON -> builder.resultReason(mapper.readValue(p, ResultReason.class));
            case KmipTag.Standard.RESULT_MESSAGE -> builder.resultMessage(mapper.readValue(p, ResultMessage.class));
            case KmipTag.Standard.RESPONSE_PAYLOAD ->
                    builder.responsePayloadStructure(mapper.readValue(p, ResponsePayloadStructure.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SimpleResponseBatchItem build(SimpleResponseBatchItem.SimpleResponseBatchItemBuilder builder) {
        return builder.build();
    }
}
