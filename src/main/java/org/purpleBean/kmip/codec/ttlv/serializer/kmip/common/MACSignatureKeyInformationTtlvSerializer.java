package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.MACSignatureKeyInformation;

public class MACSignatureKeyInformationTtlvSerializer extends AbstractKmipTtlvSerializer<MACSignatureKeyInformation, String> {

    public MACSignatureKeyInformationTtlvSerializer() {
        super(MACSignatureKeyInformation::getValue);
    }
}