package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.ExtensionTag;

import java.io.IOException;

public class ExtensionTagXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ExtensionTag, ExtensionTag.ExtensionTagBuilder> {

    public ExtensionTagXmlDeserializer() {
        super(ExtensionTag.kmipTag, ExtensionTag.encodingType);
    }

    @Override
    protected ExtensionTag.ExtensionTagBuilder createBuilder() {
        return ExtensionTag.builder();
    }

    @Override
    protected void setValue(ExtensionTag.ExtensionTagBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected ExtensionTag build(ExtensionTag.ExtensionTagBuilder builder) {
        return builder.build();
    }
}