package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.RngAlgorithm;

public class RngAlgorithmTtlvSerializer extends AbstractKmipTtlvSerializer<RngAlgorithm, Integer> {

    public RngAlgorithmTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}