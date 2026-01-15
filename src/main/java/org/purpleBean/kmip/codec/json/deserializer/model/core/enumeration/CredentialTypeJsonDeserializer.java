package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;

public class CredentialTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CredentialType, String> {

    public CredentialTypeJsonDeserializer() {
        super(CredentialType.kmipTag, CredentialType.encodingType, String.class, value -> new CredentialType(CredentialType.fromName(value)));
    }
}