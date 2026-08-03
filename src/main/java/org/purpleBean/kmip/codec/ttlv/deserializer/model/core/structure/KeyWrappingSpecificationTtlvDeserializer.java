package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.EncodingOption;
import org.purpleBean.kmip.model.core.enumeration.WrappingMethod;
import org.purpleBean.kmip.model.core.structure.EncryptionKeyInformation;
import org.purpleBean.kmip.model.core.structure.KeyWrappingSpecification;
import org.purpleBean.kmip.model.core.structure.MACSignatureKeyInformation;
import org.purpleBean.kmip.model.core.type.AttributeName;

public class KeyWrappingSpecificationTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<KeyWrappingSpecification,
        KeyWrappingSpecification.KeyWrappingSpecificationBuilder> {

  public KeyWrappingSpecificationTtlvDeserializer() {
    super(KeyWrappingSpecification.kmipTag, KeyWrappingSpecification.encodingType);
  }

  @Override
  protected KeyWrappingSpecification.KeyWrappingSpecificationBuilder createBuilder() {
    return KeyWrappingSpecification.builder();
  }

  @Override
  protected void setValue(KeyWrappingSpecification.KeyWrappingSpecificationBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.WRAPPING_METHOD ->
          builder.wrappingMethod(mapper.readValue(p, WrappingMethod.class));
      case KmipTag.Standard.ENCRYPTION_KEY_INFORMATION ->
          builder.encryptionKeyInformation(mapper.readValue(p, EncryptionKeyInformation.class));
      case KmipTag.Standard.MAC_SIGNATURE_KEY_INFORMATION ->
          builder.macSignatureKeyInformation(mapper.readValue(p, MACSignatureKeyInformation.class));
      case KmipTag.Standard.ATTRIBUTE_NAME ->
          builder.attributeName(mapper.readValue(p, AttributeName.class));
      case KmipTag.Standard.ENCODING_OPTION ->
          builder.encodingOption(mapper.readValue(p, EncodingOption.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected KeyWrappingSpecification build(
      KeyWrappingSpecification.KeyWrappingSpecificationBuilder builder) {
    return builder.build();
  }
}