package org.purplebean.kmip.codec.xml.deserializer.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;

/**
 * Abstract base class for custom XML deserializers of specific {@link KmipDataType}
 * implementations.
 *
 * <p>This class provides a template for deserializing complex KMIP objects from XML. It handles
 * the validation of the KMIP tag and encoding type, and orchestrates the parsing of the
 * XML structure into a builder object, which is then used to construct the final result.
 * It also handles the differences between Jackson's XML and JSON tree models, particularly
 * for nested structures and text content.
 *
 * @param <T> The type of {@link KmipDataType} being deserialized.
 * @param <B> The type of the builder used to construct the object.
 */
public abstract class AbstractKmipDataTypeXmlDeserializer<T extends KmipDataType, B>
    extends KmipDataTypeXmlDeserializer<T> {

  private final KmipTag kmipTag;
  private final EncodingType encodingType;

  /**
   * Constructs a new deserializer for the specified KMIP tag and encoding type.
   *
   * @param kmipTag      The expected KMIP tag of the object.
   * @param encodingType The expected encoding type of the object.
   */
  protected AbstractKmipDataTypeXmlDeserializer(KmipTag kmipTag, EncodingType encodingType) {
    this.kmipTag = kmipTag;
    this.encodingType = encodingType;
  }

  @Override
  public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    if (p.currentToken() == null) {
      p.nextToken();
    }

    String xmlTagName = null;
    if (p instanceof FromXmlParser xmlParser) {
      xmlTagName = xmlParser
          .getStaxReader()
          .getLocalName();
    } else {
      xmlTagName = (String) ctxt.getAttribute("tag");
    }

    JsonNode node = ctxt.readTree(p);

    if (node == null) {
      ctxt.reportInputMismatch(handledType(), "XML node cannot be null");
      return null;
    }

    B builder = createBuilder();
    String tag = getTag(xmlTagName, node, ctxt, builder);
    if (tag == null) {
      return null;
    }

    String type = getType(node, ctxt, builder);
    if (type == null) {
      return null;
    }

    if (EncodingType.STRUCTURE
        .getDescription()
        .equals(type)) {
      Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
      while (fields.hasNext()) {
        Map.Entry<String, JsonNode> field = fields.next();
        String fieldName = field.getKey();
        JsonNode fieldValue = field.getValue();

        if ("type".equals(fieldName) || "tag".equals(fieldName)) {
          continue;
        }

        if (fieldValue.isArray()) {
          for (JsonNode item : fieldValue) {
            processChild(builder, fieldName, type, item, p, ctxt);
          }
        } else {
          processChild(builder, fieldName, type, fieldValue, p, ctxt);
        }
      }
    } else {
      JsonNode valueNode = node.get("value");
      if (valueNode == null) {
        // Check for text content which Jackson XML maps to empty string key
        valueNode = node.get("");
      }

      if (valueNode == null) {
        ctxt.reportInputMismatch(handledType(), "Missing 'value' field");
        return null;
      }
      JsonParser valueParser = valueNode.traverse(p.getCodec());
      valueParser.nextToken();
      setValue(builder, tag, type, valueParser, ctxt);
    }

    T result = build(builder);

    verifyVersionSupport(result);
    return result;
  }

  private void processChild(B builder, String fieldName, String parentType, JsonNode node,
                            JsonParser p, DeserializationContext ctxt) throws IOException {
    String itemTag = fieldName;
    if ("TTLV".equals(itemTag) && node.has("tag")) {
      itemTag = node
          .get("tag")
          .asText();
    }
    ctxt.setAttribute("tag", itemTag);
    JsonParser childParser = node.traverse(p.getCodec());
    childParser.nextToken();
    setValue(builder, itemTag, parentType, childParser, ctxt);
  }

  /**
   * Verifies that the deserialized object is supported by the current KMIP specification.
   *
   * @param result The deserialized object.
   * @throws NoSuchElementException if the object is not supported.
   */
  protected void verifyVersionSupport(T result) {
    KmipSpec spec = KmipContext.getSpec();
    if (!result.isSupported()) {
      throw new NoSuchElementException(
          String.format("%s not supported for spec %s", handledType().getSimpleName(), spec));
    }
  }

  /**
   * Extracts and validates the KMIP tag from the XML element name or attributes.
   *
   * @param xmlTagName The XML element name.
   * @param node       The JSON node representing the XML element.
   * @param ctxt       The deserialization context.
   * @param builder    The builder object.
   * @return The tag string if valid, or null if validation fails.
   * @throws IOException if an I/O error occurs.
   */
  protected String getTag(String xmlTagName, JsonNode node, DeserializationContext ctxt, B builder)
      throws IOException {
    String tag = xmlTagName;
    if ("TTLV".equals(tag) && node.has("tag")) {
      tag = node
          .get("tag")
          .asText();
      return tag;
    }

    if (kmipTag != null && kmipTag
        .getDescription()
        .equalsIgnoreCase(tag)) {
      return tag;
    }

    if (kmipTag != null) {
      ctxt.reportInputMismatch(handledType(),
          "Expected object with tag " + kmipTag.getDescription());
      return null;
    }
    return tag;
  }

  /**
   * Extracts and validates the encoding type from the XML attributes.
   *
   * @param node    The JSON node representing the XML element.
   * @param ctxt    The deserialization context.
   * @param builder The builder object.
   * @return The type string if valid, or null if validation fails.
   * @throws IOException if an I/O error occurs.
   */
  protected String getType(JsonNode node, DeserializationContext ctxt, B builder)
      throws IOException {
    JsonNode typeNode = node.get("type");
    if (typeNode == null || !typeNode.isTextual()) {
      if (encodingType == EncodingType.STRUCTURE) {
        return EncodingType.STRUCTURE.getDescription();
      }
      ctxt.reportInputMismatch(handledType(), "Missing or invalid 'type' field");
      return null;
    }

    String type = typeNode.asText();
    if (encodingType != null && !encodingType
        .getDescription()
        .equals(type)) {
      ctxt.reportInputMismatch(handledType(), "Missing or invalid 'type' field");
      return null;
    }
    return type;
  }

  /**
   * Creates a new builder instance for constructing the object.
   *
   * @return A new builder instance.
   */
  protected abstract B createBuilder();

  /**
   * Sets a value on the builder based on the parsed XML data.
   *
   * @param builder The builder instance.
   * @param tag     The tag of the value being set.
   * @param type    The type of the value being set.
   * @param p       The JSON parser positioned at the value.
   * @param ctxt    The deserialization context.
   * @throws IOException if an I/O error occurs.
   */
  protected abstract void setValue(B builder, String tag, String type, JsonParser p,
                                   DeserializationContext ctxt) throws IOException;

  /**
   * Builds the final object from the builder.
   *
   * @param builder The builder instance.
   * @return The constructed object.
   */
  protected abstract T build(B builder);

}
