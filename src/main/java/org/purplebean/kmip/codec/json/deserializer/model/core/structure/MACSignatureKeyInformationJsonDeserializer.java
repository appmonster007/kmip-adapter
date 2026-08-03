package org.purplebean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.core.structure.MACSignatureKeyInformation;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;

/**
 * JSON deserializer for {@link MACSignatureKeyInformation}.
 */
public class MACSignatureKeyInformationJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<MACSignatureKeyInformation,
        MACSignatureKeyInformation.MACSignatureKeyInformationBuilder> {

  /**
   * Constructs a new {@link MACSignatureKeyInformationJsonDeserializer}.
   */
  public MACSignatureKeyInformationJsonDeserializer() {
    super(MACSignatureKeyInformation.kmipTag, MACSignatureKeyInformation.encodingType);
  }

  @Override
  protected MACSignatureKeyInformation.MACSignatureKeyInformationBuilder createBuilder() {
    return MACSignatureKeyInformation.builder();
  }

  @Override
  protected void setValue(MACSignatureKeyInformation.MACSignatureKeyInformationBuilder builder,
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
  protected MACSignatureKeyInformation build(
      MACSignatureKeyInformation.MACSignatureKeyInformationBuilder builder) {
    return builder.build();
  }
}
