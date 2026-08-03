package org.purplebean.kmip.codec.ttlv.deserializer.api;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KeyMaterial;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;

/**
 * TTLV deserializer for {@link KeyMaterial} objects.
 * <p>
 * This class extends {@link KmipDataTypeTtlvDeserializer} to handle the specific logic required
 * for deserializing KMIP Key Material from TTLV. It uses the {@code keyFormatType} attribute
 * from the deserialization context (mapper) to determine the concrete class to instantiate.
 */
public class KeyMaterialTtlvDeserializer extends KmipDataTypeTtlvDeserializer<KeyMaterial> {

  @Override
  public KeyMaterial deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
    return super.deserialize(ttlvBuffer, mapper);
  }

  @Override
  public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag,
                                                            EncodingType encodingType,
                                                            TtlvMapper mapper) {
    String ctxtKeyFormatType = (String) mapper.getAttribute("keyFormatType");
    KeyFormatType.Value keyFormatTypeValue;
    if (ctxtKeyFormatType == null) {
      keyFormatTypeValue = null;
    } else {
      keyFormatTypeValue = KeyFormatType.fromName(ctxtKeyFormatType);
    }
    return KeyMaterial.getClassFromRegistry(encodingType, keyFormatTypeValue);
  }
}
