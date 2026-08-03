package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.KeyCompressionType;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.model.core.structure.KeyWrappingSpecification;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.GetOpRequestPayload;

/**
 * JSON deserializer for {@link GetOpRequestPayload}.
 */
public class GetOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<GetOpRequestPayload,
        GetOpRequestPayload.GetOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link GetOpRequestPayloadJsonDeserializer}.
   */
  public GetOpRequestPayloadJsonDeserializer() {
    super(GetOpRequestPayload.kmipTag, GetOpRequestPayload.encodingType);
  }

  @Override
  protected GetOpRequestPayload.GetOpRequestPayloadBuilder createBuilder() {
    return GetOpRequestPayload.builder();
  }

  @Override
  protected void setValue(GetOpRequestPayload.GetOpRequestPayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.KEY_FORMAT_TYPE ->
          builder.keyFormatType(ctxt.readValue(p, KeyFormatType.class));
      case KmipTag.Standard.KEY_WRAP_TYPE -> builder.keyWrapType(
          ctxt.readValue(p, org.purplebean.kmip.model.core.enumeration.KeyWrapType.class));
      case KmipTag.Standard.KEY_COMPRESSION_TYPE ->
          builder.keyCompressionType(ctxt.readValue(p, KeyCompressionType.class));
      case KmipTag.Standard.KEY_WRAPPING_SPECIFICATION ->
          builder.keyWrappingSpecification(ctxt.readValue(p, KeyWrappingSpecification.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected GetOpRequestPayload build(GetOpRequestPayload.GetOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}