package org.purplebean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.model.core.structure.PrivateKey;

public class PrivateKeyXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<PrivateKey, PrivateKey.PrivateKeyBuilder> {

  public PrivateKeyXmlDeserializer() {
    super(PrivateKey.kmipTag, PrivateKey.encodingType);
  }

  @Override
  protected PrivateKey.PrivateKeyBuilder createBuilder() {
    return PrivateKey.builder();
  }

  @Override
  protected void setValue(PrivateKey.PrivateKeyBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.KEY_BLOCK -> builder.keyBlock(ctxt.readValue(p, KeyBlock.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected PrivateKey build(PrivateKey.PrivateKeyBuilder builder) {
    return builder.build();
  }
}