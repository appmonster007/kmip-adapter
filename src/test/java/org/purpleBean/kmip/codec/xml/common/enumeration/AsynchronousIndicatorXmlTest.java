package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.AsynchronousIndicator;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("AsynchronousIndicator XML Serialization")
class AsynchronousIndicatorXmlTest extends AbstractXmlSerializationSuite<AsynchronousIndicator> {
    @Override
    protected Class<AsynchronousIndicator> type() {
        return AsynchronousIndicator.class;
    }

    @Override
    protected AsynchronousIndicator createDefault() {
        return new AsynchronousIndicator(AsynchronousIndicator.Standard.MANDATORY);
    }

    @Override
    protected AsynchronousIndicator createVariant() {
        return new AsynchronousIndicator(AsynchronousIndicator.Standard.OPTIONAL);
    }
}
