package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.ObjectType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for ObjectType.
 */
public class ObjectTypeXmlDeserializer extends KmipDataTypeXmlDeserializer<ObjectType> {
    private final KmipTag kmipTag = ObjectType.kmipTag;
    private final EncodingType encodingType = ObjectType.encodingType;

    @Override
    public ObjectType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(ObjectType.class, "Invalid Tag for ObjectType");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        String description = null;

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(ObjectType.class, "Missing or invalid 'type' attribute for ObjectType");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(ObjectType.class,
                                "Missing or non-text 'value' for ObjectType");
                        return null;
                    }
                    description = p.getText();
                }
            }
        }

        if (description == null) {
            ctxt.reportInputMismatch(ObjectType.class, "Missing 'value' for ObjectType");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();

        ObjectType objecttype = new ObjectType(ObjectType.fromName(description));
        if (!objecttype.isSupported()) {
            throw new NoSuchElementException(
                    String.format("ObjectType '%s' not supported for spec %s", description, spec));
        }

        return objecttype;
    }
}