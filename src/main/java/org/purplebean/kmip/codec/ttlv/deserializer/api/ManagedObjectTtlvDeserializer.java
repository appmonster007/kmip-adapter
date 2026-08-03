package org.purplebean.kmip.codec.ttlv.deserializer.api;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.api.ManagedObject;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.ObjectType;

/**
 * TTLV deserializer for {@link ManagedObject} objects.
 *
 * <p>This class extends {@link KmipDataTypeTtlvDeserializer} to handle the specific logic required
 * for deserializing KMIP Managed Objects from TTLV. It uses the {@code objectType} attribute
 * from the deserialization context (mapper) to determine the concrete class to instantiate,
 * falling back to the standard registry lookup if the object type is not present.
 */
public class ManagedObjectTtlvDeserializer extends KmipDataTypeTtlvDeserializer<ManagedObject> {

  @Override
  public ManagedObject deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
    return super.deserialize(ttlvBuffer, mapper);
  }

  @Override
  public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag,
                                                            EncodingType encodingType,
                                                            TtlvMapper mapper) {
    String ctxtObjectType = (String) mapper.getAttribute("objectType");
    ObjectType.Value objectTypeValue;

    if (ctxtObjectType != null) {
      objectTypeValue = ObjectType.fromName(ctxtObjectType);
      return ManagedObject.getClassFromRegistry(encodingType, objectTypeValue);
    }

    return KmipDataType.getClassFromRegistry(kmipTag, encodingType);
  }
}
