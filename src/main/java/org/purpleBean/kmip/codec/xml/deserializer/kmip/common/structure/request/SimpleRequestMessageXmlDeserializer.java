package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.structure.request.SimpleRequestBatchItem;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;
import org.purpleBean.kmip.common.structure.request.SimpleRequestMessage;

import java.io.IOException;

public class SimpleRequestMessageXmlDeserializer extends KmipDataTypeXmlDeserializer<SimpleRequestMessage> {
    private final KmipTag kmipTag = SimpleRequestMessage.kmipTag;

    @Override
    public SimpleRequestMessage deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() == null) {
            p.nextToken();
        }

        String currentName;
        if (p instanceof FromXmlParser xmlParser) {
            currentName = xmlParser.getStaxReader().getLocalName();
        } else {
            currentName = (String) ctxt.getAttribute("tag");
        }

        if (!kmipTag.getDescription().equalsIgnoreCase(currentName)) {
            ctxt.reportInputMismatch(SimpleRequestMessage.class, "Invalid Tag for SimpleRequestMessage");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        SimpleRequestMessage.SimpleRequestMessageBuilder builder = SimpleRequestMessage.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(SimpleRequestMessage.class, "Unexpected token: " + p.currentToken());
            }
        }

        SimpleRequestMessage simpleRequestMessage = builder.build();

        if (!simpleRequestMessage.isSupported()) {
            ctxt.reportInputMismatch(SimpleRequestMessage.class, "SimpleRequestMessage not supported for spec " + spec);
            return null;
        }

        return simpleRequestMessage;
    }

    private void setValue(SimpleRequestMessage.SimpleRequestMessageBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        switch (nodeTag) {
            case KmipTag.Standard.REQUEST_HEADER -> builder.requestHeader(ctxt.readValue(p, SimpleRequestHeader.class));
            case KmipTag.Standard.BATCH_ITEM -> {
                if (p.isExpectedStartArrayToken()) {
                    while (p.nextToken() != JsonToken.END_ARRAY) {
                        try {
                            builder.requestBatchItem(ctxt.readValue(p, SimpleRequestBatchItem.class));
                        } catch (Exception e) {
                            builder.requestBatchItemError(e);
                        }
                    }
                } else {
                    try {
                        builder.requestBatchItem(ctxt.readValue(p, SimpleRequestBatchItem.class));
                    } catch (Exception e) {
                        builder.requestBatchItemError(e);
                    }
                }
            }
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}