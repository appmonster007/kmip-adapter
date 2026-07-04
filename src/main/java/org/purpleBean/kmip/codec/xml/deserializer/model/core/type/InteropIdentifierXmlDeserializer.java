package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.InteropIdentifier;

import java.io.IOException;

public class InteropIdentifierXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<InteropIdentifier, InteropIdentifier.InteropIdentifierBuilder> {

    public InteropIdentifierXmlDeserializer() {
        super(InteropIdentifier.kmipTag, InteropIdentifier.encodingType);
    }

    @Override
    protected InteropIdentifier.InteropIdentifierBuilder createBuilder() {
        return InteropIdentifier.builder();
    }

    @Override
    protected void setValue(InteropIdentifier.InteropIdentifierBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected InteropIdentifier build(InteropIdentifier.InteropIdentifierBuilder builder) {
        return builder.build();
    }
}
