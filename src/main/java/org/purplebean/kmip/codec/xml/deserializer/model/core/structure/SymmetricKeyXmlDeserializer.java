package org.purplebean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.model.core.structure.SymmetricKey;

/**
 * XML deserializer for {@link SymmetricKey}.
 */
public class SymmetricKeyXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<SymmetricKey, SymmetricKey.SymmetricKeyBuilder> {

  /**
   * Constructs a new {@link SymmetricKeyXmlDeserializer}.
   */
  public SymmetricKeyXmlDeserializer() {
    super(SymmetricKey.kmipTag, SymmetricKey.encodingType);
  }

  @Override
  protected SymmetricKey.SymmetricKeyBuilder createBuilder() {
    return SymmetricKey.builder();
  }

  @Override
  protected void setValue(SymmetricKey.SymmetricKeyBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.KEY_BLOCK -> builder.keyBlock(ctxt.readValue(p, KeyBlock.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SymmetricKey build(SymmetricKey.SymmetricKeyBuilder builder) {
    return builder.build();
  }
}