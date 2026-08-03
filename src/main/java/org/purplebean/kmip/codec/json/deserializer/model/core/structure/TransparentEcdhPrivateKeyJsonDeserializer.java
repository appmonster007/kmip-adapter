package org.purplebean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.structure.TransparentEcdhPrivateKey;
import org.purplebean.kmip.model.core.type.D;

/**
 * JSON deserializer for {@link TransparentEcdhPrivateKey}.
 */
public class TransparentEcdhPrivateKeyJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<TransparentEcdhPrivateKey,
        TransparentEcdhPrivateKey.TransparentEcdhPrivateKeyBuilder> {

  /**
   * Constructs a new {@link TransparentEcdhPrivateKeyJsonDeserializer}.
   */
  public TransparentEcdhPrivateKeyJsonDeserializer() {
    super(TransparentEcdhPrivateKey.kmipTag, TransparentEcdhPrivateKey.encodingType);
  }

  @Override
  protected TransparentEcdhPrivateKey.TransparentEcdhPrivateKeyBuilder createBuilder() {
    return TransparentEcdhPrivateKey.builder();
  }

  @Override
  protected void setValue(TransparentEcdhPrivateKey.TransparentEcdhPrivateKeyBuilder builder,
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
  protected TransparentEcdhPrivateKey build(
      TransparentEcdhPrivateKey.TransparentEcdhPrivateKeyBuilder builder) {
    return builder.build();
  }
}