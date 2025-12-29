package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.Modulus;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ModulusJsonSerializer extends KmipDataTypeJsonSerializer<Modulus> {

    @Override
    public void serialize(Modulus modulus, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (modulus == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!modulus.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", modulus.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(modulus.getKmipTag());
        gen.writeStringField("type", modulus.getEncodingType().getDescription());
        gen.writeObjectField("value", modulus.getValue());
        gen.writeEndObject();
    }
}