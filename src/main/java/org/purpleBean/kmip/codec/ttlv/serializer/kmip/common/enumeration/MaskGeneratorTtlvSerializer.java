package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.MaskGenerator;

public class MaskGeneratorTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<MaskGenerator, Integer> {

    public MaskGeneratorTtlvSerializer() {
        super(MaskGenerator::getValue);
    }
}