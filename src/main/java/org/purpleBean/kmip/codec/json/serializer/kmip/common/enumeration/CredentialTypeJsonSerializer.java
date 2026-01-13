package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.CredentialType;

public class CredentialTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CredentialType, String> {

    public CredentialTypeJsonSerializer() {
        super(CredentialType::getDescription);
    }
}