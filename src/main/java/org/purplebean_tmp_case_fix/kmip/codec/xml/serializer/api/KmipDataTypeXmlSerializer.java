package org.purplebean.kmip.codec.xml.serializer.api;

import com.fasterxml.jackson.databind.JsonSerializer;
import org.purplebean.kmip.api.KmipDataType;

/**
 * Base XML serializer for {@link KmipDataType} objects.
 * <p>
 * This abstract class extends Jackson's {@link JsonSerializer} and provides a mechanism
 * to automatically determine the handled type based on the generic type argument of the
 * concrete subclass. This simplifies the registration of serializers with the Jackson
 * module.
 *
 * @param <T> The specific type of {@link KmipDataType} to serialize.
 */
public abstract class KmipDataTypeXmlSerializer<T extends KmipDataType> extends JsonSerializer<T> {
  @SuppressWarnings("unchecked")
  @Override
  public Class<T> handledType() {
    java.lang.reflect.Type superType = getClass().getGenericSuperclass();
    if (superType instanceof java.lang.reflect.ParameterizedType pt) {
      java.lang.reflect.Type tArg = pt.getActualTypeArguments()[0];
      if (tArg instanceof Class<?> c) {
        return (Class<T>) c;
      }
    }
    return super.handledType();
  }
}
