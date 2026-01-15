package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.DrbgAlgorithm;

public class DrbgAlgorithmTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<DrbgAlgorithm, Integer> {

    public DrbgAlgorithmTtlvSerializer() {
        super(DrbgAlgorithm::getValue);
    }
}