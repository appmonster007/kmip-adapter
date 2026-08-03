package org.purplebean.kmip.codec.ttlv.deserializer.api;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.CredentialValue;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.CredentialType;

/**
 * TTLV deserializer for {@link CredentialValue} objects.
 *
 * <p>This class extends {@link KmipDataTypeTtlvDeserializer} to handle the specific logic required
 * for deserializing KMIP Credential Values from TTLV. It uses the {@code credentialType} attribute
 * from the deserialization context (mapper) to determine the concrete class to instantiate.
 */
public class CredentialValueTtlvDeserializer extends KmipDataTypeTtlvDeserializer<CredentialValue> {

  @Override
  public CredentialValue deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
    return super.deserialize(ttlvBuffer, mapper);
  }

  @Override
  public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag,
                                                            EncodingType encodingType,
                                                            TtlvMapper mapper) {
    String ctxtCredentialType = (String) mapper.getAttribute("credentialType");
    CredentialType.Value credentialTypeValue;
    if (ctxtCredentialType == null) {
      credentialTypeValue = null;
    } else {
      credentialTypeValue = CredentialType.fromName(ctxtCredentialType);
    }
    Class<? extends KmipDataType> clazz =
        CredentialValue.getClassFromRegistry(encodingType, credentialTypeValue);
    if (clazz == null && credentialTypeValue != null) {
      clazz = CredentialValue.getClassFromRegistry(encodingType, null);
    }
    return clazz;
  }
}
