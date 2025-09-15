package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.SampleStructure;

import java.io.IOException;
import java.util.NoSuchElementException;

public class SampleStructureJsonDeserializer extends KmipDataTypeJsonDeserializer<SampleStructure> {

    @Override
    public SampleStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        KmipTag.Value tag = p.getCodec().treeToValue(node, KmipTag.class).getValue();

        if (!node.isObject() || tag != KmipTag.Standard.SECRET_DATA) {
            ctxt.reportInputMismatch(SampleStructure.class, "Expected object for SampleStructure");
            return null;
        }

        JsonNode values = node.get("value");
        if (values == null || !values.isArray() || values.isEmpty()) {
            ctxt.reportInputMismatch(SampleStructure.class, "SampleStructure 'value' must be an array");
            return null;
        }

        SampleStructure.SampleStructureBuilder builder = SampleStructure.builder();

        for (JsonNode valueNode : values.valueStream().toList()) {
            KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
            setValue(builder, nodeTag, valueNode, p, ctxt);
        }

        SampleStructure sampleStructure = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!sampleStructure.isSupportedFor(spec)) {
            throw new NoSuchElementException();
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


