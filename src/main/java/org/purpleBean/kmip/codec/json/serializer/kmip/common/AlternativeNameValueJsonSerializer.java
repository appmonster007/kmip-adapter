package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AlternativeNameValue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class AlternativeNameValueJsonSerializer extends KmipDataTypeJsonSerializer<AlternativeNameValue> {

    @Override
    public void serialize(AlternativeNameValue alternativeNameValue, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (alternativeNameValue == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!alternativeNameValue.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", alternativeNameValue.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(alternativeNameValue.getKmipTag());
        gen.writeStringField("type", alternativeNameValue.getEncodingType().getDescription());
        gen.writeObjectField("value", alternativeNameValue.getValue());
        gen.writeEndObject();
    }
}