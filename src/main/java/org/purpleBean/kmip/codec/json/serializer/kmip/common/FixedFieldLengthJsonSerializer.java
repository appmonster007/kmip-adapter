package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.FixedFieldLength;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class FixedFieldLengthJsonSerializer extends KmipDataTypeJsonSerializer<FixedFieldLength> {

    @Override
    public void serialize(FixedFieldLength fixedFieldLength, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (fixedFieldLength == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!fixedFieldLength.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", fixedFieldLength.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(fixedFieldLength.getKmipTag());
        gen.writeStringField("type", fixedFieldLength.getEncodingType().getDescription());
        gen.writeObjectField("value", fixedFieldLength.getValue());
        gen.writeEndObject();
    }
}