package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import org.purpleBean.kmip.AttributeValue;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

public class AttributeValueXmlDeserializer extends KmipDataTypeXmlDeserializer<AttributeValue> {

    private final KmipTag kmipTag = AttributeValue.kmipTag;

    @Override
    public AttributeValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        TokenBuffer buffer = new TokenBuffer(p, ctxt);
        buffer.copyCurrentStructure(p);

        JsonParser peeker = buffer.asParser();

        if (peeker.currentToken() == null) {
            peeker.nextToken();
        }

        if (peeker.currentToken() != JsonToken.START_OBJECT) {
            peeker.nextToken();
        }

        EncodingType encodingType = EncodingType.STRUCTURE;
        if (peeker.currentToken() == JsonToken.START_OBJECT) {
            if (peeker.nextToken() != JsonToken.END_OBJECT) {
                if (peeker.currentToken() == JsonToken.FIELD_NAME) {
                    String fieldName = peeker.currentName();

                    peeker.nextToken(); // Move to the value token
                    if ("type".equalsIgnoreCase(fieldName)) {
                        String type = peeker.getText();
                        Optional<EncodingType> optionalEncodingType = EncodingType.fromName(type);
                        if (optionalEncodingType.isEmpty()) {
                            ctxt.reportInputMismatch(AttributeValue.class, "Missing or invalid 'type' attribute for AttributeValue");
                            return null;
                        }
                        encodingType = optionalEncodingType.get();
                    }
                }
            }
        }
        Class<? extends KmipDataType> clazz = KmipDataType.getClassFromRegistry(kmipTag.getValue(), encodingType);
        if (clazz == null) {
            throw new NoSuchElementException(String.format("No class registered for tag %s and encoding type %s", kmipTag.getValue(), encodingType));
        }

        ctxt.setAttribute("tag", p.currentName());
        return (AttributeValue) ctxt.readValue(buffer.asParser(), clazz);
    }
}
