package org.purpleBean.kmip.codec.json.deserializer.model.v3x0.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;
import org.purpleBean.kmip.model.v3x0.structure.CredentialInformation;

public class CredentialInformationJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CredentialInformation,
        CredentialInformation.CredentialInformationBuilder> {

  public CredentialInformationJsonDeserializer() {
    super(CredentialInformation.kmipTag, CredentialInformation.encodingType);
  }

  @Override
  protected CredentialInformation.CredentialInformationBuilder createBuilder() {
    return CredentialInformation.builder();
  }

  @Override
  protected void setValue(CredentialInformation.CredentialInformationBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    if (nodeTag == CredentialType.kmipTag.getValue()) {
      builder.credentialType(ctxt.readValue(p, CredentialType.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CredentialInformation build(
      CredentialInformation.CredentialInformationBuilder builder) {
    return builder.build();
  }
}