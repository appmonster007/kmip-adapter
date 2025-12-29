package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AsynchronousCorrelationValue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class AsynchronousCorrelationValueJsonSerializer extends KmipDataTypeJsonSerializer<AsynchronousCorrelationValue> {

    @Override
    public void serialize(AsynchronousCorrelationValue asynchronousCorrelationValue, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (asynchronousCorrelationValue == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!asynchronousCorrelationValue.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", asynchronousCorrelationValue.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(asynchronousCorrelationValue.getKmipTag());
        gen.writeStringField("type", asynchronousCorrelationValue.getEncodingType().getDescription());
        gen.writeObjectField("value", asynchronousCorrelationValue.getValue());
        gen.writeEndObject();
    }
}