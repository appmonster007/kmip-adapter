package org.purpleBean.kmip.codec.ttlv.deserializer.api;

import org.purpleBean.kmip.api.CredentialValue;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * TTLV deserializer for {@link CredentialValue} objects.
 * <p>
 * This class extends {@link KmipDataTypeTtlvDeserializer} to handle the specific logic required
 * for deserializing KMIP Credential Values from TTLV. It uses the {@code credentialType} attribute
 * from the deserialization context (mapper) to determine the concrete class to instantiate.
 */
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
        Class<? extends KmipDataType> clazz = CredentialValue.getClassFromRegistry(encodingType, credentialTypeValue);
        if (clazz == null && credentialTypeValue != null) {
            clazz = CredentialValue.getClassFromRegistry(encodingType, null);
        }
        return clazz;
    }
}
