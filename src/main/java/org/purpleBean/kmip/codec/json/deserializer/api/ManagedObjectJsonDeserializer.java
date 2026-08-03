package org.purpleBean.kmip.codec.json.deserializer.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.ManagedObject;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;

/**
 * JSON deserializer for {@link ManagedObject} objects.
 * <p>
 * This class extends {@link KmipDataTypeJsonDeserializer} to handle the specific logic required
 * for deserializing KMIP Managed Objects from JSON. It uses the {@code objectType} attribute
 * from the deserialization context to determine the concrete class to instantiate, falling back
 * to the standard registry lookup if the object type is not present.
 */
public class ManagedObjectJsonDeserializer extends KmipDataTypeJsonDeserializer<ManagedObject> {

  @Override
  public ManagedObject deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return super.deserialize(p, ctxt);
  }

  @Override
  public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag,
                                                            EncodingType encodingType,
                                                            DeserializationContext ctxt) {
    String ctxtObjectType = (String) ctxt.getAttribute("objectType");
    ObjectType.Value objectTypeValue;

    if (ctxtObjectType != null) {
      objectTypeValue = ObjectType.fromName(ctxtObjectType);
      return ManagedObject.getClassFromRegistry(encodingType, objectTypeValue);
    }

    return KmipDataType.getClassFromRegistry(kmipTag, encodingType);
  }
}
