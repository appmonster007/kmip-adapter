package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.BlockCipherMode;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for BlockCipherMode.
 */
public class BlockCipherModeXmlDeserializer extends KmipDataTypeXmlDeserializer<BlockCipherMode> {

    @Override
    public BlockCipherMode deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(BlockCipherMode.class, "Expected XML element object for BlockCipherMode");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !EncodingType.ENUMERATION.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(BlockCipherMode.class, "Missing or invalid '@type' attribute for BlockCipherMode");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(BlockCipherMode.class, "Missing or non-text '@value' attribute for BlockCipherMode");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        BlockCipherMode blockciphermode = new BlockCipherMode(BlockCipherMode.fromName(spec, description));
        if (!blockciphermode.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("BlockCipherMode '%s' not supported for spec %s", description, spec));
        }

        return blockciphermode;
    }
}
