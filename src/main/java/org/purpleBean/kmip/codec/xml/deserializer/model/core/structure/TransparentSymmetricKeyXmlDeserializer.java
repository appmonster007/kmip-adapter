package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.TransparentSymmetricKey;
import org.purpleBean.kmip.model.core.type.Key;

public class TransparentSymmetricKeyXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<TransparentSymmetricKey,
        TransparentSymmetricKey.TransparentSymmetricKeyBuilder> {

  public TransparentSymmetricKeyXmlDeserializer() {
    super(TransparentSymmetricKey.kmipTag, TransparentSymmetricKey.encodingType);
  }

  @Override
  protected TransparentSymmetricKey.TransparentSymmetricKeyBuilder createBuilder() {
    return TransparentSymmetricKey.builder();
  }

  @Override
  protected void setValue(TransparentSymmetricKey.TransparentSymmetricKeyBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.KEY -> builder.key(ctxt.readValue(p, Key.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected TransparentSymmetricKey build(
      TransparentSymmetricKey.TransparentSymmetricKeyBuilder builder) {
    return builder.build();
  }
}