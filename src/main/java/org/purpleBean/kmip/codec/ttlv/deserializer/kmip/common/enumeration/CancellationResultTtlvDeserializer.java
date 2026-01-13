package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.CancellationResult;

public class CancellationResultTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CancellationResult, Integer> {

    public CancellationResultTtlvDeserializer() {
        super(CancellationResult.kmipTag, CancellationResult.encodingType, Integer.class, value -> new CancellationResult(CancellationResult.fromValue(value)));
    }
}