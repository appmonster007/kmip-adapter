package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.DrbgAlgorithm;

public class DrbgAlgorithmTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<DrbgAlgorithm, Integer> {

    public DrbgAlgorithmTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}