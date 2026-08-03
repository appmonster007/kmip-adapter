package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.structure.CommonAttributes;
import org.purplebean.kmip.model.v2x1.structure.PrivateKeyAttributes;
import org.purplebean.kmip.model.v2x1.structure.PublicKeyAttributes;
import org.purplebean.kmip.model.v2x1.structure.request.payload.CreateKeyPairOpRequestPayload;

/**
 * JSON deserializer for {@link CreateKeyPairOpRequestPayload}.
 */
public class CreateKeyPairOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CreateKeyPairOpRequestPayload,
        CreateKeyPairOpRequestPayload.CreateKeyPairOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link CreateKeyPairOpRequestPayloadJsonDeserializer}.
   */
  public CreateKeyPairOpRequestPayloadJsonDeserializer() {
    super(CreateKeyPairOpRequestPayload.kmipTag, CreateKeyPairOpRequestPayload.encodingType);
  }

  @Override
  protected CreateKeyPairOpRequestPayload.CreateKeyPairOpRequestPayloadBuilder createBuilder() {
    return CreateKeyPairOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      CreateKeyPairOpRequestPayload.CreateKeyPairOpRequestPayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.COMMON_ATTRIBUTES ->
          builder.commonAttributes(ctxt.readValue(p, CommonAttributes.class));
      case KmipTag.Standard.PRIVATE_KEY_ATTRIBUTES ->
          builder.privateKeyAttributes(ctxt.readValue(p, PrivateKeyAttributes.class));
      case KmipTag.Standard.PUBLIC_KEY_ATTRIBUTES ->
          builder.publicKeyAttributes(ctxt.readValue(p, PublicKeyAttributes.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CreateKeyPairOpRequestPayload build(
      CreateKeyPairOpRequestPayload.CreateKeyPairOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}