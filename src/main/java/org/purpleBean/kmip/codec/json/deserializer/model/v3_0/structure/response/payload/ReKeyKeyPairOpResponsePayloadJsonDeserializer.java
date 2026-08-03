package org.purpleBean.kmip.codec.json.deserializer.model.v3_0.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purpleBean.kmip.model.v3_0.structure.response.payload.ReKeyKeyPairOpResponsePayload;
import org.purpleBean.kmip.model.v3_0.type.PrivateKeyUniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.type.PublicKeyUniqueIdentifier;

public class ReKeyKeyPairOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ReKeyKeyPairOpResponsePayload,
        ReKeyKeyPairOpResponsePayload.ReKeyKeyPairOpResponsePayloadBuilder> {

  public ReKeyKeyPairOpResponsePayloadJsonDeserializer() {
    super(ReKeyKeyPairOpResponsePayload.kmipTag, ReKeyKeyPairOpResponsePayload.encodingType);
  }

  @Override
  protected ReKeyKeyPairOpResponsePayload.ReKeyKeyPairOpResponsePayloadBuilder createBuilder() {
    return ReKeyKeyPairOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      ReKeyKeyPairOpResponsePayload.ReKeyKeyPairOpResponsePayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.PRIVATE_KEY_UNIQUE_IDENTIFIER ->
          builder.privateKeyUniqueIdentifier(ctxt.readValue(p, PrivateKeyUniqueIdentifier.class));
      case KmipTag.Standard.PUBLIC_KEY_UNIQUE_IDENTIFIER ->
          builder.publicKeyUniqueIdentifier(ctxt.readValue(p, PublicKeyUniqueIdentifier.class));
      case KmipTag.Standard.PRIVATE_KEY_TEMPLATE_ATTRIBUTE ->
          builder.privateKeyTemplateAttribute(ctxt.readValue(p, PrivateKeyTemplateAttribute.class));
      case KmipTag.Standard.PUBLIC_KEY_TEMPLATE_ATTRIBUTE ->
          builder.publicKeyTemplateAttribute(ctxt.readValue(p, PublicKeyTemplateAttribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ReKeyKeyPairOpResponsePayload build(
      ReKeyKeyPairOpResponsePayload.ReKeyKeyPairOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
