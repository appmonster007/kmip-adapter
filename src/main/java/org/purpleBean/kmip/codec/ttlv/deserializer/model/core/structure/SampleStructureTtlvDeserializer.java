package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.ActivationDate;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.structure.SampleStructure;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SampleStructureTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<SampleStructure, SampleStructure.SampleStructureBuilder> {

    public SampleStructureTtlvDeserializer() {
        super(SampleStructure.kmipTag);
    }

    @Override
    protected SampleStructure.SampleStructureBuilder createBuilder() {
        return SampleStructure.builder();
    }

    @Override
    protected void setValue(SampleStructure.SampleStructureBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ACTIVATION_DATE -> builder.activationDate(mapper.readValue(p, ActivationDate.class));
            case KmipTag.Standard.STATE -> builder.state(mapper.readValue(p, State.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected SampleStructure build(SampleStructure.SampleStructureBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return SampleStructure.encodingType;
    }
}