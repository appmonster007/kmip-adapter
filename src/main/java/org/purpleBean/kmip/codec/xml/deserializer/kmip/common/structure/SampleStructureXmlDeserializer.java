package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.SampleStructure;

import java.io.IOException;
import java.util.Map;

public class SampleStructureXmlDeserializer extends KmipDataTypeXmlDeserializer<SampleStructure> {

    @Override
    public SampleStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(SampleStructure.class, "Expected XML object for SampleStructure");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        SampleStructure.SampleStructureBuilder builder = SampleStructure.builder();

        for (Map.Entry<String, JsonNode> entry : node.propertyStream().toList()) {
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        SampleStructure sampleStructure = builder.build();

        if (!sampleStructure.isSupportedFor(spec)) {
            ctxt.reportInputMismatch(SampleStructure.class, "SampleStructure not supported for spec " + spec);
        }

        return sampleStructure;
    }

    private void setValue(SampleStructure.SampleStructureBuilder builder, KmipTag.Value nodeTag, JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ACTIVATION_DATE ->
                    builder.activationDate(p.getCodec().treeToValue(node, ActivationDateAttribute.class));
            case KmipTag.Standard.STATE -> builder.state(p.getCodec().treeToValue(node, State.class));
            default -> throw new IllegalArgumentException();
        }
    }
}
