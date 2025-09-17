package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;
import org.purpleBean.kmip.common.structure.request.SimpleRequestMessage;

import java.io.IOException;

public class SimpleRequestMessageXmlDeserializer extends KmipDataTypeXmlDeserializer<SimpleRequestMessage> {

    @Override
    public SimpleRequestMessage deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(SimpleRequestMessage.class, "Expected XML object for SimpleRequestMessage");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        SimpleRequestMessage.SimpleRequestMessageBuilder builder = SimpleRequestMessage.builder();

        JsonNode headerNode = node.get(KmipTag.Standard.REQUEST_HEADER.getDescription());
        builder.requestHeader(p.getCodec().treeToValue(headerNode, SimpleRequestHeader.class));

        JsonNode batchItemNode = node.get(KmipTag.Standard.BATCH_ITEM.getDescription());
        if (!batchItemNode.isArray() && !batchItemNode.isEmpty()) {
            ctxt.reportInputMismatch(SimpleRequestBatchItem.class, "SimpleRequestBatchItem 'value' must be array");
        }

        if (batchItemNode.isArray()) {
            for (var valueNode : batchItemNode.valueStream().toList()) {
                try {
                    SimpleRequestBatchItem item = p.getCodec().treeToValue(valueNode, SimpleRequestBatchItem.class);
                    builder.requestBatchItem(item)
                            .requestBatchItemError(null);
                } catch (Exception e) {
                    builder.requestBatchItem(null)
                            .requestBatchItemError(e);
                }
            }
        } else {
            try {
                SimpleRequestBatchItem item = p.getCodec().treeToValue(batchItemNode, SimpleRequestBatchItem.class);
                builder.requestBatchItem(item)
                        .requestBatchItemError(null);
            } catch (Exception e) {
                builder.requestBatchItem(null)
                        .requestBatchItemError(e);
            }
        }

        SimpleRequestMessage message = builder.build();

        if (!message.isSupportedFor(spec)) {
            ctxt.reportInputMismatch(SimpleRequestMessage.class, "SimpleRequestMessage not supported for spec " + spec);
        }

        return message;
    }
}
