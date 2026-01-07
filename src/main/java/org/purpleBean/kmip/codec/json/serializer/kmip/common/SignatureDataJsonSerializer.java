package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.SignatureData;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class SignatureDataJsonSerializer extends KmipDataTypeJsonSerializer<SignatureData> {

    @Override
    public void serialize(SignatureData signatureData, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (signatureData == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!signatureData.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", signatureData.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(signatureData.getKmipTag());
        gen.writeStringField("type", signatureData.getEncodingType().getDescription());
        gen.writeObjectField("value", signatureData.getValue());
        gen.writeEndObject();
    }
}
