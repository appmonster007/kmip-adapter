package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.PgpKeyVersion;

/**
 * JSON deserializer for {@link PgpKeyVersion}.
 */
public class PgpKeyVersionJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<PgpKeyVersion, PgpKeyVersion.PgpKeyVersionBuilder> {

  /**
   * Constructs a new {@link PgpKeyVersionJsonDeserializer}.
   */
  public PgpKeyVersionJsonDeserializer() {
    super(PgpKeyVersion.kmipTag, PgpKeyVersion.encodingType);
  }

  @Override
  protected PgpKeyVersion.PgpKeyVersionBuilder createBuilder() {
    return PgpKeyVersion.builder();
  }

  @Override
  protected void setValue(PgpKeyVersion.PgpKeyVersionBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected PgpKeyVersion build(PgpKeyVersion.PgpKeyVersionBuilder builder) {
    return builder.build();
  }
}
