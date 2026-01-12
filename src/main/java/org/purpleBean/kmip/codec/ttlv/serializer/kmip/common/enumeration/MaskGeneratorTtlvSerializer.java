package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.MaskGenerator;

public class MaskGeneratorTtlvSerializer extends AbstractKmipTtlvSerializer<MaskGenerator, Integer> {

    public MaskGeneratorTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}