package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.structure.TransparentEcdsaPrivateKey;
import org.purpleBean.kmip.model.core.type.D;

public class TransparentEcdsaPrivateKeyJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<TransparentEcdsaPrivateKey,
        TransparentEcdsaPrivateKey.TransparentEcdsaPrivateKeyBuilder> {

  public TransparentEcdsaPrivateKeyJsonDeserializer() {
    super(TransparentEcdsaPrivateKey.kmipTag, TransparentEcdsaPrivateKey.encodingType);
  }

  @Override
  protected TransparentEcdsaPrivateKey.TransparentEcdsaPrivateKeyBuilder createBuilder() {
    return TransparentEcdsaPrivateKey.builder();
  }

  @Override
  protected void setValue(TransparentEcdsaPrivateKey.TransparentEcdsaPrivateKeyBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.RECOMMENDED_CURVE ->
          builder.recommendedCurve(ctxt.readValue(p, RecommendedCurve.class));
      case KmipTag.Standard.D -> builder.d(ctxt.readValue(p, D.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected TransparentEcdsaPrivateKey build(
      TransparentEcdsaPrivateKey.TransparentEcdsaPrivateKeyBuilder builder) {
    return builder.build();
  }
}