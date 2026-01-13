package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.MaskGenerator;

public class MaskGeneratorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<MaskGenerator, Integer> {

    public MaskGeneratorTtlvDeserializer() {
        super(MaskGenerator.kmipTag, MaskGenerator.encodingType, Integer.class, value -> new MaskGenerator(MaskGenerator.fromValue(value)));
    }
}