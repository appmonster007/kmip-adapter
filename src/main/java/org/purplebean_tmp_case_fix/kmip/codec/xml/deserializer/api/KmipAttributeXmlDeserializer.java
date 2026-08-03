package org.purplebean.kmip.codec.xml.deserializer.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipAttribute;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipTag;

/**
 * XML deserializer for {@link KmipAttribute} objects.
 * <p>
 * This class extends {@link KmipDataTypeXmlDeserializer} to handle the specific logic required
 * for deserializing KMIP Attributes from XML. It delegates the class lookup to the
 * {@link KmipAttribute} registry.
 */
public class KmipAttributeXmlDeserializer extends KmipDataTypeXmlDeserializer<KmipAttribute> {

  @Override
  public KmipAttribute deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return super.deserialize(p, ctxt);
  }

  @Override
  public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag,
                                                            EncodingType encodingType,
                                                            DeserializationContext ctxt) {
    return KmipAttribute.getClassFromRegistry(kmipTag, encodingType);
  }
}
