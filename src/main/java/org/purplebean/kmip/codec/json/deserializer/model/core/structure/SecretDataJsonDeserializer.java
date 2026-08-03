package org.purplebean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.SecretDataType;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.model.core.structure.SecretData;

/**
 * JSON deserializer for {@link SecretData}.
 */
public class SecretDataJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<SecretData, SecretData.SecretDataBuilder> {

  /**
   * Constructs a new {@link SecretDataJsonDeserializer}.
   */
  public SecretDataJsonDeserializer() {
    super(SecretData.kmipTag, SecretData.encodingType);
  }

  @Override
  protected SecretData.SecretDataBuilder createBuilder() {
    return SecretData.builder();
  }

  @Override
  protected void setValue(SecretData.SecretDataBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.SECRET_DATA_TYPE ->
          builder.secretDataType(ctxt.readValue(p, SecretDataType.class));
      case KmipTag.Standard.KEY_BLOCK -> builder.keyBlock(ctxt.readValue(p, KeyBlock.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SecretData build(SecretData.SecretDataBuilder builder) {
    return builder.build();
  }
}