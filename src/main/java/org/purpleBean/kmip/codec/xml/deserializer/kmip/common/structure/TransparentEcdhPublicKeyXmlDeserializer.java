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
import org.purpleBean.kmip.common.QString;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.TransparentEcdhPublicKey;

import java.io.IOException;
import java.util.Map;

public class TransparentEcdhPublicKeyXmlDeserializer extends KmipDataTypeXmlDeserializer<TransparentEcdhPublicKey> {
    private final KmipTag kmipTag = TransparentEcdhPublicKey.kmipTag;
    private final EncodingType encodingType = TransparentEcdhPublicKey.encodingType;

    @Override
    public TransparentEcdhPublicKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(TransparentEcdhPublicKey.class, "Expected XML object for TransparentEcdhPublicKey");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(TransparentEcdhPublicKey.class, "Invalid Tag for TransparentEcdhPublicKey");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        TransparentEcdhPublicKey.TransparentEcdhPublicKeyBuilder builder = TransparentEcdhPublicKey.builder();

        // Process all fields in the XML
        var fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        TransparentEcdhPublicKey transparentEcdhPublicKey = builder.build();

        if (!transparentEcdhPublicKey.isSupported()) {
            ctxt.reportInputMismatch(TransparentEcdhPublicKey.class, "TransparentEcdhPublicKey not supported for spec " + spec);
            return null;
        }

        return transparentEcdhPublicKey;
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
    private void setValue(TransparentEcdhPublicKey.TransparentEcdhPublicKeyBuilder builder, KmipTag.Value nodeTag, JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.RECOMMENDED_CURVE ->
                    builder.recommendedCurve(p.getCodec().treeToValue(node, RecommendedCurve.class));
            case KmipTag.Standard.Q_STRING -> builder.qString(p.getCodec().treeToValue(node, QString.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}