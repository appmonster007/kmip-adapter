package org.purplebean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.EncodingOption;
import org.purplebean.kmip.model.core.enumeration.WrappingMethod;
import org.purplebean.kmip.model.core.structure.EncryptionKeyInformation;
import org.purplebean.kmip.model.core.structure.KeyWrappingData;
import org.purplebean.kmip.model.core.structure.MACSignatureKeyInformation;
import org.purplebean.kmip.model.core.type.IVCounterNonce;
import org.purplebean.kmip.model.core.type.MACSignature;

public class KeyWrappingDataJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<KeyWrappingData, KeyWrappingData.KeyWrappingDataBuilder> {

  public KeyWrappingDataJsonDeserializer() {
    super(KeyWrappingData.kmipTag, KeyWrappingData.encodingType);
  }

  @Override
  protected KeyWrappingData.KeyWrappingDataBuilder createBuilder() {
    return KeyWrappingData.builder();
  }

  @Override
  protected void setValue(KeyWrappingData.KeyWrappingDataBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.WRAPPING_METHOD ->
          builder.wrappingMethod(ctxt.readValue(p, WrappingMethod.class));
      case KmipTag.Standard.ENCRYPTION_KEY_INFORMATION ->
          builder.encryptionKeyInformation(ctxt.readValue(p, EncryptionKeyInformation.class));
      case KmipTag.Standard.MAC_SIGNATURE_KEY_INFORMATION ->
          builder.macSignatureKeyInformation(ctxt.readValue(p, MACSignatureKeyInformation.class));
      case KmipTag.Standard.MAC_SIGNATURE ->
          builder.macSignature(ctxt.readValue(p, MACSignature.class));
      case KmipTag.Standard.IV_COUNTER_NONCE ->
          builder.ivCounterNonce(ctxt.readValue(p, IVCounterNonce.class));
      case KmipTag.Standard.ENCODING_OPTION ->
          builder.encodingOption(ctxt.readValue(p, EncodingOption.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected KeyWrappingData build(KeyWrappingData.KeyWrappingDataBuilder builder) {
    return builder.build();
  }
}
