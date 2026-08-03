package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.TransparentDhPrivateKey;
import org.purpleBean.kmip.model.core.type.G;
import org.purpleBean.kmip.model.core.type.J;
import org.purpleBean.kmip.model.core.type.P;
import org.purpleBean.kmip.model.core.type.Q;
import org.purpleBean.kmip.model.core.type.X;

public class TransparentDhPrivateKeyJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<TransparentDhPrivateKey,
        TransparentDhPrivateKey.TransparentDhPrivateKeyBuilder> {

  public TransparentDhPrivateKeyJsonDeserializer() {
    super(TransparentDhPrivateKey.kmipTag, TransparentDhPrivateKey.encodingType);
  }

  @Override
  protected TransparentDhPrivateKey.TransparentDhPrivateKeyBuilder createBuilder() {
    return TransparentDhPrivateKey.builder();
  }

  @Override
  protected void setValue(TransparentDhPrivateKey.TransparentDhPrivateKeyBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.P -> builder.p(ctxt.readValue(p, P.class));
      case KmipTag.Standard.Q -> builder.q(ctxt.readValue(p, Q.class));
      case KmipTag.Standard.G -> builder.g(ctxt.readValue(p, G.class));
      case KmipTag.Standard.J -> builder.j(ctxt.readValue(p, J.class));
      case KmipTag.Standard.X -> builder.x(ctxt.readValue(p, X.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected TransparentDhPrivateKey build(
      TransparentDhPrivateKey.TransparentDhPrivateKeyBuilder builder) {
    return builder.build();
  }
}