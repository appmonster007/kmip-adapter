package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.type.D;
import org.purpleBean.kmip.model.v2_1.structure.TransparentEcPrivateKey;

public class TransparentEcPrivateKeyJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<TransparentEcPrivateKey,
        TransparentEcPrivateKey.TransparentEcPrivateKeyBuilder> {

  public TransparentEcPrivateKeyJsonDeserializer() {
    super(TransparentEcPrivateKey.kmipTag, TransparentEcPrivateKey.encodingType);
  }

  @Override
  protected TransparentEcPrivateKey.TransparentEcPrivateKeyBuilder createBuilder() {
    return TransparentEcPrivateKey.builder();
  }

  @Override
  protected void setValue(TransparentEcPrivateKey.TransparentEcPrivateKeyBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    if (nodeTag == RecommendedCurve.kmipTag.getValue()) {
      builder.recommendedCurve(ctxt.readValue(p, RecommendedCurve.class));
    } else if (nodeTag == D.kmipTag.getValue()) {
      builder.d(ctxt.readValue(p, D.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected TransparentEcPrivateKey build(
      TransparentEcPrivateKey.TransparentEcPrivateKeyBuilder builder) {
    return builder.build();
  }
}