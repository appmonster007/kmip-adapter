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
import org.purpleBean.kmip.common.D;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcdsaPrivateKey;

import java.io.IOException;
import java.util.Map;

public class TransparentEcdsaPrivateKeyXmlDeserializer extends KmipDataTypeXmlDeserializer<TransparentEcdsaPrivateKey> {
    private final KmipTag kmipTag = TransparentEcdsaPrivateKey.kmipTag;
    private final EncodingType encodingType = TransparentEcdsaPrivateKey.encodingType;

    @Override
    public TransparentEcdsaPrivateKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(TransparentEcdsaPrivateKey.class, "Expected XML object for TransparentEcdsaPrivateKey");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(TransparentEcdsaPrivateKey.class, "Invalid Tag for TransparentEcdsaPrivateKey");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        TransparentEcdsaPrivateKey.TransparentEcdsaPrivateKeyBuilder builder = TransparentEcdsaPrivateKey.builder();

        // Process all fields in the XML
        var fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        TransparentEcdsaPrivateKey transparentEcdsaPrivateKey = builder.build();

        if (!transparentEcdsaPrivateKey.isSupported()) {
            ctxt.reportInputMismatch(TransparentEcdsaPrivateKey.class, "TransparentEcdsaPrivateKey not supported for spec " + spec);
            return null;
        }

        return transparentEcdsaPrivateKey;
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
    private void setValue(TransparentEcdsaPrivateKey.TransparentEcdsaPrivateKeyBuilder builder, KmipTag.Value nodeTag, JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.RECOMMENDED_CURVE ->
                    builder.recommendedCurve(p.getCodec().treeToValue(node, RecommendedCurve.class));
            case KmipTag.Standard.D -> builder.d(p.getCodec().treeToValue(node, D.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}