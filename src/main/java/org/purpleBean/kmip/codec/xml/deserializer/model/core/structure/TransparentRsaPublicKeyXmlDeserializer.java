package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.TransparentRsaPublicKey;
import org.purpleBean.kmip.model.core.type.Modulus;
import org.purpleBean.kmip.model.core.type.PublicExponent;

public class TransparentRsaPublicKeyXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<TransparentRsaPublicKey,
        TransparentRsaPublicKey.TransparentRsaPublicKeyBuilder> {

  public TransparentRsaPublicKeyXmlDeserializer() {
    super(TransparentRsaPublicKey.kmipTag, TransparentRsaPublicKey.encodingType);
  }

  @Override
  protected TransparentRsaPublicKey.TransparentRsaPublicKeyBuilder createBuilder() {
    return TransparentRsaPublicKey.builder();
  }

  @Override
  protected void setValue(TransparentRsaPublicKey.TransparentRsaPublicKeyBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.MODULUS -> builder.modulus(ctxt.readValue(p, Modulus.class));
      case KmipTag.Standard.PUBLIC_EXPONENT ->
          builder.publicExponent(ctxt.readValue(p, PublicExponent.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected TransparentRsaPublicKey build(
      TransparentRsaPublicKey.TransparentRsaPublicKeyBuilder builder) {
    return builder.build();
  }
}