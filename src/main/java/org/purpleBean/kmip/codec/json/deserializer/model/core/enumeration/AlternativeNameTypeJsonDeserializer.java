package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.AlternativeNameType;

import java.io.IOException;

public class AlternativeNameTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AlternativeNameType, AlternativeNameType.AlternativeNameTypeBuilder> {

    public AlternativeNameTypeJsonDeserializer() {
        super(AlternativeNameType.kmipTag, AlternativeNameType.encodingType);
    }

    @Override
    protected AlternativeNameType.AlternativeNameTypeBuilder createBuilder() {
        return AlternativeNameType.builder();
    }

    @Override
    protected void setValue(AlternativeNameType.AlternativeNameTypeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(AlternativeNameType.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected AlternativeNameType build(AlternativeNameType.AlternativeNameTypeBuilder builder) {
        return builder.build();
    }
}
