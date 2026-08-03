package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.PgpKeyVersion;

/**
 * XML deserializer for {@link PgpKeyVersion}.
 */
public class PgpKeyVersionXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<PgpKeyVersion, PgpKeyVersion.PgpKeyVersionBuilder> {

  /**
   * Constructs a new {@link PgpKeyVersionXmlDeserializer}.
   */
  public PgpKeyVersionXmlDeserializer() {
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