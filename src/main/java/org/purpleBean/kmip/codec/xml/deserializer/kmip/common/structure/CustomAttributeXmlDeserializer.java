package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.common.structure.CustomAttribute;

import java.io.IOException;
import java.util.Map;

public class CustomAttributeXmlDeserializer extends KmipDataTypeXmlDeserializer<CustomAttribute> {
    private final KmipTag kmipTag = CustomAttribute.kmipTag;
    private final EncodingType encodingType = CustomAttribute.encodingType;

    @Override
    public CustomAttribute deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(CustomAttribute.class, "Expected XML object for CustomAttribute");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(CustomAttribute.class, "Invalid Tag for CustomAttribute");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        CustomAttribute.CustomAttributeBuilder builder = CustomAttribute.builder();

        // Process all fields in the XML
        var fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        CustomAttribute customAttribute = builder.build();

        if (!customAttribute.isSupported()) {
            ctxt.reportInputMismatch(CustomAttribute.class, "CustomAttribute not supported for spec " + spec);
            return null;
        }

        return customAttribute;
    }

    /**
     * Sets the appropriate field in the builder based on the tag and value.
     *
     * @param builder the builder to set the field on
     * @param nodeTag the tag identifying the field to set
     * @param node    the XML node containing the field value
     * @param p       the JsonParser
     * @param ctxt    the DeserializationContext
     * @throws IOException if there is an error deserializing the value
     */
    private void setValue(CustomAttribute.CustomAttributeBuilder builder, KmipTag.Value nodeTag, JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ATTRIBUTE_NAME ->
                    builder.attributeName(p.getCodec().treeToValue(node, AttributeName.class));
            case KmipTag.Standard.ATTRIBUTE_VALUE ->
                    builder.attributeValue(p.getCodec().treeToValue(node, AttributeValue.class));
            default -> throw new IllegalArgumentException();
        }
    }
}
