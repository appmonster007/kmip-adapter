package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.AsynchronousIndicator;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AsynchronousIndicator XML Serialization")
class AsynchronousIndicatorXmlTest extends AbstractXmlSerializationTestSuite<AsynchronousIndicator> {
    @Override
    protected Class<AsynchronousIndicator> type() {
        return AsynchronousIndicator.class;
    }

    @Override
    protected AsynchronousIndicator createDefault() {
        return AsynchronousIndicator.Standard.MANDATORY.inst();
    }

    @Override
    protected AsynchronousIndicator createVariant() {
        return AsynchronousIndicator.Standard.OPTIONAL.inst();
    }
}
