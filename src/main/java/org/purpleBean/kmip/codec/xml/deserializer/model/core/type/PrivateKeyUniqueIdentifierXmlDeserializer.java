package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.PrivateKeyUniqueIdentifier;

import java.io.IOException;

public class PrivateKeyUniqueIdentifierXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<PrivateKeyUniqueIdentifier, PrivateKeyUniqueIdentifier.PrivateKeyUniqueIdentifierBuilder> {

    public PrivateKeyUniqueIdentifierXmlDeserializer() {
        super(PrivateKeyUniqueIdentifier.kmipTag, PrivateKeyUniqueIdentifier.encodingType);
    }

    @Override
    protected PrivateKeyUniqueIdentifier.PrivateKeyUniqueIdentifierBuilder createBuilder() {
        return PrivateKeyUniqueIdentifier.builder();
    }

    @Override
    protected void setValue(PrivateKeyUniqueIdentifier.PrivateKeyUniqueIdentifierBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected PrivateKeyUniqueIdentifier build(PrivateKeyUniqueIdentifier.PrivateKeyUniqueIdentifierBuilder builder) {
        return builder.build();
    }
}
