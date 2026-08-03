package org.purplebean.kmip.codec.xml.deserializer.model.v3x0.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.CredentialType;
import org.purplebean.kmip.model.v3x0.structure.CredentialInformation;

public class CredentialInformationXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CredentialInformation,
        CredentialInformation.CredentialInformationBuilder> {

  public CredentialInformationXmlDeserializer() {
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