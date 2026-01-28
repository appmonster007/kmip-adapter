package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.CancellationResult;

public class CancellationResultTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CancellationResult, Integer> {

    public CancellationResultTtlvSerializer() {
        super(CancellationResult::getIntValue);
    }
}