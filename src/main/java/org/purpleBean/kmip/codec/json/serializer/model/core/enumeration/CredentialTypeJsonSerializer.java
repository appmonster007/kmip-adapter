package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;

public class CredentialTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CredentialType, String> {

    public CredentialTypeJsonSerializer() {
        super(CredentialType::getDescription);
    }
}