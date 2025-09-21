package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;

import java.io.IOException;
import java.util.NoSuchElementException;

public class SimpleRequestHeaderJsonDeserializer extends KmipDataTypeJsonDeserializer<SimpleRequestHeader> {
    private final EncodingType encodingType = EncodingType.STRUCTURE;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.REQUEST_HEADER);

    @Override
    public SimpleRequestHeader deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        KmipTag.Value tag = p.getCodec().treeToValue(node, KmipTag.class).value();
        if (!node.isObject() || tag != kmipTag.value()) {
            ctxt.reportInputMismatch(SimpleRequestHeader.class, "Expected object for SimpleRequestHeader");
            return null;
        }

        JsonNode values = node.get("value");
        if (values == null || !values.isArray()) {
            ctxt.reportInputMismatch(SimpleRequestHeader.class, "SimpleRequestHeader 'value' must be an array");
            return null;
        }

        SimpleRequestHeader.SimpleRequestHeaderBuilder builder = SimpleRequestHeader.builder();

        for (JsonNode valueNode : values) {
            KmipTag.Value childTag = p.getCodec().treeToValue(valueNode, KmipTag.class).value();
            if (childTag == KmipTag.Standard.PROTOCOL_VERSION) {
                builder.protocolVersion(p.getCodec().treeToValue(valueNode, ProtocolVersion.class));
            }
        }

        SimpleRequestHeader header = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!header.isSupportedFor(spec)) {
            throw new NoSuchElementException();
        }

        return header;
    }
}
