package org.purpleBean.kmip.codec.json.deserializer.model.core.structure.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.enumeration.ResultReason;
import org.purpleBean.kmip.model.core.enumeration.ResultStatus;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseBatchItem;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponsePayload;
import org.purpleBean.kmip.model.core.type.ResultMessage;

import java.io.IOException;

public class SimpleResponseBatchItemJsonDeserializer extends AbstractKmipStructureJsonDeserializer<SimpleResponseBatchItem, SimpleResponseBatchItem.SimpleResponseBatchItemBuilder> {

    public SimpleResponseBatchItemJsonDeserializer() {
        super(SimpleResponseBatchItem.kmipTag, SimpleResponseBatchItem.encodingType);
    }

    @Override
    protected SimpleResponseBatchItem.SimpleResponseBatchItemBuilder createBuilder() {
        return SimpleResponseBatchItem.builder();
    }

    @Override
    protected void setValue(SimpleResponseBatchItem.SimpleResponseBatchItemBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.OPERATION -> {
                Operation operation = ctxt.readValue(p, Operation.class);
                builder.operation(operation);
                ctxt.setAttribute("operation", operation.getDescription());
            }
            case KmipTag.Standard.RESULT_STATUS -> builder.resultStatus(ctxt.readValue(p, ResultStatus.class));
            case KmipTag.Standard.RESULT_REASON -> builder.resultReason(ctxt.readValue(p, ResultReason.class));
            case KmipTag.Standard.RESULT_MESSAGE -> builder.resultMessage(ctxt.readValue(p, ResultMessage.class));
            case KmipTag.Standard.RESPONSE_PAYLOAD ->
                    builder.responsePayloadStructure(ctxt.readValue(p, SimpleResponsePayload.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SimpleResponseBatchItem build(SimpleResponseBatchItem.SimpleResponseBatchItemBuilder builder) {
        return builder.build();
    }
}
