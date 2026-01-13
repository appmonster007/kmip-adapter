package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.RngAlgorithm;

public class RngAlgorithmTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<RngAlgorithm, Integer> {

    public RngAlgorithmTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}