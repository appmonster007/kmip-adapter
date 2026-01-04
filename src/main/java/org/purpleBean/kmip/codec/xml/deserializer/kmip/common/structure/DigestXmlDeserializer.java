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
import org.purpleBean.kmip.common.DigestValue;
import org.purpleBean.kmip.common.enumeration.HashingAlgorithm;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;
import org.purpleBean.kmip.common.structure.Digest;

import java.io.IOException;
import java.util.Map;

public class DigestXmlDeserializer extends KmipDataTypeXmlDeserializer<Digest> {
    private final KmipTag kmipTag = Digest.kmipTag;
    private final EncodingType encodingType = Digest.encodingType;

    @Override
    public Digest deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(Digest.class, "Expected XML object for Digest");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(Digest.class, "Invalid Tag for Digest");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        Digest.DigestBuilder builder = Digest.builder();

        // Process all fields in the XML
        for (Map.Entry<String, JsonNode> entry : node.properties()) {
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        Digest digest = builder.build();

        if (!digest.isSupported()) {
            ctxt.reportInputMismatch(Digest.class, "Digest not supported for spec " + spec);
            return null;
        }

        return digest;
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
            Digest.DigestBuilder builder,
            KmipTag.Value nodeTag,
            JsonNode node,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.HASHING_ALGORITHM ->
                    builder.hashingAlgorithm(p.getCodec().treeToValue(node, HashingAlgorithm.class));
            case KmipTag.Standard.DIGEST_VALUE ->
                    builder.digestValue(p.getCodec().treeToValue(node, DigestValue.class));
            case KmipTag.Standard.KEY_FORMAT_TYPE ->
                    builder.keyFormatType(p.getCodec().treeToValue(node, KeyFormatType.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}
