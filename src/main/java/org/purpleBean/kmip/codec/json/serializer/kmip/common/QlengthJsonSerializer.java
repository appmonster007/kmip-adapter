package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.Qlength;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class QlengthJsonSerializer extends KmipDataTypeJsonSerializer<Qlength> {

    @Override
    public void serialize(Qlength qlength, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (qlength == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!qlength.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", qlength.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(qlength.getKmipTag());
        gen.writeStringField("type", qlength.getEncodingType().getDescription());
        gen.writeObjectField("value", qlength.getValue());
        gen.writeEndObject();
    }
}