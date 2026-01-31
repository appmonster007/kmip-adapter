package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.NetworkIdentifier;

import java.io.IOException;

public class NetworkIdentifierXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<NetworkIdentifier, NetworkIdentifier.NetworkIdentifierBuilder> {

    public NetworkIdentifierXmlDeserializer() {
        super(NetworkIdentifier.kmipTag, NetworkIdentifier.encodingType);
    }

    @Override
    protected NetworkIdentifier.NetworkIdentifierBuilder createBuilder() {
        return NetworkIdentifier.builder();
    }

    @Override
    protected void setValue(NetworkIdentifier.NetworkIdentifierBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected NetworkIdentifier build(NetworkIdentifier.NetworkIdentifierBuilder builder) {
        return builder.build();
    }
}