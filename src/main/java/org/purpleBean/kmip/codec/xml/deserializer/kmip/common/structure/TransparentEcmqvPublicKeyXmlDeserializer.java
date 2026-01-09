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
import org.purpleBean.kmip.common.structure.TransparentEcmqvPublicKey;

import java.io.IOException;
import java.util.Map;

public class TransparentEcmqvPublicKeyXmlDeserializer extends KmipDataTypeXmlDeserializer<TransparentEcmqvPublicKey> {
    private final KmipTag kmipTag = TransparentEcmqvPublicKey.kmipTag;
    private final EncodingType encodingType = TransparentEcmqvPublicKey.encodingType;

    @Override
    public TransparentEcmqvPublicKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(TransparentEcmqvPublicKey.class, "Expected XML object for TransparentEcmqvPublicKey");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(TransparentEcmqvPublicKey.class, "Invalid Tag for TransparentEcmqvPublicKey");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        TransparentEcmqvPublicKey.TransparentEcmqvPublicKeyBuilder builder = TransparentEcmqvPublicKey.builder();

        // Process all fields in the XML
        var fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        TransparentEcmqvPublicKey transparentEcmqvPublicKey = builder.build();

        if (!transparentEcmqvPublicKey.isSupported()) {
            ctxt.reportInputMismatch(TransparentEcmqvPublicKey.class, "TransparentEcmqvPublicKey not supported for spec " + spec);
            return null;
        }

        return transparentEcmqvPublicKey;
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
    private void setValue(TransparentEcmqvPublicKey.TransparentEcmqvPublicKeyBuilder builder, KmipTag.Value nodeTag, JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.RECOMMENDED_CURVE ->
                    builder.recommendedCurve(p.getCodec().treeToValue(node, RecommendedCurve.class));
            case KmipTag.Standard.Q_STRING -> builder.qString(p.getCodec().treeToValue(node, QString.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}