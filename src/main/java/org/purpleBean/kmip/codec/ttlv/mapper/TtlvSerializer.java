package org.purplebean.kmip.codec.ttlv.mapper;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;

/**
 * An abstract base class for creating custom serializers that convert a Java object
 * into its TTLV (Tag-Type-Length-Value) byte representation.
 * <p>
 * This class provides the core logic for serialization and includes a mechanism to automatically
 * determine the target type {@code <T>} that the serializer handles. This is essential for the
 * {@link TtlvMapper} to correctly associate the serializer with the appropriate class.
 *
 * <p><b>Key Features:</b></p>
 * <ul>
 *   <li><b>Serialization Logic:</b> The main responsibility of a concrete implementation is to
 *   provide
 *       the logic for the {@link #serialize(Object, TtlvMapper)} method, which takes an object
 *       and converts it into a {@link ByteBuffer} containing the TTLV data.</li>
 *   <li><b>Type Inference:</b> The {@link #handledType()} method uses reflection to
 *   automatically infer the
 *       generic type {@code <T>} that the serializer is designed for. This simplifies registration
 *       by removing the need to explicitly specify the target class.</li>
 *   <li><b>Recursive Serialization:</b> The provided {@link TtlvMapper} instance allows for
 *   recursive
 *       serialization of nested objects, enabling the handling of complex data structures.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * To create a custom serializer, extend this class and implement the abstract {@code serialize}
 * method.
 * The handled type should be specified as a generic parameter.
 *
 * <pre>
 * {@code
 * public class MyObjectSerializer extends TtlvSerializer<MyObject> {
 *     @Override
 *     public ByteBuffer serialize(MyObject value, TtlvMapper mapper) throws IOException {
 *         // Convert MyObject to TTLV byte representation
 *         // ...
 *         return ByteBuffer.wrap(ttlvBytes);
 *     }
 * }
 * }
 * </pre>
 *
 * @param <T> The type of the object that this serializer handles.
 * @see TtlvMapper
 * @see TtlvDeserializer
 */
public abstract class TtlvSerializer<T> {

  /**
   * Serializes an object of type {@code T} into its TTLV byte representation.
   *
   * @param value  The object to be serialized.
   * @param mapper The {@link TtlvMapper} instance that can be used to recursively serialize
   *               nested objects.
   * @return A {@link ByteBuffer} containing the TTLV representation of the object.
   * @throws IOException if an I/O error occurs during serialization.
   */
  public abstract ByteBuffer serialize(T value, TtlvMapper mapper) throws IOException;


  /**
   * Infers and returns the class of the type {@code T} that this serializer handles.
   * <p>
   * This method uses reflection to inspect the generic type arguments of the class that extends
   * {@code TtlvSerializer}. It first checks the generic superclass and then falls back to
   * implemented interfaces. This allows the {@link TtlvMapper} to automatically register the
   * serializer for the correct target type.
   *
   * @return The {@link Class} of the handled type, or {@code null} if it cannot be determined.
   */
  @SuppressWarnings("unchecked")
  public Class<T> handledType() {
    // Prefer generic superclass (if subclasses extend this with a concrete type)
    Type superType = getClass().getGenericSuperclass();
    if (superType instanceof ParameterizedType pt) {
      Type tArg = pt.getActualTypeArguments()[0];
      if (tArg instanceof Class<?> c) {
        return (Class<T>) c;
      }
    }
    // Fallback: try the implemented interfaces to locate TtlvSerializer<T>
    for (Type itf : getClass().getGenericInterfaces()) {
      if (itf instanceof ParameterizedType itfPt) {
        if (itfPt.getRawType() instanceof Class<?> raw && raw
            .getName()
            .equals(TtlvSerializer.class.getName())) {
          Type tArg = itfPt.getActualTypeArguments()[0];
          if (tArg instanceof Class<?> c) {
            return (Class<T>) c;
          }
        }
      }
    }
    return null;
  }
}
