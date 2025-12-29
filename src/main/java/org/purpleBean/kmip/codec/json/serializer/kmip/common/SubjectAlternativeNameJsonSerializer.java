package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.SubjectAlternativeName;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class SubjectAlternativeNameJsonSerializer extends KmipDataTypeJsonSerializer<SubjectAlternativeName> {

    @Override
    public void serialize(SubjectAlternativeName subjectAlternativeName, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (subjectAlternativeName == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!subjectAlternativeName.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", subjectAlternativeName.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(subjectAlternativeName.getKmipTag());
        gen.writeStringField("type", subjectAlternativeName.getEncodingType().getDescription());
        gen.writeObjectField("value", subjectAlternativeName.getValue());
        gen.writeEndObject();
    }
}