package org.purpleBean.kmip.codec.xml.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.DigestValue;
import org.purpleBean.kmip.common.enumeration.HashingAlgorithm;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;
import org.purpleBean.kmip.common.structure.Digest;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("Digest Xml Serialization Tests")
class DigestXmlTest extends AbstractXmlSerializationSuite<Digest> {

    @Override
    protected Class<Digest> type() {
        return Digest.class;
    }

    @Override
    protected Digest createDefault() {
        return Digest.builder()
                .hashingAlgorithm(new HashingAlgorithm(HashingAlgorithm.Standard.SHA_256))
                .digestValue(DigestValue.of(new byte[0]))
                .keyFormatType(new KeyFormatType(KeyFormatType.Standard.PKCS_1))
                .build();
    }
}
