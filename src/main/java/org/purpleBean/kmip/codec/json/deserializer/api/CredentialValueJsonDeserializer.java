package org.purpleBean.kmip.codec.json.deserializer.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.CredentialValue;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;

/**
 * JSON deserializer for {@link CredentialValue} objects.
 * <p>
 * This class extends {@link KmipDataTypeJsonDeserializer} to handle the specific logic required
 * for deserializing KMIP Credential Values from JSON. It uses the {@code credentialType} attribute
 * from the deserialization context to determine the concrete class to instantiate.
 */
public class CredentialValueJsonDeserializer extends KmipDataTypeJsonDeserializer<CredentialValue> {

  @Override
  public CredentialValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return super.deserialize(p, ctxt);
  }

  @Override
  public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag,
                                                            EncodingType encodingType,
                                                            DeserializationContext ctxt) {
    String ctxtCredentialType = (String) ctxt.getAttribute("credentialType");
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
