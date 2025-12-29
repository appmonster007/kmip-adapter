package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AsynchronousIndicator;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class AsynchronousIndicatorJsonSerializer extends KmipDataTypeJsonSerializer<AsynchronousIndicator> {

    @Override
    public void serialize(AsynchronousIndicator asynchronousIndicator, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (asynchronousIndicator == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!asynchronousIndicator.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", asynchronousIndicator.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(asynchronousIndicator.getKmipTag());
        gen.writeStringField("type", asynchronousIndicator.getEncodingType().getDescription());
        gen.writeObjectField("value", asynchronousIndicator.getValue());
        gen.writeEndObject();
    }
}