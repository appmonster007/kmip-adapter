package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;

import java.io.IOException;
import java.util.Map;

public class SimpleRequestBatchItemXmlDeserializer extends JsonDeserializer<SimpleRequestBatchItem> {

    @Override
    public SimpleRequestBatchItem deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isEmpty() && !node.isObject()) {
            ctxt.reportInputMismatch(SimpleRequestBatchItem.class, "Expected XML object for SimpleRequestBatchItem");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        SimpleRequestBatchItem.SimpleRequestBatchItemBuilder builder = SimpleRequestBatchItem.builder();

        for (Map.Entry<String, JsonNode> entry : node.propertyStream().toList()) {
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        SimpleRequestBatchItem item = builder.build();

        if (!item.isSupportedFor(spec)) {
            ctxt.reportInputMismatch(SimpleRequestBatchItem.class, "SimpleRequestBatchItem not supported for spec " + spec);
        }

        return item;
    }

    private void setValue(SimpleRequestBatchItem.SimpleRequestBatchItemBuilder builder,
                          KmipTag.Value nodeTag,
                          JsonNode node,
                          JsonParser p,
                          DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            default -> throw new IllegalArgumentException();
        }
    }
}
