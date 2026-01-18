package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.Authentication;
import org.purpleBean.kmip.model.core.structure.Credential;

import java.io.IOException;

public class AuthenticationXmlDeserializer extends AbstractKmipStructureXmlDeserializer<Authentication, Authentication.AuthenticationBuilder> {

    public AuthenticationXmlDeserializer() {
        super(Authentication.kmipTag);
    }

    @Override
    protected Authentication.AuthenticationBuilder createBuilder() {
        return Authentication.builder();
    }

    @Override
    protected void setValue(Authentication.AuthenticationBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.CREDENTIAL -> builder.credential(ctxt.readValue(p, Credential.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Authentication build(Authentication.AuthenticationBuilder builder) {
        return builder.build();
    }
}