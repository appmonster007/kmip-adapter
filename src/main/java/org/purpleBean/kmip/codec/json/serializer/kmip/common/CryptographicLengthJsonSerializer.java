package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.CryptographicLength;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CryptographicLengthJsonSerializer extends KmipDataTypeJsonSerializer<CryptographicLength> {

    @Override
    public void serialize(CryptographicLength cryptographicLength, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (cryptographicLength == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!cryptographicLength.isSupported()) {
            throw new UnsupportedEncodingException(String.format("%s is not supported for KMIP spec %s", cryptographicLength.getKmipTag().getDescription(), spec));
        }

        gen.writeStartObject();
        gen.writeObject(cryptographicLength.getKmipTag());
        gen.writeStringField("type", cryptographicLength.getEncodingType().getDescription());
        gen.writeObjectField("value", cryptographicLength.getValue());
        gen.writeEndObject();
    }
}
