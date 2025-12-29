package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.PrimeExponentQ;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class PrimeExponentQJsonSerializer extends KmipDataTypeJsonSerializer<PrimeExponentQ> {

    @Override
    public void serialize(PrimeExponentQ primeExponentQ, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (primeExponentQ == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!primeExponentQ.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", primeExponentQ.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(primeExponentQ.getKmipTag());
        gen.writeStringField("type", primeExponentQ.getEncodingType().getDescription());
        gen.writeObjectField("value", primeExponentQ.getValue());
        gen.writeEndObject();
    }
}