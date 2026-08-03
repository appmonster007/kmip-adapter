package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.ManagedObject;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.Attributes;
import org.purplebean.kmip.model.v2x1.structure.response.payload.ExportOpResponsePayload;

/**
 * TTLV deserializer for {@link ExportOpResponsePayload}.
 */
public class ExportOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ExportOpResponsePayload,
        ExportOpResponsePayload.ExportOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link ExportOpResponsePayloadTtlvDeserializer}.
   */
  public ExportOpResponsePayloadTtlvDeserializer() {
    super(ExportOpResponsePayload.kmipTag, ExportOpResponsePayload.encodingType);
  }

  @Override
  protected ExportOpResponsePayload.ExportOpResponsePayloadBuilder createBuilder() {
    return ExportOpResponsePayload.builder();
  }

  @Override
  protected void setValue(ExportOpResponsePayload.ExportOpResponsePayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_TYPE ->
          builder.objectType(mapper.readValue(p, ObjectType.class));
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.ATTRIBUTES -> builder.attributes(mapper.readValue(p, Attributes.class));
      default -> {
        if (ManagedObject.isManagedObject(nodeTag)) {
          builder.object(mapper.readValue(p, ManagedObject.class));
        } else {
          throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
      }
    }
  }

  @Override
  protected ExportOpResponsePayload build(
      ExportOpResponsePayload.ExportOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}