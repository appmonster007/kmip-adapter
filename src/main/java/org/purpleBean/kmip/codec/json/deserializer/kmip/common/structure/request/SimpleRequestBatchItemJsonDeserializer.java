package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;

import java.io.IOException;
import java.util.NoSuchElementException;

public class SimpleRequestBatchItemJsonDeserializer extends KmipDataTypeJsonDeserializer<SimpleRequestBatchItem> {
    private final EncodingType encodingType = EncodingType.STRUCTURE;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.BATCH_ITEM);

    @Override
    public SimpleRequestBatchItem deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        KmipTag.Value tag = p.getCodec().treeToValue(node, KmipTag.class).getValue();
        if (!node.isObject() || tag != kmipTag.getValue()) {
            ctxt.reportInputMismatch(SimpleRequestBatchItem.class, "Expected object for SimpleRequestBatchItem");
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

        SimpleRequestBatchItem.SimpleRequestBatchItemBuilder builder = SimpleRequestBatchItem.builder();

        SimpleRequestBatchItem batchItem = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!batchItem.isSupportedFor(spec)) {
            throw new NoSuchElementException();
        }

        return batchItem;
    }
}
