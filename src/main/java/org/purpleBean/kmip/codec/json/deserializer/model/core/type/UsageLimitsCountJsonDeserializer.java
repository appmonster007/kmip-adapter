package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.UsageLimitsCount;

import java.io.IOException;

public class UsageLimitsCountJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<UsageLimitsCount, UsageLimitsCount.UsageLimitsCountBuilder> {

    public UsageLimitsCountJsonDeserializer() {
        super(UsageLimitsCount.kmipTag, UsageLimitsCount.encodingType);
    }

    @Override
    protected UsageLimitsCount.UsageLimitsCountBuilder createBuilder() {
        return UsageLimitsCount.builder();
    }

    @Override
    protected void setValue(UsageLimitsCount.UsageLimitsCountBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Long.class));
    }

    @Override
    protected UsageLimitsCount build(UsageLimitsCount.UsageLimitsCountBuilder builder) {
        return builder.build();
    }
}
