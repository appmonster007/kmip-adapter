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
import org.purpleBean.kmip.common.MediaIdentifier;

import java.io.IOException;

public class MediaIdentifierXmlDeserializer extends KmipDataTypeXmlDeserializer<MediaIdentifier> {
    private final KmipTag kmipTag = MediaIdentifier.kmipTag;
    private final EncodingType encodingType = MediaIdentifier.encodingType;

    @Override
    public MediaIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(MediaIdentifier.class, "Invalid Tag for MediaIdentifier");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        MediaIdentifier.MediaIdentifierBuilder builder = MediaIdentifier.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(MediaIdentifier.class, "Missing or invalid 'type' attribute for MediaIdentifier");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(MediaIdentifier.class,
                                "Missing or non-text 'value' for MediaIdentifier");
                        return null;
                    }
                    builder.value(p.getText());
                }
            }
        }

        MediaIdentifier mediaIdentifier = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!mediaIdentifier.isSupported()) {
            ctxt.reportInputMismatch(MediaIdentifier.class, "MediaIdentifier not supported for spec " + spec);
            return null;
        }

        return mediaIdentifier;
    }
}