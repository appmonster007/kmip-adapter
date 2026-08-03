package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.EncodingOption;
import org.purplebean.kmip.model.core.enumeration.WrappingMethod;
import org.purplebean.kmip.model.core.structure.EncryptionKeyInformation;
import org.purplebean.kmip.model.core.structure.KeyWrappingData;
import org.purplebean.kmip.model.core.structure.MACSignatureKeyInformation;
import org.purplebean.kmip.model.core.type.IVCounterNonce;
import org.purplebean.kmip.model.core.type.MACSignature;

public class KeyWrappingDataTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<KeyWrappingData, KeyWrappingData.KeyWrappingDataBuilder> {

  public KeyWrappingDataTtlvDeserializer() {
    super(KeyWrappingData.kmipTag, KeyWrappingData.encodingType);
  }

  @Override
  protected KeyWrappingData.KeyWrappingDataBuilder createBuilder() {
    return KeyWrappingData.builder();
  }

  @Override
  protected void setValue(KeyWrappingData.KeyWrappingDataBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.WRAPPING_METHOD ->
          builder.wrappingMethod(mapper.readValue(p, WrappingMethod.class));
      case KmipTag.Standard.ENCRYPTION_KEY_INFORMATION ->
          builder.encryptionKeyInformation(mapper.readValue(p, EncryptionKeyInformation.class));
      case KmipTag.Standard.MAC_SIGNATURE_KEY_INFORMATION ->
          builder.macSignatureKeyInformation(mapper.readValue(p, MACSignatureKeyInformation.class));
      case KmipTag.Standard.MAC_SIGNATURE ->
          builder.macSignature(mapper.readValue(p, MACSignature.class));
      case KmipTag.Standard.IV_COUNTER_NONCE ->
          builder.ivCounterNonce(mapper.readValue(p, IVCounterNonce.class));
      case KmipTag.Standard.ENCODING_OPTION ->
          builder.encodingOption(mapper.readValue(p, EncodingOption.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected KeyWrappingData build(KeyWrappingData.KeyWrappingDataBuilder builder) {
    return builder.build();
  }
}