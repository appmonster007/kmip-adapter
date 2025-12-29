package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.DigestValue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class DigestValueJsonSerializer extends KmipDataTypeJsonSerializer<DigestValue> {

    @Override
    public void serialize(DigestValue digestValue, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (digestValue == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!digestValue.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", digestValue.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(digestValue.getKmipTag());
        gen.writeStringField("type", digestValue.getEncodingType().getDescription());
        gen.writeObjectField("value", digestValue.getValue());
        gen.writeEndObject();
    }
}