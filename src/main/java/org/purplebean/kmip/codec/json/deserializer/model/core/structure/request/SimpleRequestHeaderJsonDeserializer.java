package org.purplebean.kmip.codec.json.deserializer.model.core.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.structure.request.SimpleRequestHeader;

/**
 * JSON deserializer for {@link SimpleRequestHeader}.
 */
public class SimpleRequestHeaderJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<SimpleRequestHeader,
        SimpleRequestHeader.SimpleRequestHeaderBuilder> {

  /**
   * Constructs a new {@link SimpleRequestHeaderJsonDeserializer}.
   */
  public SimpleRequestHeaderJsonDeserializer() {
    super(SimpleRequestHeader.kmipTag, SimpleRequestHeader.encodingType);
  }

  @Override
  protected SimpleRequestHeader.SimpleRequestHeaderBuilder createBuilder() {
    return SimpleRequestHeader.builder();
  }

  @Override
  protected void setValue(SimpleRequestHeader.SimpleRequestHeaderBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.PROTOCOL_VERSION ->
          builder.protocolVersion(ctxt.readValue(p, ProtocolVersion.class));
      default -> {
        while (p.nextToken() != JsonToken.END_OBJECT) ;
      }
    }
  }

  @Override
  protected SimpleRequestHeader build(SimpleRequestHeader.SimpleRequestHeaderBuilder builder) {
    return builder.build();
  }
}