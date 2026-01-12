package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.DrbgAlgorithm;

public class DrbgAlgorithmTtlvSerializer extends AbstractKmipTtlvSerializer<DrbgAlgorithm, Integer> {

    public DrbgAlgorithmTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}