package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.Authentication;
import org.purpleBean.kmip.model.core.structure.Credential;

import java.io.IOException;

public class AuthenticationJsonDeserializer extends AbstractKmipStructureJsonDeserializer<Authentication, Authentication.AuthenticationBuilder> {

    public AuthenticationJsonDeserializer() {
        super(Authentication.kmipTag, Authentication.encodingType);
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