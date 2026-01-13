package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.common.ActivationDate;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.SampleStructure;

import java.io.IOException;

public class SampleStructureXmlDeserializer extends AbstractKmipStructureXmlDeserializer<SampleStructure, SampleStructure.SampleStructureBuilder> {

    public SampleStructureXmlDeserializer() {
        super(SampleStructure.kmipTag);
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