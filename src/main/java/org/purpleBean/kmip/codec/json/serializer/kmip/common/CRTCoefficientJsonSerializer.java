package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.CRTCoefficient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CRTCoefficientJsonSerializer extends KmipDataTypeJsonSerializer<CRTCoefficient> {

    @Override
    public void serialize(CRTCoefficient cRTCoefficient, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (cRTCoefficient == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!cRTCoefficient.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", cRTCoefficient.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(cRTCoefficient.getKmipTag());
        gen.writeStringField("type", cRTCoefficient.getEncodingType().getDescription());
        gen.writeObjectField("value", cRTCoefficient.getValue());
        gen.writeEndObject();
    }
}