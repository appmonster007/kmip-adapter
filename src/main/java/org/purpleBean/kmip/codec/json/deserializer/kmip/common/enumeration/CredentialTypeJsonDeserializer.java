package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.CredentialType;

public class CredentialTypeJsonDeserializer extends AbstractKmipJsonDeserializer<CredentialType, String> {

    public CredentialTypeJsonDeserializer() {
        super(CredentialType.kmipTag, CredentialType.encodingType, String.class, value -> new CredentialType(CredentialType.fromName(value)));
    }
}