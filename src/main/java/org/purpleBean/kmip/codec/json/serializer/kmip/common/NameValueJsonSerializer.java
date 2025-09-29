package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.NameValue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class NameValueJsonSerializer extends KmipDataTypeJsonSerializer<NameValue> {

    @Override
    public void serialize(NameValue nameValue, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (nameValue == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!nameValue.isSupported()) {
            throw new UnsupportedEncodingException(String.format("%s is not supported for KMIP spec %s", nameValue.getKmipTag().getDescription(), spec));
        }

        gen.writeStartObject();
        gen.writeObject(nameValue.getKmipTag());
        gen.writeStringField("type", nameValue.getEncodingType().getDescription());
        gen.writeObjectField("value", nameValue.getValue());
        gen.writeEndObject();
    }
}
