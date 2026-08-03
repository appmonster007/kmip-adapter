package org.purplebean.kmip.codec.ttlv.deserializer.api;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipAttribute;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;

/**
 * TTLV deserializer for {@link KmipAttribute} objects.
 * <p>
 * This class extends {@link KmipDataTypeTtlvDeserializer} to handle the specific logic required
 * for deserializing KMIP Attributes from TTLV. It delegates the class lookup to the
 * {@link KmipAttribute} registry.
 */
public class KmipAttributeTtlvDeserializer extends KmipDataTypeTtlvDeserializer<KmipAttribute> {

  @Override
  public KmipAttribute deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
    return super.deserialize(ttlvBuffer, mapper);
  }

  @Override
  public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag,
                                                            EncodingType encodingType,
                                                            TtlvMapper mapper) {
    return KmipAttribute.getClassFromRegistry(kmipTag, encodingType);
  }
}
