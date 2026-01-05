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
import org.purpleBean.kmip.common.KeyValueLocationValue;
import org.purpleBean.kmip.common.enumeration.KeyValueLocationType;
import org.purpleBean.kmip.common.structure.KeyValueLocation;

import java.io.IOException;
import java.util.Map;

public class KeyValueLocationXmlDeserializer extends KmipDataTypeXmlDeserializer<KeyValueLocation> {
    private final KmipTag kmipTag = KeyValueLocation.kmipTag;
    private final EncodingType encodingType = KeyValueLocation.encodingType;

    @Override
    public KeyValueLocation deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(KeyValueLocation.class, "Expected XML object for KeyValueLocation");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(KeyValueLocation.class, "Invalid Tag for KeyValueLocation");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        KeyValueLocation.KeyValueLocationBuilder builder = KeyValueLocation.builder();

        // Process all fields in the XML
        for (Map.Entry<String, JsonNode> entry : node.properties()) {
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        KeyValueLocation keyvaluelocation = builder.build();

        if (!keyvaluelocation.isSupported()) {
            ctxt.reportInputMismatch(KeyValueLocation.class, "KeyValueLocation not supported for spec " + spec);
            return null;
        }

        return keyvaluelocation;
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
    private void setValue(
            KeyValueLocation.KeyValueLocationBuilder builder,
            KmipTag.Value nodeTag,
            JsonNode node,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.KEY_VALUE_LOCATION_TYPE ->
                    builder.keyValueLocationType(p.getCodec().treeToValue(node, KeyValueLocationType.class));
            case KmipTag.Standard.KEY_VALUE_LOCATION_VALUE ->
                    builder.keyValueLocationValue(p.getCodec().treeToValue(node, KeyValueLocationValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}