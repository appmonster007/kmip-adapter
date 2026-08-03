package org.purplebean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.structure.TransparentEcdsaPublicKey;
import org.purplebean.kmip.model.core.type.QString;

/**
 * XML deserializer for {@link TransparentEcdsaPublicKey}.
 */
public class TransparentEcdsaPublicKeyXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<TransparentEcdsaPublicKey,
        TransparentEcdsaPublicKey.TransparentEcdsaPublicKeyBuilder> {

  /**
   * Constructs a new {@link TransparentEcdsaPublicKeyXmlDeserializer}.
   */
  public TransparentEcdsaPublicKeyXmlDeserializer() {
    super(TransparentEcdsaPublicKey.kmipTag, TransparentEcdsaPublicKey.encodingType);
  }

  @Override
  protected TransparentEcdsaPublicKey.TransparentEcdsaPublicKeyBuilder createBuilder() {
    return TransparentEcdsaPublicKey.builder();
  }

  @Override
  protected void setValue(TransparentEcdsaPublicKey.TransparentEcdsaPublicKeyBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.RECOMMENDED_CURVE ->
          builder.recommendedCurve(ctxt.readValue(p, RecommendedCurve.class));
      case KmipTag.Standard.Q_STRING -> builder.qString(ctxt.readValue(p, QString.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected TransparentEcdsaPublicKey build(
      TransparentEcdsaPublicKey.TransparentEcdsaPublicKeyBuilder builder) {
    return builder.build();
  }
}