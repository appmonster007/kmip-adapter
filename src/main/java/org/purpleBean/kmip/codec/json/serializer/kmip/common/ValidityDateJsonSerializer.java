package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.ValidityDate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ValidityDateJsonSerializer extends KmipDataTypeJsonSerializer<ValidityDate> {

    @Override
    public void serialize(ValidityDate validityDate, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (validityDate == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!validityDate.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", validityDate.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(validityDate.getKmipTag());
        gen.writeStringField("type", validityDate.getEncodingType().getDescription());
        gen.writeObjectField("value", validityDate.getValue());
        gen.writeEndObject();
    }
}