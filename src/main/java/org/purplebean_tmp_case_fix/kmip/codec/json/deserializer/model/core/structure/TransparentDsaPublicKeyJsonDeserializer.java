package org.purplebean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.TransparentDsaPublicKey;
import org.purplebean.kmip.model.core.type.G;
import org.purplebean.kmip.model.core.type.P;
import org.purplebean.kmip.model.core.type.Q;
import org.purplebean.kmip.model.core.type.Y;

public class TransparentDsaPublicKeyJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<TransparentDsaPublicKey,
        TransparentDsaPublicKey.TransparentDsaPublicKeyBuilder> {

  public TransparentDsaPublicKeyJsonDeserializer() {
    super(TransparentDsaPublicKey.kmipTag, TransparentDsaPublicKey.encodingType);
  }

  @Override
  protected TransparentDsaPublicKey.TransparentDsaPublicKeyBuilder createBuilder() {
    return TransparentDsaPublicKey.builder();
  }

  @Override
  protected void setValue(TransparentDsaPublicKey.TransparentDsaPublicKeyBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.P -> builder.p(ctxt.readValue(p, P.class));
      case KmipTag.Standard.Q -> builder.q(ctxt.readValue(p, Q.class));
      case KmipTag.Standard.G -> builder.g(ctxt.readValue(p, G.class));
      case KmipTag.Standard.Y -> builder.y(ctxt.readValue(p, Y.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected TransparentDsaPublicKey build(
      TransparentDsaPublicKey.TransparentDsaPublicKeyBuilder builder) {
    return builder.build();
  }
}