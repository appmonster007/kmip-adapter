package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.CancellationResult;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("CancellationResult XML Serialization")
class CancellationResultXmlTest extends AbstractXmlSerializationSuite<CancellationResult> {
    @Override
    protected Class<CancellationResult> type() {
        return CancellationResult.class;
    }

    @Override
    protected CancellationResult createDefault() {
        return new CancellationResult(CancellationResult.Standard.CANCELED);
    }

    @Override
    protected CancellationResult createVariant() {
        return new CancellationResult(CancellationResult.Standard.UNABLE_TO_CANCEL);
    }
}
