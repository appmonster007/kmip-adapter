package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.MaskGenerator;

public class MaskGeneratorTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<MaskGenerator, Integer> {

    public MaskGeneratorTtlvSerializer() {
        super(MaskGenerator::getIntValue);
    }
}