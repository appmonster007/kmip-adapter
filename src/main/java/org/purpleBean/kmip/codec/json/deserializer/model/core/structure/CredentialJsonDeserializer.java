package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.CredentialValue;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;
import org.purpleBean.kmip.model.core.structure.Credential;

import java.io.IOException;

public class CredentialJsonDeserializer extends AbstractKmipStructureJsonDeserializer<Credential, Credential.CredentialBuilder> {

    public CredentialJsonDeserializer() {
        super(Credential.kmipTag, Credential.encodingType);
    }

    @Override
    protected Credential.CredentialBuilder createBuilder() {
        return Credential.builder();
    }

    @Override
    protected void setValue(Credential.CredentialBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.CREDENTIAL_TYPE -> {
                CredentialType credentialType = ctxt.readValue(p, CredentialType.class);
                builder.credentialType(credentialType);
                ctxt.setAttribute("credentialType", credentialType.getDescription());
            }
            case KmipTag.Standard.CREDENTIAL_VALUE -> {
                builder.credentialValue(ctxt.readValue(p, CredentialValue.class));
            }
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Credential build(Credential.CredentialBuilder builder) {
        return builder.build();
    }
}