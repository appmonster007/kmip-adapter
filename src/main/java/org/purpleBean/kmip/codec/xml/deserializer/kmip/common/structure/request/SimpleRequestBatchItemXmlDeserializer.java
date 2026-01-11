package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;

import java.io.IOException;

public class SimpleRequestBatchItemXmlDeserializer extends KmipDataTypeXmlDeserializer<SimpleRequestBatchItem> {

    @Override
    public SimpleRequestBatchItem deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() == null) {
            p.nextToken();
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        SimpleRequestBatchItem.SimpleRequestBatchItemBuilder builder = SimpleRequestBatchItem.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken(); // Move to the value token
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(SimpleRequestBatchItem.class, "Unexpected token: " + p.currentToken());
            }
        }

        SimpleRequestBatchItem item = builder.build();

        if (!item.isSupported()) {
            ctxt.reportInputMismatch(SimpleRequestBatchItem.class, "SimpleRequestBatchItem not supported for spec " + spec);
        }

        return item;
    }

    private void setValue(
            SimpleRequestBatchItem.SimpleRequestBatchItemBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        switch (nodeTag) {
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}
