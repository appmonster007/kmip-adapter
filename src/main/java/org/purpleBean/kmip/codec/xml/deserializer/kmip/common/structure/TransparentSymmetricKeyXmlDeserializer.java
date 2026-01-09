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
import org.purpleBean.kmip.common.Key;
import org.purpleBean.kmip.common.structure.TransparentSymmetricKey;

import java.io.IOException;
import java.util.Map;

public class TransparentSymmetricKeyXmlDeserializer extends KmipDataTypeXmlDeserializer<TransparentSymmetricKey> {
    private final KmipTag kmipTag = TransparentSymmetricKey.kmipTag;
    private final EncodingType encodingType = TransparentSymmetricKey.encodingType;

    @Override
    public TransparentSymmetricKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(TransparentSymmetricKey.class, "Expected XML object for TransparentSymmetricKey");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(TransparentSymmetricKey.class, "Invalid Tag for TransparentSymmetricKey");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        TransparentSymmetricKey.TransparentSymmetricKeyBuilder builder = TransparentSymmetricKey.builder();

        // Process all fields in the XML
        var fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        TransparentSymmetricKey transparentSymmetricKey = builder.build();

        if (!transparentSymmetricKey.isSupported()) {
            ctxt.reportInputMismatch(TransparentSymmetricKey.class, "TransparentSymmetricKey not supported for spec " + spec);
            return null;
        }

        return transparentSymmetricKey;
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
    private void setValue(TransparentSymmetricKey.TransparentSymmetricKeyBuilder builder, KmipTag.Value nodeTag, JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.KEY -> builder.key(p.getCodec().treeToValue(node, Key.class));
            default -> throw new IllegalArgumentException();
        }
    }
}