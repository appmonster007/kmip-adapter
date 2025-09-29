package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;

import java.io.IOException;
import java.util.Map;

public class SimpleRequestHeaderXmlDeserializer extends KmipDataTypeXmlDeserializer<SimpleRequestHeader> {

    @Override
    public SimpleRequestHeader deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(SimpleRequestHeader.class, "Expected XML object for SimpleRequestHeader");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        SimpleRequestHeader.SimpleRequestHeaderBuilder builder = SimpleRequestHeader.builder();

        for (Map.Entry<String, JsonNode> entry : node.propertyStream().toList()) {
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        SimpleRequestHeader header = builder.build();

        if (!header.isSupported()) {
            ctxt.reportInputMismatch(SimpleRequestHeader.class, "SimpleRequestHeader not supported for spec " + spec);
        }

        return header;
    }

    private void setValue(SimpleRequestHeader.SimpleRequestHeaderBuilder builder,
                          KmipTag.Value nodeTag,
                          JsonNode node,
                          JsonParser p,
                          DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.PROTOCOL_VERSION ->
                    builder.protocolVersion(p.getCodec().treeToValue(node, ProtocolVersion.class));
            default -> throw new IllegalArgumentException();
        }
    }
}
