package org.purplebean.kmip.codec.json.deserializer.model.v3x0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v3x0.type.OtpSeed;

/**
 * JSON deserializer for {@link OtpSeed}.
 */
public class OtpSeedJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<OtpSeed, OtpSeed.OtpSeedBuilder> {

  /**
   * Constructs a new {@link OtpSeedJsonDeserializer}.
   */
  public OtpSeedJsonDeserializer() {
    super(OtpSeed.kmipTag, OtpSeed.encodingType);
  }

  @Override
  protected OtpSeed.OtpSeedBuilder createBuilder() {
    return OtpSeed.builder();
  }

  @Override
  protected void setValue(OtpSeed.OtpSeedBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected OtpSeed build(OtpSeed.OtpSeedBuilder builder) {
    return builder.build();
  }
}