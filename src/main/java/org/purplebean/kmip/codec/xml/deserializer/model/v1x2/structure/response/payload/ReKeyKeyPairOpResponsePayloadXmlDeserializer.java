package org.purplebean.kmip.codec.xml.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purplebean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purplebean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purplebean.kmip.model.core.type.PublicKeyUniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.ReKeyKeyPairOpResponsePayload;

/**
 * XML deserializer for {@link ReKeyKeyPairOpResponsePayload}.
 */
public class ReKeyKeyPairOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ReKeyKeyPairOpResponsePayload,
        ReKeyKeyPairOpResponsePayload.ReKeyKeyPairOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link ReKeyKeyPairOpResponsePayloadXmlDeserializer}.
   */
  public ReKeyKeyPairOpResponsePayloadXmlDeserializer() {
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