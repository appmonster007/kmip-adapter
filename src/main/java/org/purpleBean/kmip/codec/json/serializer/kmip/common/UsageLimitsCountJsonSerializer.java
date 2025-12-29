package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.UsageLimitsCount;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class UsageLimitsCountJsonSerializer extends KmipDataTypeJsonSerializer<UsageLimitsCount> {

    @Override
    public void serialize(UsageLimitsCount usageLimitsCount, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (usageLimitsCount == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!usageLimitsCount.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", usageLimitsCount.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(usageLimitsCount.getKmipTag());
        gen.writeStringField("type", usageLimitsCount.getEncodingType().getDescription());
        gen.writeObjectField("value", usageLimitsCount.getValue());
        gen.writeEndObject();
    }
}