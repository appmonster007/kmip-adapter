package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.MACSignatureKeyInformation;

public class MACSignatureKeyInformationTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<MACSignatureKeyInformation, String> {

    public MACSignatureKeyInformationTtlvSerializer() {
        super(MACSignatureKeyInformation::getValue);
    }
}