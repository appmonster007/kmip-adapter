package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.UsageLimitsTotal;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class UsageLimitsTotalJsonSerializer extends KmipDataTypeJsonSerializer<UsageLimitsTotal> {

    @Override
    public void serialize(UsageLimitsTotal usageLimitsTotal, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (usageLimitsTotal == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!usageLimitsTotal.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", usageLimitsTotal.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(usageLimitsTotal.getKmipTag());
        gen.writeStringField("type", usageLimitsTotal.getEncodingType().getDescription());
        gen.writeObjectField("value", usageLimitsTotal.getValue());
        gen.writeEndObject();
    }
}