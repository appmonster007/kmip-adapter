package org.purpleBean.kmip.codec.ttlv.deserializer.kmip;

import org.purpleBean.kmip.api.CredentialValue;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CredentialValueTtlvDeserializer extends KmipDataTypeTtlvDeserializer<CredentialValue> {

    @Override
    public CredentialValue deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        return super.deserialize(ttlvBuffer, mapper);
    }

    @Override
    public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag, EncodingType encodingType, TtlvMapper mapper) {
        String ctxtCredentialType = (String) mapper.getAttribute("credentialType");
        CredentialType.Value credentialTypeValue;
        if (ctxtCredentialType == null) {
            credentialTypeValue = null;
        } else {
            credentialTypeValue = CredentialType.fromName(ctxtCredentialType);
        }
        return CredentialValue.getClassFromRegistry(encodingType, credentialTypeValue);
    }
}