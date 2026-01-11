package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.UniqueBatchItemID;

import java.io.IOException;
import java.nio.ByteBuffer;

public class UniqueBatchItemIDXmlDeserializer extends KmipDataTypeXmlDeserializer<UniqueBatchItemID> {
    private final KmipTag kmipTag = UniqueBatchItemID.kmipTag;
    private final EncodingType encodingType = UniqueBatchItemID.encodingType;

    @Override
    public UniqueBatchItemID deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(UniqueBatchItemID.class, "Invalid Tag for UniqueBatchItemID");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        UniqueBatchItemID.UniqueBatchItemIDBuilder builder = UniqueBatchItemID.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(UniqueBatchItemID.class, "Missing or invalid 'type' attribute for UniqueBatchItemID");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(UniqueBatchItemID.class,
                                "Missing or non-text 'value' for UniqueBatchItemID");
                        return null;
                    }
                    builder.value(ctxt.readValue(p, ByteBuffer.class));
                }
            }
        }

        UniqueBatchItemID uniqueBatchItemID = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!uniqueBatchItemID.isSupported()) {
            ctxt.reportInputMismatch(UniqueBatchItemID.class, "UniqueBatchItemID not supported for spec " + spec);
            return null;
        }

        return uniqueBatchItemID;
    }
}