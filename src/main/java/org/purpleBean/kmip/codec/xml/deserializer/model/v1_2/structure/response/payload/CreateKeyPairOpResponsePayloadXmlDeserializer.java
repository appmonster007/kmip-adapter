package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purpleBean.kmip.model.core.type.PublicKeyUniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.CreateKeyPairOpResponsePayload;

public class CreateKeyPairOpResponsePayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CreateKeyPairOpResponsePayload,
        CreateKeyPairOpResponsePayload.CreateKeyPairOpResponsePayloadBuilder> {

  public CreateKeyPairOpResponsePayloadXmlDeserializer() {
    super(CreateKeyPairOpResponsePayload.kmipTag, CreateKeyPairOpResponsePayload.encodingType);
  }

  @Override
  protected CreateKeyPairOpResponsePayload.CreateKeyPairOpResponsePayloadBuilder createBuilder() {
    return CreateKeyPairOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      CreateKeyPairOpResponsePayload.CreateKeyPairOpResponsePayloadBuilder builder, String tag,
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
  protected CreateKeyPairOpResponsePayload build(
      CreateKeyPairOpResponsePayload.CreateKeyPairOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}