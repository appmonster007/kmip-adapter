package org.purplebean.kmip.codec.json.deserializer.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipTag;

/**
 * Base JSON deserializer for {@link KmipDataType} objects.
 *
 * <p>This class provides the common logic for deserializing KMIP data types from JSON.
 * It handles the extraction of the KMIP tag and encoding type from the JSON structure
 * and uses the {@link KmipDataType} registry to find the appropriate concrete class
 * to instantiate.
 *
 * @param <T> The specific type of {@link KmipDataType} to deserialize.
 */
public class KmipDataTypeJsonDeserializer<T extends KmipDataType>
    extends JsonDeserializer<KmipDataType> {

  @Override
  public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    JsonNode node = ctxt.readTree(p);

    JsonNode tagNode = node.get("tag");
    JsonNode typeNode = node.get("type");

    if (tagNode == null || !tagNode.isTextual() || typeNode == null || !typeNode.isTextual()) {
      ctxt.handleUnexpectedToken(KmipDataType.class, p);
      return null;
    }

    KmipTag.Value kmipTagValue = KmipTag.fromName(tagNode.asText());
    Optional<EncodingType> encodingType = EncodingType.fromName(typeNode.asText());

    if (encodingType.isEmpty()) {
      ctxt.handleUnexpectedToken(KmipDataType.class, p);
      return null;
    }

    Class<? extends KmipDataType> clazz =
        getKmipDataTypeClass(kmipTagValue, encodingType.get(), ctxt);
    if (clazz == null) {
      throw new NoSuchElementException(
          String.format("No class registered for tag %s and encoding type %s",
              kmipTagValue.getValue(), encodingType.get()));
    }

    JsonParser valueParser = node.traverse(p.getCodec());
    valueParser.nextToken();
    return (T) ctxt.readValue(valueParser, clazz);
  }

  /**
   * Determines the concrete {@link KmipDataType} class to instantiate based on the tag and
   * encoding type.
   *
   * @param kmipTag      The KMIP tag.
   * @param encodingType The encoding type.
   * @param ctxt         The deserialization context.
   * @return The concrete class to instantiate.
   */
  public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag,
                                                            EncodingType encodingType,
                                                            DeserializationContext ctxt) {
    return KmipDataType.getClassFromRegistry(kmipTag, encodingType);
  }

  @Override
  public Class<?> handledType() {
    Type superType = getClass().getGenericSuperclass();
    if (superType instanceof ParameterizedType pt) {
      Type typeArg = pt.getActualTypeArguments()[0];
      if (typeArg instanceof Class<?> c) {
        return c;
      }
      if (typeArg instanceof ParameterizedType parameterized) {
        Type raw = parameterized.getRawType();
        if (raw instanceof Class<?> rc) {
          return rc;
        }
      }
    }
    return super.handledType();
  }
}
