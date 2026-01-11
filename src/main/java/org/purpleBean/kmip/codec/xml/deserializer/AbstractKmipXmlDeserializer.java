package org.purpleBean.kmip.codec.xml.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.function.Function;

public abstract class AbstractKmipXmlDeserializer<T extends KmipDataType, V> extends KmipDataTypeXmlDeserializer<T> {

    private final KmipTag kmipTag;
    private final EncodingType encodingType;
    private final Class<V> valueClass;
    private final Function<V, T> factory;

    protected AbstractKmipXmlDeserializer(KmipTag kmipTag, EncodingType encodingType, Class<V> valueClass, Function<V, T> factory) {
        this.kmipTag = kmipTag;
        this.encodingType = encodingType;
        this.valueClass = valueClass;
        this.factory = factory;
    }

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(handledType(), "Invalid Tag for " + handledType().getSimpleName());
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        V value = null;

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(handledType(), "Missing or invalid 'type' attribute for " + handledType().getSimpleName());
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    value = p.readValueAs(valueClass);
                }
            }
        }

        if (value == null) {
            ctxt.reportInputMismatch(handledType(), "Missing 'value' for " + handledType().getSimpleName());
            return null;
        }

        T result = factory.apply(value);

        KmipSpec spec = KmipContext.getSpec();
        if (!result.isSupported()) {
            throw new NoSuchElementException(
                    String.format("%s with value '%s' not supported for spec %s", handledType().getSimpleName(), value, spec));
        }

        return result;
    }
}