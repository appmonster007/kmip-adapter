package org.purplebean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.EncodingOption;
import org.purplebean.kmip.model.core.enumeration.WrappingMethod;
import org.purplebean.kmip.model.core.structure.EncryptionKeyInformation;
import org.purplebean.kmip.model.core.structure.KeyWrappingSpecification;
import org.purplebean.kmip.model.core.structure.MACSignatureKeyInformation;
import org.purplebean.kmip.model.core.type.AttributeName;

public class KeyWrappingSpecificationJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<KeyWrappingSpecification,
        KeyWrappingSpecification.KeyWrappingSpecificationBuilder> {

  public KeyWrappingSpecificationJsonDeserializer() {
    super(KeyWrappingSpecification.kmipTag, KeyWrappingSpecification.encodingType);
  }

  @Override
  protected KeyWrappingSpecification.KeyWrappingSpecificationBuilder createBuilder() {
    return KeyWrappingSpecification.builder();
  }

  @Override
  protected void setValue(KeyWrappingSpecification.KeyWrappingSpecificationBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.WRAPPING_METHOD ->
          builder.wrappingMethod(ctxt.readValue(p, WrappingMethod.class));
      case KmipTag.Standard.ENCRYPTION_KEY_INFORMATION ->
          builder.encryptionKeyInformation(ctxt.readValue(p, EncryptionKeyInformation.class));
      case KmipTag.Standard.MAC_SIGNATURE_KEY_INFORMATION ->
          builder.macSignatureKeyInformation(ctxt.readValue(p, MACSignatureKeyInformation.class));
      case KmipTag.Standard.ATTRIBUTE_NAME ->
          builder.attributeName(ctxt.readValue(p, AttributeName.class));
      case KmipTag.Standard.ENCODING_OPTION ->
          builder.encodingOption(ctxt.readValue(p, EncodingOption.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected KeyWrappingSpecification build(
      KeyWrappingSpecification.KeyWrappingSpecificationBuilder builder) {
    return builder.build();
  }
}
