package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.ManagedObject;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.GetOpResponsePayload;

/**
 * JSON deserializer for {@link GetOpResponsePayload}.
 */
public class GetOpResponsePayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<GetOpResponsePayload,
        GetOpResponsePayload.GetOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link GetOpResponsePayloadJsonDeserializer}.
   */
  public GetOpResponsePayloadJsonDeserializer() {
    super(GetOpResponsePayload.kmipTag, GetOpResponsePayload.encodingType);
  }

  @Override
  protected GetOpResponsePayload.GetOpResponsePayloadBuilder createBuilder() {
    return GetOpResponsePayload.builder();
  }

  @Override
  protected void setValue(GetOpResponsePayload.GetOpResponsePayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(ctxt.readValue(p, ObjectType.class));
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      default -> {
        if (ManagedObject.isManagedObject(nodeTag)) {
          builder.object(ctxt.readValue(p, ManagedObject.class));
        } else {
          throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
      }
    }
  }

  @Override
  protected GetOpResponsePayload build(GetOpResponsePayload.GetOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
