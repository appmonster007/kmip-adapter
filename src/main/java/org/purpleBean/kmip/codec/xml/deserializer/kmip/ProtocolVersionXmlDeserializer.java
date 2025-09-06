package org.purpleBean.kmip.codec.xml.deserializer.kmip;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.codec.KmipCodecContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.common.enumeration.State;

import java.io.IOException;

public class ProtocolVersionXmlDeserializer extends JsonDeserializer<ProtocolVersion> {

    @Override
    public ProtocolVersion deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ProtocolVersion.class,
                    "Expected XML element object for ProtocolVersion");
            return null;
        }

        JsonNode majorNode = node.get(KmipTag.Standard.PROTOCOL_VERSION_MAJOR.getDescription());
        JsonNode minorNode = node.get(KmipTag.Standard.PROTOCOL_VERSION_MINOR.getDescription());

        if (majorNode == null || minorNode == null) {
            ctxt.reportInputMismatch(ProtocolVersion.class,
                    "Missing ProtocolVersionMajor or ProtocolVersionMinor element");
            return null;
        }

        // Delegate to child deserializers
        ProtocolVersion.ProtocolVersionMajor major =
                p.getCodec().treeToValue(majorNode, ProtocolVersion.ProtocolVersionMajor.class);
        ProtocolVersion.ProtocolVersionMinor minor =
                p.getCodec().treeToValue(minorNode, ProtocolVersion.ProtocolVersionMinor.class);

        ProtocolVersion protocolVersion = ProtocolVersion.of(major, minor);

        KmipSpec spec = KmipCodecContext.getSpec();
        if (!protocolVersion.isSupportedFor(spec)) {
            ctxt.reportInputMismatch(ProtocolVersion.class,
                    "ProtocolVersion not supported for spec " + spec);
        }

        return protocolVersion;
    }
}
