package org.purplebean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.core.structure.EncryptionKeyInformation;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;

public class EncryptionKeyInformationJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<EncryptionKeyInformation,
        EncryptionKeyInformation.EncryptionKeyInformationBuilder> {

  public EncryptionKeyInformationJsonDeserializer() {
    super(EncryptionKeyInformation.kmipTag, EncryptionKeyInformation.encodingType);
  }

  @Override
  protected EncryptionKeyInformation.EncryptionKeyInformationBuilder createBuilder() {
    return EncryptionKeyInformation.builder();
  }

  @Override
  protected void setValue(EncryptionKeyInformation.EncryptionKeyInformationBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
          builder.cryptographicParameters(ctxt.readValue(p, CryptographicParameters.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected EncryptionKeyInformation build(
      EncryptionKeyInformation.EncryptionKeyInformationBuilder builder) {
    return builder.build();
  }
}
