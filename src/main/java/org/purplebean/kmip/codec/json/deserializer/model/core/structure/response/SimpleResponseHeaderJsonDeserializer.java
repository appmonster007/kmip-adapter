package org.purplebean.kmip.codec.json.deserializer.model.core.structure.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.structure.response.SimpleResponseHeader;

/**
 * JSON deserializer for {@link SimpleResponseHeader}.
 */
public class SimpleResponseHeaderJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<SimpleResponseHeader,
        SimpleResponseHeader.SimpleResponseHeaderBuilder> {

  /**
   * Constructs a new {@link SimpleResponseHeaderJsonDeserializer}.
   */
  public SimpleResponseHeaderJsonDeserializer() {
    super(SimpleResponseHeader.kmipTag, SimpleResponseHeader.encodingType);
  }

  @Override
  protected SimpleResponseHeader.SimpleResponseHeaderBuilder createBuilder() {
    return SimpleResponseHeader.builder();
  }

  @Override
  protected void setValue(SimpleResponseHeader.SimpleResponseHeaderBuilder builder, String tag,
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
  protected SimpleResponseHeader build(SimpleResponseHeader.SimpleResponseHeaderBuilder builder) {
    return builder.build();
  }
}
