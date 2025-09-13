package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;
import org.purpleBean.kmip.common.structure.request.SimpleRequestMessage;

import java.io.IOException;
import java.util.NoSuchElementException;

public class SimpleRequestMessageJsonDeserializer extends KmipDataTypeJsonDeserializer<SimpleRequestMessage> {
    private final EncodingType encodingType = EncodingType.STRUCTURE;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.REQUEST_MESSAGE);


    @Override
    public SimpleRequestMessage deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        KmipTag.Value tag = p.getCodec().treeToValue(node, KmipTag.class).getValue();
        if (!node.isObject() || tag != kmipTag.getValue()) {
            ctxt.reportInputMismatch(SimpleRequestMessage.class, "Expected object for SimpleRequestMessage");
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(State.class, String.format("Missing or non-text 'type' field for %s", kmipTag.getDescription()));
            return null;
        }

        JsonNode values = node.get("value");
        if (values == null || !values.isArray() || values.isEmpty()) {
            ctxt.reportInputMismatch(SimpleRequestMessage.class, "SimpleRequestMessage 'value' must be an array with at least 1 element");
            return null;
        }

        SimpleRequestMessage.SimpleRequestMessageBuilder builder = SimpleRequestMessage.builder();

        // first element is header
        JsonNode headerNode = values.get(0);
        builder.requestHeader(p.getCodec().treeToValue(headerNode, SimpleRequestHeader.class));

        // remaining elements are batch items
        for (int i = 1; i < values.size(); i++) {
            try {
                builder.requestBatchItem(p.getCodec().treeToValue(values.get(i), SimpleRequestBatchItem.class));
                builder.requestBatchItemError(null);
            } catch (Exception e) {
                builder.requestBatchItem(null);
                builder.requestBatchItemError(e);
            }
        }

        SimpleRequestMessage message = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!message.isSupportedFor(spec)) {
            throw new NoSuchElementException();
        }

        return message;
    }
}
