package org.purpleBean.kmip.codec.xml.deserializer.kmip;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.CredentialValue;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;

import java.io.IOException;

public class CredentialValueXmlDeserializer extends KmipDataTypeXmlDeserializer<CredentialValue> {

    @Override
    public CredentialValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return super.deserialize(p, ctxt);
    }

    @Override
    public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag, EncodingType encodingType, DeserializationContext ctxt) {
        CredentialType.Value credentialTypeValue = CredentialType.fromName((String) ctxt.getAttribute("credentialType"));
        return CredentialValue.getClassFromRegistry(encodingType, credentialTypeValue);
    }
}