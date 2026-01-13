package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.CancellationResult;

public class CancellationResultTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CancellationResult, Integer> {

    public CancellationResultTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}