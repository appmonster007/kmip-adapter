package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.PublicKeyUniqueIdentifier;

import java.io.IOException;

public class PublicKeyUniqueIdentifierXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<PublicKeyUniqueIdentifier, PublicKeyUniqueIdentifier.PublicKeyUniqueIdentifierBuilder> {

    public PublicKeyUniqueIdentifierXmlDeserializer() {
        super(PublicKeyUniqueIdentifier.kmipTag, PublicKeyUniqueIdentifier.encodingType);
    }

    @Override
    protected PublicKeyUniqueIdentifier.PublicKeyUniqueIdentifierBuilder createBuilder() {
        return PublicKeyUniqueIdentifier.builder();
    }

    @Override
    protected void setValue(PublicKeyUniqueIdentifier.PublicKeyUniqueIdentifierBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected PublicKeyUniqueIdentifier build(PublicKeyUniqueIdentifier.PublicKeyUniqueIdentifierBuilder builder) {
        return builder.build();
    }
}
