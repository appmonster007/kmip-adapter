package org.purpleBean.kmip.codec.xml.deserializer.kmip;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.*;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

public class KmipAttributeXmlDeserializer<T extends KmipAttribute> extends KmipDataTypeXmlDeserializer<KmipAttribute> {

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        String currentName;
        if (p instanceof FromXmlParser xmlParser) {
            currentName = xmlParser.getStaxReader().getLocalName();
        } else {
            currentName = (String) ctxt.getAttribute("tag");
        }

        KmipSpec spec = KmipContext.getSpec();
        KmipTag.Value kmipTag = KmipTag.fromName(spec, currentName);

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
                            ctxt.reportInputMismatch(KmipAttribute.class, "Missing or invalid 'type' attribute for KmipAttribute");
                            return null;
                        }
                        encodingType = optionalEncodingType.get();
                    }
                }
            }
        }
        Class<? extends KmipAttribute> clazz = KmipAttribute.getClassFromRegistry(kmipTag, encodingType);
        if (clazz == null) {
            throw new NoSuchElementException(String.format("No class registered for tag %s and encoding type %s", kmipTag.getValue(), encodingType));
        }

        ctxt.setAttribute("tag", p.currentName());
        return (T) ctxt.readValue(buffer.asParser(), clazz);
    }
}