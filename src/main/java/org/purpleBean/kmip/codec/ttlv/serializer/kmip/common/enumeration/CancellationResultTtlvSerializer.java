package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.CancellationResult;

public class CancellationResultTtlvSerializer extends AbstractKmipTtlvSerializer<CancellationResult, Integer> {

    public CancellationResultTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}