package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.structure.MACSignatureKeyInformation;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

public class MACSignatureKeyInformationJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<MACSignatureKeyInformation,
        MACSignatureKeyInformation.MACSignatureKeyInformationBuilder> {

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
