package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.UsageLimitsTotal;

import java.io.IOException;

public class UsageLimitsTotalJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<UsageLimitsTotal, UsageLimitsTotal.UsageLimitsTotalBuilder> {

    public UsageLimitsTotalJsonDeserializer() {
        super(UsageLimitsTotal.kmipTag, UsageLimitsTotal.encodingType);
    }

    @Override
    protected UsageLimitsTotal.UsageLimitsTotalBuilder createBuilder() {
        return UsageLimitsTotal.builder();
    }

    @Override
    protected void setValue(UsageLimitsTotal.UsageLimitsTotalBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Long.class));
    }

    @Override
    protected UsageLimitsTotal build(UsageLimitsTotal.UsageLimitsTotalBuilder builder) {
        return builder.build();
    }
}
