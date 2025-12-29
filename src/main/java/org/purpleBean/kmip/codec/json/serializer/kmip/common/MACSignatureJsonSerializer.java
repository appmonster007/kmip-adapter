package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.MACSignature;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MACSignatureJsonSerializer extends KmipDataTypeJsonSerializer<MACSignature> {

    @Override
    public void serialize(MACSignature mACSignature, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (mACSignature == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!mACSignature.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", mACSignature.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(mACSignature.getKmipTag());
        gen.writeStringField("type", mACSignature.getEncodingType().getDescription());
        gen.writeObjectField("value", mACSignature.getValue());
        gen.writeEndObject();
    }
}