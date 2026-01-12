package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.common.ActivationDate;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.SampleStructure;

import java.io.IOException;

public class SampleStructureJsonDeserializer extends AbstractKmipStructureJsonDeserializer<SampleStructure, SampleStructure.SampleStructureBuilder> {

    public SampleStructureJsonDeserializer() {
        super(SampleStructure.kmipTag, SampleStructure.encodingType);
    }

    @Override
    protected SampleStructure.SampleStructureBuilder createBuilder() {
        return SampleStructure.builder();
    }

    @Override
    protected void setValue(SampleStructure.SampleStructureBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ACTIVATION_DATE -> builder.activationDate(ctxt.readValue(p, ActivationDate.class));
            case KmipTag.Standard.STATE -> builder.state(ctxt.readValue(p, State.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SampleStructure build(SampleStructure.SampleStructureBuilder builder) {
        return builder.build();
    }
}