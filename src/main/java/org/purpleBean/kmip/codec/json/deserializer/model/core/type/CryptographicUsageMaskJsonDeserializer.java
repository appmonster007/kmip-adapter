package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.CryptographicUsageMask;

import java.io.IOException;

public class CryptographicUsageMaskJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CryptographicUsageMask, CryptographicUsageMask.CryptographicUsageMaskBuilder> {

    public CryptographicUsageMaskJsonDeserializer() {
        super(CryptographicUsageMask.kmipTag, CryptographicUsageMask.encodingType);
    }

    @Override
    protected CryptographicUsageMask.CryptographicUsageMaskBuilder createBuilder() {
        return CryptographicUsageMask.builder();
    }

    @Override
    protected void setValue(CryptographicUsageMask.CryptographicUsageMaskBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected CryptographicUsageMask build(CryptographicUsageMask.CryptographicUsageMaskBuilder builder) {
        return builder.build();
    }
}
