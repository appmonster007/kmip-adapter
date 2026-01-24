package org.purpleBean.kmip.codec.xml.deserializer.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.api.AttributeValue;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.NoSuchElementException;
import java.util.Optional;

public class KmipDataTypeXmlDeserializer<T extends KmipDataType> extends JsonDeserializer<KmipDataType> {

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        String currentName;
        if (p instanceof FromXmlParser xmlParser) {
            currentName = xmlParser.getStaxReader().getLocalName();
        } else {
            currentName = (String) ctxt.getAttribute("tag");
        }

        KmipTag.Value kmipTag = KmipTag.fromName(currentName);

        TokenBuffer buffer = new TokenBuffer(p, ctxt);
        buffer.copyCurrentStructure(p);

        JsonParser peeker = buffer.asParser();
        EncodingType encodingType = EncodingType.STRUCTURE;

        if (peeker.currentToken() == null) {
            peeker.nextToken();
        }

        if (peeker.currentToken() != JsonToken.START_OBJECT) {
            peeker.nextToken();
        }

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

        Class<? extends KmipDataType> clazz = getKmipDataTypeClass(kmipTag, encodingType, ctxt);
        if (clazz == null) {
            throw new NoSuchElementException(String.format("No class registered for tag %s and encoding type %s", kmipTag.getValue(), encodingType));
        }

        ctxt.setAttribute("tag", kmipTag.getDescription());
        return (T) ctxt.readValue(buffer.asParser(), clazz);
    }

    public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag, EncodingType encodingType, DeserializationContext ctxt) {
        return KmipDataType.getClassFromRegistry(kmipTag, encodingType);
    }

    @Override
    public Class<?> handledType() {
        // Try to infer the generic parameter (T) from the concrete subclass declaration
        // Handles both raw classes and parameterized types (template classes)
        Type superType = getClass().getGenericSuperclass();
        if (superType instanceof ParameterizedType pt) {
            Type tArg = pt.getActualTypeArguments()[0];
            if (tArg instanceof Class<?> c) {
                return c;
            }
            if (tArg instanceof ParameterizedType parameterized) {
                Type raw = parameterized.getRawType();
                if (raw instanceof Class<?> rc) {
                    return rc;
                }
            }
        }
        return super.handledType();
    }
}
