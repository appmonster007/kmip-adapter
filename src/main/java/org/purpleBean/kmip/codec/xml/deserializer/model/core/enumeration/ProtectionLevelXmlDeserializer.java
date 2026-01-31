package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ProtectionLevel;

import java.io.IOException;

public class ProtectionLevelXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ProtectionLevel, ProtectionLevel.ProtectionLevelBuilder> {

    public ProtectionLevelXmlDeserializer() {
        super(ProtectionLevel.kmipTag, ProtectionLevel.encodingType);
    }

    @Override
    protected ProtectionLevel.ProtectionLevelBuilder createBuilder() {
        return ProtectionLevel.builder();
    }

    @Override
    protected void setValue(ProtectionLevel.ProtectionLevelBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ProtectionLevel.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected ProtectionLevel build(ProtectionLevel.ProtectionLevelBuilder builder) {
        return builder.build();
    }
}