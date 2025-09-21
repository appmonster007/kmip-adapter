package org.purpleBean.kmip.codec.json.deserializer.kmip;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.ProtocolVersion;

import java.io.IOException;

public class ProtocolVersionJsonDeserializer extends KmipDataTypeJsonDeserializer<ProtocolVersion> {

    @Override
    public ProtocolVersion deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        KmipTag.Value tag = p.getCodec().treeToValue(node, KmipTag.class).value();

        if (!node.isObject() || tag != KmipTag.Standard.PROTOCOL_VERSION) {
            ctxt.reportInputMismatch(ProtocolVersion.class, "Expected object for ProtocolVersion");
            return null;
        }

        JsonNode values = node.get("value");
        if (values == null || !values.isArray() || values.size() != 2) {
            ctxt.reportInputMismatch(ProtocolVersion.class, "ProtocolVersion 'value' must be array length 2");
            return null;
        }

        JsonNode majorObj = values.get(0);
        JsonNode minorObj = values.get(1);

        JsonNode majorVal = majorObj.get("value");
        JsonNode minorVal = minorObj.get("value");
        if (majorVal == null || !majorVal.isInt() || minorVal == null || !minorVal.isInt()) {
            ctxt.reportInputMismatch(ProtocolVersion.class, "Major/minor 'value' must be integers");
            return null;
        }

        // Delegate to child deserializers
        ProtocolVersion.ProtocolVersionMajor major =
                p.getCodec().treeToValue(majorObj, ProtocolVersion.ProtocolVersionMajor.class);
        ProtocolVersion.ProtocolVersionMinor minor =
                p.getCodec().treeToValue(minorObj, ProtocolVersion.ProtocolVersionMinor.class);

        return ProtocolVersion.of(major, minor);
    }
}


