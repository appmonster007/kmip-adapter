package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.ReplacedUniqueIdentifier;

import java.io.IOException;

public class ReplacedUniqueIdentifierXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ReplacedUniqueIdentifier, ReplacedUniqueIdentifier.ReplacedUniqueIdentifierBuilder> {

    public ReplacedUniqueIdentifierXmlDeserializer() {
        super(ReplacedUniqueIdentifier.kmipTag, ReplacedUniqueIdentifier.encodingType);
    }

    @Override
    protected ReplacedUniqueIdentifier.ReplacedUniqueIdentifierBuilder createBuilder() {
        return ReplacedUniqueIdentifier.builder();
    }

    @Override
    protected void setValue(ReplacedUniqueIdentifier.ReplacedUniqueIdentifierBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected ReplacedUniqueIdentifier build(ReplacedUniqueIdentifier.ReplacedUniqueIdentifierBuilder builder) {
        return builder.build();
    }
}