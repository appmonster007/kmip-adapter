package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.PrimeFieldSize;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class PrimeFieldSizeJsonSerializer extends KmipDataTypeJsonSerializer<PrimeFieldSize> {

    @Override
    public void serialize(PrimeFieldSize primeFieldSize, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (primeFieldSize == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!primeFieldSize.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", primeFieldSize.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(primeFieldSize.getKmipTag());
        gen.writeStringField("type", primeFieldSize.getEncodingType().getDescription());
        gen.writeObjectField("value", primeFieldSize.getValue());
        gen.writeEndObject();
    }
}