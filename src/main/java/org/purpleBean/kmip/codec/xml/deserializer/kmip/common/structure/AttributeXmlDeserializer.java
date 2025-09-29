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
import org.purpleBean.kmip.common.AttributeIndex;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.common.structure.Attribute;

import java.io.IOException;
import java.util.Map;

public class AttributeXmlDeserializer extends KmipDataTypeXmlDeserializer<Attribute> {
    private final KmipTag kmipTag = Attribute.kmipTag;
    private final EncodingType encodingType = Attribute.encodingType;

    @Override
    public Attribute deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(Attribute.class, "Expected XML object for Attribute");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(Attribute.class, "Invalid Tag for Attribute");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        Attribute.AttributeBuilder builder = Attribute.builder();

        // Process all fields in the XML
        var fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        Attribute attribute = builder.build();

        if (!attribute.isSupported()) {
            ctxt.reportInputMismatch(Attribute.class, "Attribute not supported for spec " + spec);
            return null;
        }

        return attribute;
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
    private void setValue(Attribute.AttributeBuilder builder, KmipTag.Value nodeTag, JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ATTRIBUTE_NAME ->
                    builder.attributeName(p.getCodec().treeToValue(node, AttributeName.class));
            case KmipTag.Standard.ATTRIBUTE_INDEX ->
                    builder.attributeIndex(p.getCodec().treeToValue(node, AttributeIndex.class));
            case KmipTag.Standard.ATTRIBUTE_VALUE ->
                    builder.attributeValue(p.getCodec().treeToValue(node, AttributeValue.class));
            default -> throw new IllegalArgumentException();
        }
    }
}
