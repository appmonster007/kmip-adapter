package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.IVCounterNonce;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class IVCounterNonceJsonSerializer extends KmipDataTypeJsonSerializer<IVCounterNonce> {

    @Override
    public void serialize(IVCounterNonce iVCounterNonce, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (iVCounterNonce == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!iVCounterNonce.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", iVCounterNonce.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(iVCounterNonce.getKmipTag());
        gen.writeStringField("type", iVCounterNonce.getEncodingType().getDescription());
        gen.writeObjectField("value", iVCounterNonce.getValue());
        gen.writeEndObject();
    }
}