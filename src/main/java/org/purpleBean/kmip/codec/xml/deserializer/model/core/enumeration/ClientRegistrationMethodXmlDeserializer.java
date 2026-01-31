package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ClientRegistrationMethod;

import java.io.IOException;

public class ClientRegistrationMethodXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ClientRegistrationMethod, ClientRegistrationMethod.ClientRegistrationMethodBuilder> {

    public ClientRegistrationMethodXmlDeserializer() {
        super(ClientRegistrationMethod.kmipTag, ClientRegistrationMethod.encodingType);
    }

    @Override
    protected ClientRegistrationMethod.ClientRegistrationMethodBuilder createBuilder() {
        return ClientRegistrationMethod.builder();
    }

    @Override
    protected void setValue(ClientRegistrationMethod.ClientRegistrationMethodBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ClientRegistrationMethod.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected ClientRegistrationMethod build(ClientRegistrationMethod.ClientRegistrationMethodBuilder builder) {
        return builder.build();
    }
}